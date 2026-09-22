# Lecture05 — Project Notes

Ứng dụng demo Google Maps Compose đơn giản, minh hoạ 4 tính năng rời rạc thay vì 1 luồng hoàn chỉnh: hiển thị bản đồ cơ bản, marker/polyline tuỳ chỉnh, xin quyền vị trí runtime, và lấy/theo dõi vị trí người dùng. Project KHÔNG theo kiến trúc data/domain/presentation như Tutorial05 — toàn bộ nằm trong 3 file Kotlin ở package gốc `com.example.googlemapsexample`, các composable được viết độc lập và chọn 1 cái để chạy bằng cách comment/uncomment trong `MainActivity.setContent`.

## 1. Tổng quan kiến trúc

```
MainActivity.setContent { ... }
   │  (chỉ 1 dòng được uncomment tại 1 thời điểm)
   ├─▶ MapScreen()                     — bản đồ tĩnh, 1 marker cố định
   ├─▶ AdvancedMapFeaturesDemo()       — marker icon tuỳ chỉnh + polyline + click event
   ├─▶ PermissionRequestScreen()       — xin quyền, khi granted tự gọi AdvancedMapFeaturesDemo()
   ├─▶ LocationMapScreen()             — lấy vị trí 1 lần qua nút bấm
   └─▶ ContinuousLocationMapScreen()   — theo dõi vị trí liên tục (đang được chọn chạy)
```

Không có ViewModel, không có dependency injection, không gọi network — toàn bộ state (`currentLocation`, `mapType`...) là `remember { mutableStateOf() }` cục bộ trong từng composable. Đây là các ví dụ độc lập để minh hoạ từng API riêng lẻ (Maps Compose, Accompanist Permissions, FusedLocationProviderClient), không phải 1 app hoàn chỉnh.

## 2. Vai trò từng file

| File | Vai trò |
|---|---|
| `MainActivity.kt` | Entry point. Chứa `MapScreen()` (bản đồ tĩnh, camera đặt tại RMIT Vietnam, 1 `Marker`) và `AdvancedMapFeaturesDemo()` (camera animate tới San Francisco, `MapType.SATELLITE`, marker icon tuỳ chỉnh qua `bitmapDescriptorFromVector()`, `onClick` marker in log, vẽ `Polyline` 3 điểm). Hàm top-level `bitmapDescriptorFromVector()` convert vector drawable → `BitmapDescriptor` để dùng làm icon marker. |
| `PermissionRequestScreen.kt` | Composable xin quyền `ACCESS_FINE_LOCATION`/`ACCESS_COARSE_LOCATION` bằng Accompanist Permissions (`rememberMultiplePermissionsState`). Xử lý 3 nhánh `when`: đã granted (hiện Toast + gọi thẳng `AdvancedMapFeaturesDemo()`), `shouldShowRationale` (hiện lý do + nút xin lại), còn lại (nút "Request Permissions"). |
| `LocationMapScreen.kt` | 2 composable + 1 hàm suspend: `LocationMapScreen()` — nút "Show My Location" gọi `getLastKnownLocation()` (suspend, wrap callback `FusedLocationProviderClient.lastLocation` bằng `suspendCancellableCoroutine`) rồi animate camera tới vị trí đó. `ContinuousLocationMapScreen()` — dùng `LocationRequest` (interval 5s, min update 3s, `PRIORITY_HIGH_ACCURACY`) + `LocationCallback` để nhận vị trí liên tục; `LaunchedEffect` bắt đầu `requestLocationUpdates()` khi quyền được cấp, `DisposableEffect` gọi `removeLocationUpdates()` khi composable rời màn hình (tránh leak location updates). |
| `res/drawable/restaurant_marker.xml` | Icon vector tuỳ chỉnh (tint đen) dùng làm marker icon trong `AdvancedMapFeaturesDemo`. |

`ui/theme/Color.kt`, `Theme.kt`, `Type.kt` giữ nguyên theme Compose mặc định (Purple/Pink), không custom.

## 3. AndroidManifest — phần custom thêm

So với manifest mặc định Android Studio sinh ra, project đã thêm:

- 2 `<uses-permission>`: `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`.
- `<meta-data android:name="com.google.android.geo.API_KEY" .../>` — API key Google Maps, đang hardcode trực tiếp trong manifest.
- Attribute `android:windowSoftInputMode="adjustResize"` trên `<activity>`.

## 4. Dependency thêm ngoài mặc định

Template Android Studio (Empty Activity + Compose) mặc định chỉ có: `core-ktx`, `lifecycle-runtime-ktx`, `activity-compose`, `compose-bom`, `ui`, `ui-graphics`, `ui-tooling(-preview)`, `material3`, `junit`, `androidx-junit`, `espresso-core`, `ui-test-junit4`, `ui-test-manifest`. Ngoài các dòng đó, `gradle/libs.versions.toml` không có entry mới nào — toàn bộ khai trực tiếp dạng string literal trong `app/build.gradle.kts`:

```kotlin
implementation("com.google.maps.android:maps-compose:4.3.3")
// Google Play Services Maps
implementation("com.google.android.gms:play-services-maps:19.0.0")

implementation("com.google.accompanist:accompanist-permissions:0.37.3")

implementation("com.google.android.gms:play-services-location:21.3.0")
```
