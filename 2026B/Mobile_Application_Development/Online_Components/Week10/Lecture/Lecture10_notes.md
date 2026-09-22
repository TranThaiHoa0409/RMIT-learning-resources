# Lecture10 — Text Recognition Demo (Notes)

## 1. Tổng quan

Demo trong lớp về CameraX + ML Kit **Text Recognition** (nhận diện text từ camera preview theo thời gian thực), có kèm một đoạn code cảm biến khoảng cách (proximity sensor) chỉ để log, và một composable `CameraPreview()` cũ không còn được dùng (đã comment lại trong `setContent`). Toàn bộ nằm trong 1 file `MainActivity.kt`, không tách theo MVVM.

Package: `com.example.lecture10`. `minSdk 24`, `targetSdk/compileSdk 36`.

## 2. `MainActivity.kt` (file custom duy nhất)

- **Xin quyền runtime**: check + request `Manifest.permission.CAMERA` nếu chưa cấp (không xử lý callback kết quả).
- **Proximity sensor**: implement `SensorEventListener`, đăng ký `Sensor.TYPE_PROXIMITY` ở `onResume()`, hủy ở `onPause()`. `onSensorChanged` chỉ `Log.d` "Proximity: NEAR/FAR" — **không có tác dụng gì lên UI** (khác với Tutorial10, ở đây sensor chỉ để demo/log, không dùng để pause scan).
- `setContent`: gọi `TextRecognitionScreen()`; dòng gọi `CameraPreview()` bị **comment lại** — để tham khảo cách làm preview đơn giản trước khi thêm phần phân tích ảnh.

- **`CameraPreview()`** *(composable không dùng, đã comment gọi)*: bản preview camera đơn giản nhất — chỉ bind `Preview` use case vào `PreviewView` qua `ProcessCameraProvider`, chưa có `ImageAnalysis`.

- **`TextRecognitionScreen()`** *(composable chính đang dùng)*:
  - Tạo `PreviewView` qua `AndroidView`, lấy `ProcessCameraProvider`.
  - Tạo `TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)` (ML Kit, bộ nhận diện chữ Latin mặc định).
  - Build `ImageAnalysis` (`STRATEGY_KEEP_ONLY_LATEST`), analyzer chạy trên thread riêng (`Executors.newSingleThreadExecutor()`): convert `imageProxy` → `InputImage`, chạy qua `recognizer`, cập nhật state `detectedText` bằng text nhận diện được (hoặc "No text detected" nếu rỗng).
  - Bind `preview` + `analyzer` vào lifecycle (`cameraProvider.bindToLifecycle`), camera sau (`DEFAULT_BACK_CAMERA`).
  - UI: preview cao 400dp phía trên, `Text(detectedText)` hiển thị bên dưới.

## 3. `AndroidManifest.xml` (đã chỉnh so với mặc định)

- Thêm `<uses-feature android:name="android.hardware.camera" android:required="false" />`.
- Thêm permission `CAMERA`.
- Không khai báo Service/Receiver. Phần còn lại (theme, icon, activity launcher) là mặc định AS sinh ra.

## 4. Dependency thêm ngoài mặc định

So với template Compose "Empty Activity" chuẩn:

**`app/build.gradle.kts`:**

```kotlin
val camerax_version = "1.3.0"
implementation("androidx.camera:camera-core:$camerax_version")
implementation("androidx.camera:camera-camera2:$camerax_version")   // comment "REQUIRED" trong code gốc

implementation(libs.androidx.camera.view)        // qua version catalog, version 1.4.2 — khác version với camera-core/camera2 (1.3.0) ở trên, nên rà lại cho đồng bộ
implementation(libs.androidx.camera.lifecycle)   // qua version catalog, version 1.4.2

implementation("com.google.mlkit:text-recognition:16.0.0")
```

**`gradle/libs.versions.toml`** — version + library entries thêm ngoài mặc định:

```toml
[versions]
cameraView = "1.4.2"
cameraLifecycle = "1.4.2"

[libraries]
androidx-camera-view = { group = "androidx.camera", name = "camera-view", version.ref = "cameraView" }
androidx-camera-lifecycle = { group = "androidx.camera", name = "camera-lifecycle", version.ref = "cameraLifecycle" }
```

**Lưu ý riêng:** các dependency CameraX đang bị khai báo ở 2 nơi khác nhau với 2 version khác nhau — `camera-core`/`camera-camera2` hardcode `1.3.0`, còn `camera-view`/`camera-lifecycle` qua catalog là `1.4.2`. Nên thống nhất lại một version duy nhất (và có thể đưa `camera-core`/`camera-camera2` vào version catalog luôn cho gọn) khi làm lại từ đầu.
