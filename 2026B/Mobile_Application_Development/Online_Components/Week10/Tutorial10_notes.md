# Tutorial10 — Smart Scanner App (Notes)

## 1. Tổng quan

App demo "Smart Scanner": dùng CameraX để lấy camera preview, chạy ML Kit Image Labeling để nhận diện vật thể theo thời gian thực, kết hợp cảm biến khoảng cách (proximity sensor) để tự động pause/resume việc quét, và song song lấy vị trí GPS hiển thị lên màn hình. Toàn bộ nằm trong 1 `Activity` + 1 composable màn hình, chưa tách theo MVVM/data-domain-presentation.

Package: `com.example.tutorial10`. `minSdk 24`, `targetSdk/compileSdk 36`.

## 2. `MainActivity.kt`

- **Xin quyền runtime**: request 1 lượt 3 permission — `CAMERA`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION` (dùng `ActivityCompat.requestPermissions`, không xử lý callback kết quả).
- **Proximity sensor**: implement `SensorEventListener`, đăng ký `Sensor.TYPE_PROXIMITY` ở `onResume()`, hủy đăng ký ở `onPause()`. Khi `onSensorChanged` nhận event proximity: nếu khoảng cách nhỏ hơn `maximumRange` của sensor (vật ở gần) → set `pauseScanning.value = true` (dừng quét), ngược lại → `false` (tiếp tục quét). Có `Toast` demo phản hồi trạng thái.
- **`pauseScanning`**: `mutableStateOf<Boolean>` đặt trong `companion object` — dùng làm state global để `SmartScannerScreen` đọc và quyết định có xử lý frame camera hay không.
- Khởi tạo `SensorManager` và `FusedLocationProviderClient` (client location được tạo ở đây nhưng không dùng trực tiếp — `SmartScannerScreen` tự tạo client riêng của nó).
- `setContent` chỉ gọi `SmartScannerScreen()`.

## 3. `SmartScannerScreen.kt`

Composable chính, gồm 2 phần: camera preview (nửa trên) + panel kết quả (nửa dưới).

- **Location**: tạo `FusedLocationProviderClient` riêng, trong `LaunchedEffect(Unit)` request location update liên tục (`LocationRequest` với `PRIORITY_HIGH_ACCURACY`, interval 2000ms), callback cập nhật state `location` (lat/lng) hiển thị ở panel dưới.
- **Camera preview + phân tích ảnh**: dùng `AndroidView` nhúng `PreviewView` (CameraX). Trong `factory`:
  - Lấy `ProcessCameraProvider`, build `Preview` gắn `surfaceProvider` vào `PreviewView`.
  - Tạo `ImageLabeling` client (`ImageLabelerOptions.DEFAULT_OPTIONS`).
  - Build `ImageAnalysis` (`STRATEGY_KEEP_ONLY_LATEST`), set analyzer chạy trên 1 thread riêng (`Executors.newSingleThreadExecutor()`): nếu `MainActivity.pauseScanning.value == true` thì bỏ qua frame (hiện "Scanning paused"); ngược lại convert `imageProxy` thành `InputImage`, chạy qua labeler, lấy top 3 label kèm confidence hiển thị vào state `detected`.
  - Bind `preview` + `analyzer` vào lifecycle qua `cameraProvider.bindToLifecycle(...)`, camera sau (`DEFAULT_BACK_CAMERA`).
- **UI kết quả**: hiển thị danh sách object detect được (`detected`) và vị trí hiện tại (`location`).

## 4. `AndroidManifest.xml` (đã chỉnh so với mặc định)

- Thêm `<uses-feature android:name="android.hardware.camera" android:required="false" />`.
- Thêm 3 permission: `CAMERA`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`.
- Không khai báo Service/Receiver nào thêm. Phần còn lại (theme, icon, activity launcher) là mặc định AS sinh ra.

## 5. Dependency thêm ngoài mặc định

So với template Compose "Empty Activity" chuẩn, project thêm các dependency sau:

**`app/build.gradle.kts`:**

```kotlin
implementation(libs.image.labeling.common)   // ML Kit image-labeling-common 18.1.0, qua version catalog — nhưng không thấy dùng trực tiếp API của lib này trong code
implementation(libs.material3)               // material3 1.3.2 qua catalog — trùng mục đích với libs.androidx.material3 mặc định, nên kiểm tra lại có dư thừa không

val cameraxVersion = "1.3.0"
implementation("androidx.camera:camera-core:$cameraxVersion")
implementation("androidx.camera:camera-camera2:$cameraxVersion")
implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
implementation("androidx.camera:camera-view:$cameraxVersion")

implementation("com.google.mlkit:image-labeling:17.0.9")   // ML Kit thật sự được dùng trong code (ImageLabeling.getClient) — hardcode version, khác với libs.image.labeling.common ở trên (2 artifact ML Kit khác nhau, nên rà lại)

implementation("com.google.android.gms:play-services-location:21.0.1")
```

**`gradle/libs.versions.toml`** — version + library entries thêm ngoài mặc định:

```toml
[versions]
imageLabelingCommon = "18.1.0"
material3 = "1.3.2"

[libraries]
image-labeling-common = { group = "com.google.mlkit", name = "image-labeling-common", version.ref = "imageLabelingCommon" }
material3 = { group = "androidx.compose.material3", name = "material3", version.ref = "material3" }
```

**Lưu ý riêng:** `libs.image.labeling.common` và `libs.material3` được khai báo qua version catalog nhưng đây là 2 dependency có vẻ dư/trùng với dependency hardcode `com.google.mlkit:image-labeling:17.0.9` và `libs.androidx.material3` mặc định — nên đối chiếu lại với đề bài tutorial xem có cần giữ cả 2 hay chỉ 1 bộ là đủ.
