package com.example.googlemapsexample

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import android.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.googlemapsexample.ui.theme.GoogleMapsExampleTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //MapScreen()
            //AdvancedMapFeaturesDemo()
            //PermissionRequestScreen()
            //LocationMapScreen()
            ContinuousLocationMapScreen()
        }
    }
}

@Composable
fun MapScreen() {
    val rmitVietnam = LatLng(10.7297, 106.6939)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(rmitVietnam, 12f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = rmitVietnam),
            title = "RMIT Vietnam"
        )
    }
}

@Composable
fun AdvancedMapFeaturesDemo() {
    val context = LocalContext.current
    var mapType by remember { mutableStateOf(MapType.SATELLITE) }
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(LatLng(37.7749, -122.4194), 12f),
            durationMs = 1000
        )
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = mapType)
    ) {
        // 1. Custom Marker Icon & Click Event
        val icon = bitmapDescriptorFromVector(context, R.drawable.restaurant_marker)
        Marker(
            state = MarkerState(position = LatLng(37.7749, -122.4194)),
            icon = icon,
            title = "Custom Icon Marker",
            snippet = "A cool city!",
            onClick = {
                println("Marker clicked")
                true // Handles click event
            }
        )

        // 2. Drawing Shapes (Polyline)
        Polyline(
            points = listOf(
                LatLng(37.7749, -122.4194), // San Francisco City Center (Start)
                LatLng(37.7849, -122.4094), // Union Square area
                LatLng(37.8049, -122.4094)  // Fisherman's Wharf area (End)
            ),
            color = Color.Blue,
            width = 10f // Increased slightly for better visibility on screen
        )
    }
}

fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

