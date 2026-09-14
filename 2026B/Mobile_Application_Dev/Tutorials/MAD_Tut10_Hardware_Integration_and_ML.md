# MAD Tutorial 10 — Hardware Integration and Machine Learning

**Môn học:** COSC2657/COSC2543/COSC2729 — Android Development

## Objectives
Xây dựng một app **Smart Scanner** thể hiện:
- Dùng **Proximity Sensor** để pause/resume scanning
- Lấy **vị trí thiết bị** (GPS) bằng Fused Location Provider
- Hiển thị **CameraX preview**
- Chạy **ML Kit Object Classification** theo thời gian thực
- Hiển thị object detected + location trên **Jetpack Compose UI**

---

## Part 1 — Setup Requirements

### Permissions trong `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

### Dependencies trong `build.gradle (app)`:
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

---

## Part 2 — Permission Requirements

- Từ Android 6.0 (API 23), **dangerous permissions** (như Camera và Location) phải được yêu cầu tại **runtime**.
- App cần:
  - **Camera** → cho CameraX preview
  - **Fine & Coarse Location** → cho GPS

```kotlin
private fun allPermissionsGranted(perms: Array<String>) =
    perms.all { ContextCompat.checkSelfPermission(context = this, permission = it) == PackageManager.PERMISSION_GRANTED }

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

---

## Part 3 — Proximity Sensor Requirements

- **Proximity sensor** thường nằm gần loa thoại của điện thoại. Nó báo cáo trạng thái **NEAR** (bị che) hoặc **FAR** (không bị che).
- Dùng nó để **pause/resume camera analysis**:
  - **NEAR** → dừng phân tích frame
  - **FAR** → tiếp tục phân tích frame
- Để demo, hiển thị **Toast** khi trạng thái thay đổi.

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

---

## Part 4 — Location Requirements

- Dùng `FusedLocationProviderClient` để lấy location.
- Yêu cầu **continuous updates** mỗi 2 giây.
- Hiển thị latitude & longitude bên dưới kết quả detection.

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

---

## Part 5 — Camera + Object Classification Requirements

- Dùng **CameraX** cho preview và **ImageAnalysis** cho phân tích theo từng frame.
- Wrap mỗi frame vào `InputImage`.
- Xử lý bằng **ML Kit ImageLabeler**.
- Hiển thị **top 3 labels** kèm confidence score.
- Nếu scanning đang paused (proximity = NEAR), bỏ qua việc phân tích.

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

---

## Part 6 — UI Requirements

- **Nửa trên:** camera preview
- **Nửa dưới:** panel trắng hiển thị object detected và location
- Nếu scanning đang paused → hiển thị message "Scanning paused"

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

---

## Tóm tắt luồng hoạt động của app Smart Scanner

1. App request permissions (Camera + Location) tại runtime.
2. Đăng ký `SensorManager` để lắng nghe proximity sensor.
3. Bind CameraX (`Preview` + `ImageAnalysis`) vào lifecycle.
4. Với mỗi frame từ `ImageAnalysis`:
   - Nếu `pauseScanning == true` (proximity NEAR) → bỏ qua, hiển thị "Scanning paused".
   - Ngược lại → convert frame thành `InputImage`, đưa vào ML Kit `ImageLabeler`, lấy top 3 label + confidence.
5. Song song, `FusedLocationProviderClient` liên tục cập nhật location mỗi 2 giây.
6. UI Compose chia 2 nửa: camera preview (trên) và kết quả detection + location (dưới).

## Liên hệ với lý thuyết Week 10
- Minh họa kết hợp 3 mảng đã học: **Sensor** (proximity), **Camera** (CameraX), và **ML Kit** (Image Labeling) trong cùng một app thực tế.
- Location dùng thêm `FusedLocationProviderClient` (không nằm trong slide lý thuyết Week 10 nhưng liên quan tới Location & Maps đã học ở Week 5).
