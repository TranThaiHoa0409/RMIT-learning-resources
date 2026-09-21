package com.example.tutorial05.presentation.ui

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.tutorial05.domain.model.Restaurant
import com.example.tutorial05.presentation.viewmodel.MapViewModel
import com.example.tutorial05.presentation.viewmodel.MapViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("MissingPermission")
@Composable
fun InteractiveMapScreen() {
    val context = LocalContext.current
    val viewModel: MapViewModel = remember {
        val activity = context as ComponentActivity
        ViewModelProvider(
            activity,
            MapViewModelFactory(context.applicationContext)
        ).get(MapViewModel::class.java)
    }

    val cameraPositionState = rememberCameraPositionState()
    val restaurants = viewModel.restaurants
    val currentLocation = viewModel.currentLocation.value

    // Animate camera when location updates
    LaunchedEffect(currentLocation) {
        currentLocation?.let {
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(it, 15f)
            )
        }
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Start,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.loadRestaurants()
            }) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
    ) { padding ->
        GoogleMap(
            modifier = Modifier.padding(padding),
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                viewModel.addRestaurant(latLng, generateRandomName())
            }
        ) {
            // Render User's Current Location Marker (Blue Marker)
            currentLocation?.let { userLatLng ->
                Marker(
                    state = MarkerState(position = userLatLng),
                    title = "My Location",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                )
            }

            // Render Restaurant Markers
            restaurants.forEach { restaurant ->
                Marker(
                    state = MarkerState(position = LatLng(restaurant.lat, restaurant.lng)),
                    title = restaurant.name
                )
            }
        }
    }
}

fun generateRandomName(): String {
    val adjectives = listOf("Cozy", "Spicy", "Golden", "Urban", "Happy", "Tasty", "Chill", "Sunny")
    val nouns = listOf("Bistro", "Diner", "Cafe", "Hub", "Corner", "Spot", "House", "Place")
    return "${adjectives.random()} ${nouns.random()}"
}