# MAD Week 5 – Pre-Class: Maps and Permissions

## 1. Google Maps Android API

Building dynamic, interactive experiences that respond to where users are and what's around them requires location-aware apps, which use the Google Maps Android API.

### Why Maps Are Needed in Apps

Apps like Pokémon GO, Uber, and food delivery services rely on maps and location data to create engaging, personalised experiences.

The map in Pokémon GO is a core feature, utilizing GPS data to overlay digital elements (such as Pokémon, PokéStops, and Gyms) onto the real-world surroundings.

To do so, the app needs to connect to Google's map servers to download map tiles, satellite imagery, and location data. The app cannot store all the world's maps locally; it needs to request specific map data based on the user's location. The Google Maps Android API serves as the bridge between the app and Google's mapping services.

### What Is the Google Maps Android API?

The Google Maps Android API, also known as the Maps SDK for Android, is a set of tools and protocols that allows developers to integrate Google Maps data and functionality into their Android applications. It is essentially a software development kit (SDK) that simplifies the process of displaying maps, accessing location data, and handling user interactions within an Android app.

Using the Maps API gives an app access to:

- Map data: tiles, satellite imagery, street information
- Interactive controls: zoom, pan, tap responses
- Location services: GPS positioning, place detection
- Custom elements: markers, routes, and overlays added by the developer

### Location-Based Services

Location-based services enable the app to track the user's location continuously and update the app experience accordingly.

In Pokémon GO, when the app is opened, it requests the user's location from Google Play Services, which uses GPS, Wi-Fi, and cell towers to pinpoint the user's position. The app then uses this location data to centre the map on the user's position, load nearby Pokémon and PokéStops, and trigger location-based game events.

### How an App Consumes the Google Maps API

Adding maps to an Android app involves the following key steps:

- Setup: get API key, add SDK, set permissions
- Display: create MapFragment, load map data
- Interact: add markers, handle gestures, update elements
- Location: track user position, respond to movement

Official reference: [Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk)

### Important Reminders

When creating maps, it is important to always display proper attribution notices. Enterprise licensing requirements should be considered, especially for commercial applications. Google's copyright notices must never be hidden or removed, as this respects intellectual property. User privacy should be considered when requesting location permissions, to ensure a responsible approach to user data.

## 2. Permissions Handling with Accompanist Permissions

### What Are Permissions in Android?

In an Android app, permissions are requests made by the app to access certain resources or perform specific actions on the user's device.

There are two types of permissions:

| Permission Type | Description |
|---|---|
| Normal Permissions | Automatically granted at installation time. Includes basic actions such as accessing network state or using the phone's vibrator. |
| Dangerous Permissions | Require explicit user approval. Grant access to sensitive actions or data, such as using the camera, accessing contacts, or tracking location. Users are asked to grant these permissions when the app requests them. |

Permissions help protect user privacy by allowing them to control which apps can access their data or hardware features, such as the camera or microphone. Developers must handle permissions properly, requesting only necessary permissions at runtime and clearly explaining to users why each permission is required.

The traditional method involved complex callback patterns that did not align well with Compose's declarative approach, which is addressed by using the Accompanist library to simplify the process.

### Accompanist Library

Accompanist is a collection of extension libraries for Jetpack Compose that provides functionality not yet available in the core Compose toolkit. For permissions specifically, it offers Accompanist Permissions - a Compose-friendly way to handle runtime permissions.

Accompanist Permissions provides Composable functions that handle permission requests declaratively, fitting naturally into a Compose UI.

### How It Works

Instead of managing permission callbacks manually, permissions can be handled directly in Composables:

```kotlin
@Composable
fun CameraScreen() {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    
    when {
        cameraPermissionState.status.isGranted -> {
            // Show camera UI
        }
        cameraPermissionState.status.shouldShowRationale -> {
            // Show explanation UI
        }
        else -> {
            // Show request permission button
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Grant Camera Permission")
            }
        }
    }
}
```

### Why Use Accompanist for Permissions

- Declarative: fits Compose's declarative paradigm
- State Management: automatically manages permission state
- Recomposition: UI updates automatically when permissions change
- Less Boilerplate: no need for complex callback handling

The library essentially bridges the gap between Android's imperative permission system and Compose's declarative UI model, making permission handling much cleaner in Compose apps.
