# Tutorial 10: Hardware Integration and Machine Learning

## Objectives

In this tutorial, you will build a **Smart Scanner app** that demonstrates:

- Using the **Proximity Sensor** to pause/resume scanning
- Getting the **device's location** (GPS) with Fused Location Provider
- Showing a **CameraX preview**
- Running **ML Kit Object Classification** in real time
- Displaying detected objects + location in a Jetpack Compose UI

## Part 1 - Setup Requirements

- Add these permissions in `AndroidManifest.xml`:
  - `<uses-permission android:name="android.permission.CAMERA" />`
  - `<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />`
  - `<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />`
- Add these dependencies in `build.gradle (app)`:

```kotlin
val cameraxVersion = "1.3.0"

// CameraX
implementation("androidx.camera:camera-core:$cameraxVersion")
implementation("androidx.camera:camera-camera2:$cameraxVersion")
implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
implementation("androidx.camera:camera-view:$cameraxVersion")

// ML Kit Object Classification
implementation("com.google.mlkit:image-labeling:17.0.9")

// Location Services
implementation("com.google.android.gms:play-services-location:21.0.1")
```

## Part 2 - Permission Requirements

- Starting with Android 6.0 (API 23), **dangerous permissions** (like Camera and Location) must be requested at runtime
- Our app needs:
  - **Camera** -> for CameraX preview
  - **Fine & Coarse Location** -> for GPS

```kotlin
private fun allPermissionsGranted(perms: Array<String>) =
    perms.all { ContextCompat.checkSelfPermission(context = this, permission = it) == PackageManager.PERMISSION_GRANTED }
```

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Request permissions

    val requiredPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    if (!allPermissionsGranted(perms = requiredPermissions)) {
        ActivityCompat.requestPermissions(activity = this, requiredPermissions, requestCode = 0)
    }
}
```

## Part 3 - Proximity Sensor Requirements

- The **proximity sensor** is usually located near the earpiece of the phone. It reports whether something is **NEAR** (covered) or **FAR** (uncovered)
- We use it to **pause/resume camera analysis**:
  - **NEAR** -> stop analyzing frames
  - **FAR** -> resume analyzing frames
- For demo purposes, we also show a **Toast** when state changes

```kotlin
override fun onSensorChanged(event: SensorEvent?) {
    if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
        val distance = event.values[0]
        pauseScanning.value = distance < event.sensor.maximumRange

        // Feedback for demo
        if (pauseScanning.value) {
            Toast.makeText(context = this, text = "Proximity: NEAR → Pausing scan", duration = Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context = this, text = "Proximity: FAR → Resuming scan", duration = Toast.LENGTH_SHORT).show()
        }
    }
}
```

## Part 4 - Location Requirements

- Use `FusedLocationProviderClient` to get location
- Request **continuous updates** every 2 seconds
- Display latitude & longitude below detection results

```kotlin
// Start continuous location updates
val fusedClient = LocationServices.getFusedLocationProviderClient(context)
LaunchedEffect(key1 = Unit) {
    val request = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, intervalMillis = 2000L
    ).build()

    fusedClient.requestLocationUpdates(p0 = request, p1 = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let {
                location = "Lat=${it.latitude}, Lng=${it.longitude}"
            }
        }
    }, p2 = Looper.getMainLooper())
}
```

## Part 5 - Camera + Object Classification Requirements

- Use **CameraX** for preview and `ImageAnalysis` for frame-by-frame analysis
- Wrap each frame into `InputImage`
- Process with **ML Kit ImageLabeler**
- Show top 3 labels with confidence scores
- If scanning is paused (proximity = NEAR), skip analysis

```kotlin
val analyzer = ImageAnalysis.Builder()
    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
    .build()
    .also {
        it.setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
            if (MainActivity.pauseScanning.value) {
                detected = "Scanning paused"
                imageProxy.close()
                return@setAnalyzer
            }

            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val image = InputImage.fromMediaImage(
                    mediaImage,
                    rotationDegrees = imageProxy.imageInfo.rotationDegrees
                )
                labeler.process(p0 = image)
                    .addOnSuccessListener { labels ->
                        detected = labels.take(n = 3).joinToString(separator = "\n") { l ->
                            "${l.text} (${String.format("%.2f", l.confidence)})"
                        }
                    }
                    .addOnCompleteListener { imageProxy.close() }
            } else {
                imageProxy.close()
            }
        }
    }
```

## Part 6 - UI Requirements

- Top half: camera preview
- Bottom half: white panel showing detected objects and location
- If scanning is paused -> display "Scanning paused" message

```kotlin
Column(Modifier.fillMaxSize()) {
    // Top: Camera Preview
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight = 1f),
        factory = { ... }
    )

    // Bottom: Results panel
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight = 0.5f),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Detected Objects:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(height = 8.dp))
            Text(text = detected)

            Spacer(Modifier.height(height = 16.dp))
            Text(text = "Location: $location", color = MaterialTheme.colorScheme.primary)
        }
    }
}
```
