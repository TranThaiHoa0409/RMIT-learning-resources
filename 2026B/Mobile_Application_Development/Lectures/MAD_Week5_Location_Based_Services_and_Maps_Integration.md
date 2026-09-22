# Location-based Services and Maps Integration

## Week Agenda

- Introduction to Google Maps API
- Permissions runtime handling with Accompanist Permissions
- Introduction to Google Location Services

## Introduction to Google Maps API

### Google Maps Android API

With Google Maps Android API, you can add maps based on Google Maps data to your application.

The API includes:

- Access to Map servers
- Data downloading
- Map display
- Response to map gestures

The API allows you to add:

- Icons anchored to specific positions on the map (Markers)
- Sets of line segments (Polylines)
- Enclosed segments (Polygons)
- Bitmap graphics anchored to specific positions on the map (Ground Overlays)
- Sets of images which are displayed on top of the base map tiles (Tile Overlays)

![Maps Utils Demo app showing markers and cluster counts on a map of London](images/Week5/05_maps_markers_demo.png)

### Using the API

- Course covers Google Maps Android API v2
- Part of Google Play services library
- Original v1 library is deprecated
- Original apps still work, but new API keys not available

### Types of Apps You Can Build

- Adding maps to Android apps
  - Using Google Store and other location finders
  - Finding distances between locations
  - Displaying the current location
- For more complex tasks, pass user to Google Maps

### Things You Must Do

- Get a Google account
- Display an attribution notice
- Some services might require an enterprise license — https://developers.google.com/maps/premium/overview

### Things Not To Do

- Don't just re-implement Google Maps or Google Earth
- Don't create a wrapper for redistributing the API
- Don't hide any copyright notices or advertising

### Setting Up Environment

- Add Google API Key to `AndroidManifest.xml`
- What is an API key?
  - A code passed in by computer programs calling an API
  - To identify the calling program
  - Used to track and control how the API is being used

![Diagram of an Android/mobile app sending an API key to Google Maps](images/Week5/09_api_key_flow_diagram.png)

### Enable Maps SDK for Android

- Go to Google Cloud Console
- Create a project or select an existing one
- Enable **Maps SDK for Android** (Search bar)
- Generate an **API Key**
- Add API Key to `AndroidManifest.xml`

```xml
<application>
    <meta-data
        android:name="com.google.android.geo.API_KEY"
        android:value="AIzaSyD-EEdM3Oks_LXAumGsCxAAfURC-FyFsP0" />
</application>
```

- Add Dependencies to `build.gradle(:app)`

```kotlin
implementation("com.google.maps.android:maps-compose:2.11.4")
implementation("com.google.android.gms:play-services-maps:18.1.0")
```

### Display a Map using Compose

```kotlin
@Composable
fun MapScreen() {
    val singapore = LatLng(1.35, 103.87)
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 12f)
        }
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore"
        )
    }
}
```

![Google Map centered on Singapore with a marker](images/Week5/11_map_singapore_marker.png)

### Key Operations in Jetpack Compose

**Adding Markers**

```kotlin
GoogleMap(...) {
    Marker(
        state = MarkerState(position = LatLng(37.7749, -122.4194)),
        title = "San Francisco",
        snippet = "A cool city!"
    )
}
```

**Customizing Marker Appearance**

```kotlin
val context = LocalContext.current
val icon = BitmapDescriptorFactory.fromResource(R.drawable.restaurant_marker)

Marker(
    state = MarkerState(position = LatLng(37.7749, -122.4194)),
    icon = icon,
    title = "Custom Icon Marker"
)
```

**Handling Marker Click Events**

```kotlin
GoogleMap(...) {
    Marker(
        state = MarkerState(position = location),
        title = "Click Me",
        onClick = {
            println("Marker clicked")
            true // or false depending on behavior
        }
    )
}
```

**Controlling the Camera (Zoom, Pan, Animate)**

```kotlin
val cameraPositionState = rememberCameraPositionState()

LaunchedEffect(Unit) {
    cameraPositionState.animate(
        update = CameraUpdateFactory.newLatLngZoom(LatLng(40.7128, -74.0060), 12f),
        durationMs = 1000
    )
}

GoogleMap(cameraPositionState = cameraPositionState)
```

**Map Types**

```kotlin
// Types: MapType.NORMAL, MapType.SATELLITE,
// MapType.TERRAIN, MapType.HYBRID, MapType.NONE
var mapType by remember { mutableStateOf(MapType.NORMAL) }

GoogleMap(
    properties = MapProperties(mapType = mapType),
    ...
)
```

**Displaying Polylines or Shapes**

```kotlin
Polyline(
    points = listOf(
        LatLng(0.0, 0.0),
        LatLng(1.0, 1.0),
        LatLng(2.0, 2.0)
    ),
    color = Color.Blue,
    width = 5f
)
// Same for Circle(), Polygon(), etc.
```

## Runtime permissions handling with Accompanist Permissions

### Accompanist Permissions

- Add Accompanist Permissions Dependency

```kotlin
implementation("com.google.accompanist:accompanist-permissions:0.37.3")
```

- Request Permissions in `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

- Make sure to update to the latest composeBom and Kotlin version (`libs.versions.toml`)

```toml
kotlin = "2.2.0"
composeBom = "2025.07.00"
```

```kotlin
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequestScreen() {
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            permissionsState.allPermissionsGranted -> {
                Text(text = "All location permissions granted!")
            }

            permissionsState.shouldShowRationale -> {
                Text(text = "We need your location permission to proceed.")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { permissionsState.launchMultiplePermissionRequest() }) {
                    Text(text = "Grant Permissions")
                }
            }

            else -> {
                Text(text = "Some location permissions are not granted.")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { permissionsState.launchMultiplePermissionRequest() }) {
                    Text(text = "Request Permissions")
                }
            }
        }
    }
}
```

## Introduction to Google Location Services

### Google Location Services

- Add Location Services Dependency

```kotlin
implementation("com.google.android.gms:play-services-location:21.3.0")
```

- Request Permissions in `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

- Get Current Location (with fused location provider)

```kotlin
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
                        val location = getLastKnownLocation(fusedLocationProviderClient = fusedLocationClient)
                        location?.let {
                            val latLng = LatLng(it.latitude, it.longitude)
                            currentLocation = latLng
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newLatLngZoom(latLng, 15f),
                                durationMs = 1000
                            )
                        }
                        Log.d("LocationMapScreen", "Location fetched: $currentLocation")
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
```

- The method to return the last known location (on the device) if available

```kotlin
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
```

### Using LocationRequest - Get location updates

We can use `FusedLocationProviderClient` to get location updates instead of just calling `lastLocation`.

```kotlin
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationMapScreen() {
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

    // Set up location request
    val locationRequest: LocationRequest = remember {
        LocationRequest.Builder(5000L) // 5 seconds
            .setMinUpdateIntervalMillis(3000L)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()
    }

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

    // Start updates when permissions are granted
    LaunchedEffect(permissionsState.allPermissionsGranted) {
        if (permissionsState.allPermissionsGranted) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    // Stop updates when Composable leaves the screen
    DisposableEffect(Unit) {
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    // UI (Map + Info)
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
```
