package com.example.googlemapsexample

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationMapScreen() {
    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val cameraPositionState = rememberCameraPositionState()
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraPositionState = cameraPositionState
        ) {
            currentLocation?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "You are here"
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (permissionsState.allPermissionsGranted) {
                Button(onClick = {
                    coroutineScope.launch {
                        val location = getLastKnownLocation(fusedLocationClient)
                        location?.let {
                            val latLng = LatLng(it.latitude, it.longitude)
                            currentLocation = latLng
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newLatLngZoom(latLng, 15f),
                                durationMs = 1000
                            )
                        }
                    }
                }) {
                    Text(text = "Show My Location")
                }
            } else {
                Button(onClick = { permissionsState.launchMultiplePermissionRequest() }) {
                    Text(text = "Request Location Permissions")
                }
            }
        }
    }
}

@SuppressLint("MissingPermission")
suspend fun getLastKnownLocation(
    fusedLocationProviderClient: FusedLocationProviderClient
): Location? {
    return suspendCancellableCoroutine { cont ->
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                Log.d("LocationMapScreen", "Last known location: $location")
                cont.resume(location)
            }
            .addOnFailureListener {
                Log.d("LocationMapScreen", "Failed to get last known location", it)
                cont.resume(null)
            }
    }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContinuousLocationMapScreen() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val cameraPositionState = rememberCameraPositionState()
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // 1. Setup Location Request
    val locationRequest = remember {
        LocationRequest.Builder(5000L) // 5 seconds
            .setMinUpdateIntervalMillis(3000L)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()
    }

    // 2. Setup Callback
    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { location ->
                    val latLng = LatLng(location.latitude, location.longitude)
                    currentLocation = latLng
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newLatLngZoom(latLng, 16f),
                            durationMs = 800
                        )
                    }
                }
            }
        }
    }

    // 3. Start updates when permissions are granted
    LaunchedEffect(permissionsState.allPermissionsGranted) {
        if (permissionsState.allPermissionsGranted) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    // 4. Stop updates when Composable leaves screen
    DisposableEffect(Unit) {
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    // UI Layout
    Column(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraPositionState = cameraPositionState
        ) {
            currentLocation?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "You are here"
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!permissionsState.allPermissionsGranted) {
                Button(onClick = { permissionsState.launchMultiplePermissionRequest() }) {
                    Text(text = "Request Location Permissions")
                }
            } else {
                Text(text = "Receiving location updates every 5 seconds")
            }
        }
    }
}
