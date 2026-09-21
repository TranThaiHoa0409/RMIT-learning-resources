# Tutorial05 — Project Notes

Ứng dụng bản đồ tương tác (Google Maps Compose) hiển thị vị trí hiện tại của người dùng và danh sách nhà hàng (restaurant) lấy từ một backend REST cục bộ (`10.0.2.2` — localhost khi chạy trên Android Emulator). Kiến trúc theo **Clean Architecture 3 lớp** kết hợp **MVVM**: `domain` (business logic thuần, không phụ thuộc Android/Compose) → `data` (triển khai cụ thể: network, location) → `presentation` (ViewModel + UI Compose).

## 1. Tổng quan kiến trúc

```
presentation (UI + ViewModel)
        │  gọi qua interface
        ▼
domain (Restaurant, RestaurantRepository, LocationTracker, UseCase)
        ▲  implement interface
        │
data (RestaurantRepositoryImpl, DefaultLocationTracker, ApiClient/Retrofit)
```

Luồng dữ liệu chính: `MapViewModel` gọi `GetRestaurantsUseCase` / `AddRestaurantUseCase` (domain) → các use case gọi `RestaurantRepository` (interface, domain) → được triển khai bởi `RestaurantRepositoryImpl` (data) → gọi `RestaurantApi` qua Retrofit (`ApiClient`) → dữ liệu trả về dạng `RestaurantDto` được map sang `Restaurant` (domain model) bằng extension function `toDomain()`/`toDto()`.

Vị trí người dùng: `MapViewModel` cũng dùng `LocationTracker` (interface, domain), triển khai bởi `DefaultLocationTracker` (data, dùng `FusedLocationProviderClient` của Google Play Services) để lấy stream `Flow<LatLng>` cập nhật vị trí realtime.

Dependency giữa các lớp được nối thủ công (manual DI, không dùng Hilt/Koin) trong `MapViewModelFactory`.

## 2. Domain layer

Chứa model và interface thuần Kotlin, không import Android framework (trừ `LatLng` của Google Maps trong `LocationTracker`, do đây là kiểu dữ liệu vị trí dùng xuyên suốt app).

| File | Vai trò |
|---|---|
| `domain/model/Restaurant.kt` | Data class domain model: `id`, `name`, `lat`, `lng`. |
| `domain/repository/RestaurantRepository.kt` | Interface định nghĩa contract `getRestaurants()` / `addRestaurant()`, trả về `Result<T>` để xử lý lỗi. |
| `domain/location/LocationTracker.kt` | Interface trả về `Flow<LatLng>` — trừu tượng hoá nguồn vị trí, tách khỏi chi tiết Google Play Services. |
| `domain/usecase/GetRestaurantsUseCase.kt` | Use case gọi `repository.getRestaurants()`, dùng `operator fun invoke()` để gọi như hàm. |
| `domain/usecase/AddRestaurantUseCase.kt` | Use case gọi `repository.addRestaurant()`, tương tự `operator invoke`. |

## 3. Data layer

Triển khai cụ thể cho các interface ở domain, chứa logic network và vị trí thực tế.

| File | Vai trò |
|---|---|
| `data/model/RestaurantDto.kt` | DTO khớp JSON từ API (`id`, `name`, `lat`, `lng`), kèm 2 extension function `toDomain()` / `toDto()` để map qua lại với `Restaurant`. |
| `data/network/RestaurantApi.kt` | Interface Retrofit khai báo 2 endpoint: `GET restaurants`, `POST restaurants`. |
| `data/network/ApiClient.kt` | Singleton object khởi tạo `OkHttpClient` (timeout 30s) + `Retrofit` với base URL `http://10.0.2.2:3004/` và `GsonConverterFactory`; expose `api: RestaurantApi`. |
| `data/repository/RestaurantRepositoryImpl.kt` | Implement `RestaurantRepository`, gọi `ApiClient.api`, bọc try/catch trả `Result.success`/`Result.failure`. |
| `data/location/DefaultLocationTracker.kt` | Implement `LocationTracker` bằng `FusedLocationProviderClient`; dùng `callbackFlow` để convert callback location updates (interval 3s, min update 1s, high accuracy) thành `Flow<LatLng>`. |

## 4. Presentation layer

| File | Vai trò |
|---|---|
| `presentation/viewmodel/MapViewModel.kt` | `ViewModel` giữ state `restaurants` (`mutableStateListOf`) và `currentLocation` (`mutableStateOf`). `init{}` tự gọi `loadRestaurants()` + `startLocationUpdates()`. Có `loadRestaurants()` và `addRestaurant()` chạy trên `Dispatchers.IO`, cập nhật UI state qua `Dispatchers.Main`. `startLocationUpdates()` collect Flow từ `LocationTracker`. |
| `presentation/viewmodel/MapViewModelFactory.kt` | `ViewModelProvider.Factory` — nơi wiring thủ công: khởi tạo `RestaurantRepositoryImpl`, `DefaultLocationTracker`, 2 use case, rồi inject vào `MapViewModel`. Đây là điểm composition root của toàn bộ dependency graph. |
| `presentation/ui/InteractiveMapScreen.kt` | Composable chính: lấy `MapViewModel` qua `ViewModelProvider`, hiển thị `GoogleMap` (Marker cho vị trí user màu xanh + Marker cho từng restaurant), `Scaffold` với `FloatingActionButton` để refresh danh sách. `onMapClick` trên bản đồ sẽ gọi `viewModel.addRestaurant()` với tên random (hàm `generateRandomName()` cuối file — tiện ích test nhanh, không phải domain logic). |
| `presentation/ui/components/RequestLocationPermission.kt` | Composable xin quyền `ACCESS_FINE_LOCATION` bằng thư viện Accompanist Permissions; hiển thị UI fallback (text + nút "Request Permission") nếu chưa được cấp quyền, chỉ render `onPermissionGranted()` khi đã granted. |

## 5. MainActivity

`MainActivity.kt` đã được sửa khỏi template mặc định: thay vì gọi `Greeting()` mặc định, entry point gọi `Tutorial05Theme { RequestLocationPermission { InteractiveMapScreen() } }` — nghĩa là màn hình bản đồ chỉ hiển thị sau khi xin quyền vị trí thành công.

## 6. AndroidManifest — phần custom thêm

So với manifest mặc định Android Studio sinh ra, project đã thêm:

- 3 `<uses-permission>`: `INTERNET`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`.
- `<meta-data android:name="com.google.android.geo.API_KEY" .../>` — API key Google Maps (đang hardcode trực tiếp trong manifest).
- Attribute `android:networkSecurityConfig="@xml/network_security_config"` trên thẻ `<application>`.

## 7. File mới thêm hoàn toàn (không có trong template mặc định)

`res/xml/network_security_config.xml` — cho phép cleartext traffic (HTTP, không phải HTTPS) tới domain `10.0.2.2` (địa chỉ localhost khi Android Emulator gọi về máy host) — cần thiết vì `ApiClient` gọi `http://10.0.2.2:3004/` chứ không phải `https://`.

## 8. Dependency thêm ngoài mặc định

Template Android Studio (Empty Activity + Compose) mặc định chỉ có: `core-ktx`, `lifecycle-runtime-ktx`, `lifecycle-viewmodel-compose`, `activity-compose`, `compose-bom`, `ui`, `ui-graphics`, `ui-tooling(-preview)`, `material3`, `junit`, `androidx-junit`, `espresso-core`, `ui-test-junit4`, `ui-test-manifest`. Ngoài các dòng đó, project đã thêm:

**Trong `gradle/libs.versions.toml`:**

```toml
[versions]
playServicesMaps = "19.2.0"

[libraries]
play-services-maps = { group = "com.google.android.gms", name = "play-services-maps", version.ref = "playServicesMaps" }
```

**Trong `app/build.gradle.kts` (khối `dependencies`):**

```kotlin
implementation(libs.play.services.maps)

// Google Maps Compose
implementation("com.google.maps.android:maps-compose:6.7.0")
// Location Services
implementation("com.google.android.gms:play-services-location:21.3.0")
// Accompanist Permissions
implementation("com.google.accompanist:accompanist-permissions:0.32.0")
// Retrofit & Coroutines
implementation("com.squareup.retrofit2:retrofit:3.0.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
```

Ghi chú: `OkHttpClient` dùng trong `ApiClient.kt` không được khai báo dependency riêng — đây là transitive dependency đi kèm `retrofit2`, không cần thêm dòng riêng.
