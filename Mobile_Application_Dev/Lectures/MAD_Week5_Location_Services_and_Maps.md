# Week 5: Location-based Services and Maps Integration

**Course:** COSC2657 / COSC2543 / COSC2729 — Android Development
**Lecturer:** Minh T. Vu

## Agenda
- Introduction to Google Maps API
- Permissions runtime handling with Accompanist Permissions
- Introduction to Google Location Services

---

## 1. Introduction to Google Maps API

### Google Maps Android API
With the Google Maps Android API, you can add maps based on Google Maps data to your application.

**The API includes:**
- Access to map servers
- Data downloading
- Map display
- Response to map gestures

**You can add:**
- Icons anchored to specific positions (**Markers**)
- Sets of line segments (**Polylines**)
- Enclosed segments (**Polygons**)
- Bitmap graphics anchored to positions (**Ground Overlays**)
- Sets of images displayed on top of base map tiles (**Tile Overlays**)

### Using the API
- This course covers **Google Maps Android API v2**, part of the Google Play services library.
- The original v1 library is deprecated — old apps still work, but new API keys are no longer available.

### Types of Apps You Can Build
- Adding maps to Android apps
- Using Google Store and other location finders
- Finding distances between locations
- Displaying the current location
- For more complex tasks, pass the user to the full Google Maps app

### Things You Must Do
- Get a Google account
- Display an attribution notice
- Some services may require an enterprise license ([Google Maps Premium overview](https://developers.google.com/maps/premium/overview))

### Things Not To Do
- Don't re-implement Google Maps or Google Earth
- Don't create a wrapper for redistributing the API
- Don't hide copyright notices or advertising

### Setting Up the Environment
- Add a **Google API Key** to `AndroidManifest.xml`.
- **What is an API key?** A code passed by a calling program to identify itself; used to track and control API usage.

### Enabling Maps SDK for Android
1. Go to the **Google Cloud Console**.
2. Create a project or select an existing one.
3. Enable **Maps SDK for Android** (via search bar).
4. Generate an **API Key**.
5. Add the API Key to `AndroidManifest.xml`.
6. Add dependencies to `build.gradle(:app)`.

### Displaying a Map with Compose
Example (slide 11): a `MapScreen()` composable defines a `LatLng` for Singapore (`1.35`, `103.87`) and renders a `GoogleMap()` composable that fills the available size. Its `cameraPositionState` is built with `rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(target = singapore, zoom = 12f) }`. A `Marker(state = MarkerState(position = singapore), title = "Singapore")` is placed inside the `GoogleMap` content block to show a pin on the map.

### Key Operations in Jetpack Compose
- Adding Markers
- Customizing Marker appearance
- Handling Marker click events
- Controlling the camera (zoom, pan, animate)
- Map types
- Displaying Polylines or shapes (same pattern applies to `Circle()`, `Polygon()`, etc.)

---

## 2. Runtime Permissions with Accompanist Permissions

### Setup
- Add the **Accompanist Permissions** dependency.
- Request permissions in `AndroidManifest.xml`.
- Make sure `composeBom` and the Kotlin version are up to date (`libs.versions.toml`).

### Example Pattern
A composable annotated with `@OptIn(ExperimentalPermissionsApi::class)` uses `rememberMultiplePermissionsState(permissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))` to track the location permissions. The UI then branches on the state:
- `permissionsState.allPermissionsGranted` → show "All location permissions granted!"
- `permissionsState.shouldShowRationale` → show a rationale message plus a **"Grant Permissions"** button that calls `permissionsState.launchMultiplePermissionRequest()`
- otherwise → show "Some location permissions are not granted." plus a **"Request Permissions"** button

---

## 3. Introduction to Google Location Services

### Setup
- Add the **Location Services** dependency.
- Request permissions in `AndroidManifest.xml`.
- Get the current location using the **Fused Location Provider**.

### Getting the Last Known Location
A `LocationMapScreen()` composable obtains a `fusedLocationClient` via `remember { LocationServices.getFusedLocationProviderClient(context) }`, tracks permissions with `rememberMultiplePermissionsState(...)` for `ACCESS_FINE_LOCATION`/`ACCESS_COARSE_LOCATION`, and holds a `cameraPositionState` plus a nullable `currentLocation` (`LatLng?`) via `remember { mutableStateOf<LatLng?>(value = null) }`, along with a `coroutineScope = rememberCoroutineScope()`.

The `GoogleMap` composable is placed in a `Column`, weighted to fill remaining space (`Modifier.fillMaxWidth().weight(1f)`), using the shared `cameraPositionState`. If `currentLocation` is non-null, a `Marker(state = MarkerState(position = it), title = "You are here")` is drawn.

Below the map, a `Column` shows either:
- If **all permissions are granted**: a **"Show My Location"** button. On click, it launches a coroutine that calls `getLastKnownLocation(fusedLocationProviderClient = fusedLocationClient)`, converts the returned location into a `LatLng`, stores it in `currentLocation`, and animates the camera to it via `cameraPositionState.animate(update = CameraUpdateFactory.newLatLngZoom(latLng, zoom = 15f), durationMs = 1000)`.
- Otherwise: a **"Request Location Permissions"** button that calls `permissionsState.launchMultiplePermissionRequest()`.

- **The `getLastKnownLocation` method** returns the last known location on the device, if available.

### Using `LocationRequest` — Getting Continuous Location Updates
- **`FusedLocationProviderClient`** can also be used to get **ongoing** location updates, instead of just the one-off `lastLocation` call.
- A `DisposableEffect(key1 = Unit)` stops updates when the composable leaves the screen: its `onDispose` block calls `fusedLocationClient.removeLocationUpdates(p0 = locationCallback)`.
- The UI (map + info) again uses a `Column` with a weighted `GoogleMap` showing a `Marker` at `currentLocation` titled "You are here", below a `Column` that:
  - Shows a **"Request Location Permissions"** button if permissions are not all granted.
  - Otherwise shows the text **"Receiving location updates every 5 seconds"**, indicating the app is actively subscribed to periodic location callbacks.

---

*End of Week 5 — Location-based Services and Maps Integration*
