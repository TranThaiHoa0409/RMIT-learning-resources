# HANDOFF — Gingerbread PoD Manager (RMIT MAD · Assignment 2 · Option 3)

## 0. Bố cục

Tổng file trong repo: 153 file (135 file text + 18 file nhị phân). File handoff này **mô tả** repo (cấu trúc, flow, class/fun/composable) — không chứa nguyên văn source

- **§1 Tổng quan dự án**: thông tin chung, thành viên & phân công, yêu cầu đề bài và mức đáp ứng, rubric.
- **§2 Tech stack & cấu hình build**: bảng phiên bản thư viện, cấu hình build đặc biệt, điểm chính trong AndroidManifest, theme & branding.
- **§3 Kiến trúc**: nguyên tắc MVVM + Clean Architecture, phụ thuộc chéo giữa các feature, cây package, danh sách Hilt module.
- **§4 Dữ liệu & lưu trữ**: schema Room (bảng, DAO, quan hệ), DataStore Preferences, cấu trúc file lưu trên máy, dữ liệu mock của Manifest.
- **§5 Flow đã triển khai**: mô tả chi tiết từng luồng theo code hiện tại — khởi động app, Login/phân quyền, UC-01 đến UC-04, và luồng demo end-to-end.
- **§6 Lịch sử triển khai & các thay đổi trong quá trình làm**: tóm tắt từng sprint (0–3), thay đổi so với kế hoạch gốc.
- **§7 Quyết định đã chốt**: bảng các quyết định kỹ thuật/thiết kế đã thống nhất, kèm nguồn (sprint nào).
- **§8 Bug đã sửa & root cause**: bảng bug quan trọng đã gặp, nguyên nhân gốc, cách fix — bài học để tránh lặp lại.
- **§9 Những điểm QUAN TRỌNG cần lưu ý**: các cảnh báo, rủi ro, chỗ dễ gây lỗi nếu không biết, gồm cả phần chưa xác minh.
- **§10 Test**: danh sách file test, loại test, số lượng, phạm vi; lệnh chạy test.
- **§11 Giới hạn đã biết & việc chưa làm**: những gì cố ý không làm hoặc chưa làm, và ý tưởng tương lai chưa quyết định.
- **§12**: hướng dẫn dựng lại project từ source (repo GitHub hoặc zip) + bước kiểm tra.
- **§13**: cây repo tổng thể (file cấu hình, resource, nhị phân).
- **§14**: cây source Kotlin chi tiết — mỗi file liệt kê class, fun, @Composable, field của data class.

---

## 1. Tổng quan dự án

### 1.1 Thông tin chung

| Mục | Giá trị |
|---|---|
| Tên app (hiển thị) | `Gingerbread PoD` |
| Tên project | Gingerbread PoD Manager (`rootProject.name = "gingerbread_PoDManager"`) |
| Package / applicationId | `com.example.gingerbread_podmanager` |
| Môn | COSC2657 / COSC2543 / COSC2729 — Mobile Application Development (RMIT Vietnam) |
| Bài | Assignment 2 — Option 3: Asset Delivery & Proof-of-Delivery (PoD) App |
| Khách hàng (bối cảnh đề) | Phuong Hai JSC — giao thiết bị/vật tư kỹ thuật tới công trường |
| File nộp | File `.zip`, phải có `.apk` + `Readme.txt` + video demo ≤ 12 phút |
| Repo | Private: `RMIT-Vietnam-Teaching/assignment-2-group-gingerbread-mad` |
| Video demo | `https://youtu.be/9v506T4fR0c` |
| Google Cloud (Maps) | Project `gingerbread-podmanager-2026b` |

### 1.2 Thành viên

| Member | Student ID | Họ tên | Phụ trách |
|---|---|---|---|
| A | S3988393 | Bui Viet Anh | UC-01 Manifest & Map + OCR |
| B | S3878631 | Nguyen Minh Quang | UC-02 Live Tracking (Foreground Service) |
| C | S4020222 | Phung Minh Quang | UC-03 PoD Capture |
| D | S4027751 | Tran Thai Hoa | UC-04 Sync & Dashboard + tích hợp, review, Sprint 3 |

### 1.3 Yêu cầu đề bài

- **UC-01:** Google Maps + custom pin, xem chi tiết đơn, OCR ML Kit quét tracking number.
- **UC-02:** "Start Delivery Route" → Foreground Service GPS + notification đích đến; sống qua lifecycle.
- **UC-03:** Chữ ký bằng Custom Compose Canvas, ảnh proof, GPS + timestamp tự động, lưu Room.
- **UC-04:** WorkManager sync offline, BroadcastReceiver mạng, Dashboard dispatcher, xuất PDF. Upload hiện tại là stub.
- **Architecture:** MVVM + Clean Architecture + Compose + Navigation Component.
- **Dependency Injection:** Hilt DI cho ViewModel, Repository, Database và Network client.
- **Tính năng sáng tạo:** OCR reconciliation `WrongStopMatch` / `NotFoundInManifest` (USP) + Login chia role.
- **Testing:** 36 Unit test JVM + 2 Compose UI test.

### 1.4 Rubric

| Tiêu chí | Điểm |
|---|---|
| App usefulness & creativeness | 40 |
| App completion & functionality | 30 |
| Non-functionality (UI/UX, quality, professionalism, testing) | 20 |
| Demo (phải nêu USP) | 10 |

---

## 2. Tech stack & cấu hình build

### 2.1 Phiên bản (từ `gradle/libs.versions.toml`)

| Thành phần | Version | Ghi chú |
|---|---|---|
| Gradle wrapper | 9.7.1 | `gradle-wrapper.properties` |
| JDK toolchain (Gradle daemon) | 21 | `gradle/gradle-daemon-jvm.properties`, plugin `foojay-resolver-convention 1.0.0` |
| AGP | 9.2.1 | ⚠️ khoá — bị giới hạn bởi phiên bản Android Studio cài trên máy |
| Kotlin | 2.4.10 | ⚠️ khoá |
| KSP | 2.3.11 | ⚠️ khoá |
| Hilt | 2.60.1 | ⚠️ khoá |
| androidx.hilt (navigation-compose, hilt-work, hilt-compiler) | 1.4.0 | cả 3 dùng chung `version.ref = "hiltNavigationCompose"` |
| Compose BOM | 2026.08.00 | ⚠️ khoá |
| Room | 2.8.4 | ⚠️ khoá — dùng `@Upsert` |
| Maps Compose (+ utils) | 8.5.0 | bắt buộc cho `ClusterItem` code của A |
| Navigation Compose | 2.9.8 | NavHost truyền thống, không phải Navigation3 |
| play-services-location | 21.3.0 | |
| Accompanist Permissions | 0.37.2 | |
| ML Kit Text Recognition (`play-services-mlkit-text-recognition`) | 19.0.1 | |
| CameraX | 1.6.2 | đổi từ 1.5.3 ở Sprint 2 (leaf dependency) |
| WorkManager | 2.10.0 | |
| core-ktx | 1.19.0 | lý do nâng `compileSdk` 36 → 37 ở Sprint 0 |
| lifecycle-runtime-ktx | 2.11.0 | |
| activity-compose | 1.13.0 | |
| core-splashscreen | 1.2.0 | Sprint 3 |
| exifinterface | 1.4.1 | Sprint 3 — xoay ảnh theo EXIF khi xuất PDF |
| datastore-preferences | 1.1.7 | Sprint 3 — lưu role |
| JUnit / androidx-junit / espresso | 4.13.2 / 1.3.0 / 3.7.0 | |

### 2.2 Cấu hình đặc biệt cần nhớ

- `compileSdk { version = release(37) }` — cú pháp AGP 9 (khác `compileSdk = 37` cũ). `targetSdk = 36`, `minSdk = 24`, `versionCode = 1`, `versionName = "1.0"`.
- `compileOptions` Java 11.
- Plugins trong `app/build.gradle.kts`: `android.application`, `kotlin.compose`, `ksp`, `hilt` (không khai báo plugin `kotlin-android` riêng).
- ⚠️ `gradle.properties` có `android.disallowKotlinSourceSets=false` — workaround bắt buộc cho bug tương thích AGP 9 built-in Kotlin + KSP (tài liệu Sprint 0 ghi: GitHub issue #2729 của KSP). Xoá dòng này sẽ gãy build.
- `gradle.properties`: `org.gradle.configuration-cache=true`, `org.gradle.jvmargs=-Xmx2048m`.
- `buildTypes.release { optimization { enable = false } }` — tắt R8/minify.
- `MAPS_API_KEY` đọc từ `local.properties` → `manifestPlaceholders["MAPS_API_KEY"]` (fallback `""` nếu thiếu — app vẫn build, chỉ bản đồ không hiện).
- `app/src/main/keepRules/rules.keep` — file keep rules mặc định của template AGP 9 (chỉ comment).

### 2.3 `AndroidManifest.xml` — điểm chính

- Quyền: `INTERNET`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, `FOREGROUND_SERVICE`, `FOREGROUND_SERVICE_LOCATION`, `POST_NOTIFICATIONS`, `CAMERA`, `WRITE_EXTERNAL_STORAGE` (`maxSdkVersion="28"`, chỉ để ghi PDF vào Downloads ở API 24–28).
- `uses-feature android.hardware.camera required=false`.
- `MainActivity`: `launchMode="singleTop"` (để tap notification tái sử dụng Activity đang chạy), `theme="@style/Theme.App.Starting"` (splash), `windowSoftInputMode="adjustResize"`.
- `DeliveryTrackingService`: `exported=false`, `foregroundServiceType="location"`.
- `androidx.startup.InitializationProvider` được gỡ bằng `tools:node="remove"`, nghĩa là **toàn bộ AndroidX Startup provider bị loại bỏ**, không chỉ `WorkManagerInitializer`.
- Không có documentation hoặc comment giải thích lý do gỡ toàn bộ provider.
- Tuy nhiên, `WorkManager` được cấu hình thủ công trong `GingerbreadApp` thông qua `Configuration.Provider`, cho thấy app chủ động kiểm soát quá trình khởi tạo WorkManager.

### 2.4 Theme & branding

- `Gingerbread_PoDManagerTheme`: theo dark mode hệ thống, `dynamicColor = false` mặc định (giữ màu thương hiệu). Palette "Style 1" + font Google Fonts: Inter (title/display) và Outfit (body), cần `ui-text-google-fonts` + `res/values/font_certs.xml`.
- Splash: `Theme.App.Starting` với nền `@color/splash_background` = `#B45309`, `postSplashScreenTheme = Theme.Gingerbread_PoDManager`; gọi `installSplashScreen()` trước `super.onCreate()` trong `MainActivity`.
- Icon: adaptive icon, nền `#92400E` (`values/ic_launcher_background.xml`), foreground là ảnh logo sinh bằng Image Asset Studio.
- `drawable/ic_launcher_foreground.xml` (vector) vẫn được dùng làm small icon của notification tracking.

---

## 3. Kiến trúc

### 3.1 Nguyên tắc

- MVVM + Clean Architecture theo từng feature: `presentation → domain → data`. Domain thuần Kotlin (không import Android).
- Mỗi UC nằm trong 1 package `feature_*`; phần dùng chung đặt trong `core/`.
- ⚠️ **Ranh giới module:** feature KHÔNG được import data layer (DAO/Entity/Impl) của feature khác. Chỉ được phụ thuộc vào **domain interface/model** (Dependency Inversion). Vi phạm từng xảy ra và đã được sửa bằng cách chuyển `DeliveryDao`/`DeliveryEntity` sang `core/database/`.
- Business logic đặt trong use case, không trong Worker/Service (giữ unit-testable).
- Chỉ có **1** class `@Database` (`AppDatabase`) cho toàn app.

### 3.2 Phụ thuộc chéo giữa feature

| Feature | Import từ feature khác |
|---|---|
| `feature_manifest` | (không) — dùng `core.database.dao.DeliveryDao` |
| `feature_tracking` | (không) |
| `feature_pod` | `feature_tracking.domain.repository.LocationTrackingRepository` (interface) |
| `feature_sync` | `feature_pod.domain.model.Delivery`, `feature_pod.domain.repository.PodRepository`, `feature_tracking.domain.model.TrackedLocation`, `feature_tracking.domain.repository.LocationTrackingRepository` |
| `feature_auth` | (không) — dùng `core.domain.model.UserRole` |

Ghi chú: `core/database/AppDatabase.kt` + `core/di/DatabaseModule.kt` import entity/DAO của `feature_pod` và `feature_sync` (để đăng ký vào database duy nhất) — đây là quy ước có chủ đích ghi trong docstring `AppDatabase`.

### 3.3 Cây package

```
com.example.gingerbread_podmanager/
├── GingerbreadApp.kt           @HiltAndroidApp + Configuration.Provider (HiltWorkerFactory)
├── MainActivity.kt             splash, periodic sync, NetworkStateReceiver, intent từ notification
├── navigation/NavGraph.kt      role gate + ShipperFlow + ManagerFlow + bottom nav
├── di/                         ManifestModule.kt, SessionModule.kt
├── core/                       
│   ├── database/AppDatabase.kt, dao/DeliveryDao.kt, entity/DeliveryEntity.kt
│   ├── di/DatabaseModule.kt, LocationModule.kt
│   ├── domain/model/DeliveryStatus.kt, UserRole.kt
│   └── theme/Color.kt, Theme.kt, Type.kt
├── feature_manifest/  
├── feature_tracking/  
├── feature_pod/       
├── feature_sync/      
└── feature_auth/      
```

### 3.4 Hilt modules

| Module | File | Cung cấp / bind |
|---|---|---|
| `DatabaseModule` (object) | `core/di/DatabaseModule.kt` | `AppDatabase` (@Singleton, `Room.databaseBuilder`, **không** Migration/fallback), 4 DAO |
| `LocationModule` (object) | `core/di/LocationModule.kt` | `FusedLocationProviderClient` @Singleton — dùng chung UC-02 + UC-03 |
| `ManifestBindingModule` / `ManifestUseCaseModule` | `di/ManifestModule.kt` | `ManifestRepository` ← `ManifestRepositoryImpl`; 5 use case manifest (@Provides) |
| `SessionDataStoreModule` / `SessionBindingModule` | `di/SessionModule.kt` | `DataStore<Preferences>` (tên `"session"`); `SessionRepository` ← `SessionRepositoryImpl` |
| `TrackingModule` | `feature_tracking/di/TrackingModule.kt` | `LocationTrackingRepository` ← `LocationTrackingRepositoryImpl` |
| `PodModule` | `feature_pod/di/PodModule.kt` | `PodRepository`, `LocationProvider` ← `TrackingAwareLocationProvider`, `SignatureImageStore` ← `FileSignatureImageStore`, `PhotoFileStore` ← `FilePhotoStore` |
| `SyncModule` | `feature_sync/di/SyncModule.kt` | `SyncRepository`, `DeliveryUploader` ← `StubDeliveryUploader`, `PdfGenerator` ← `AndroidPdfGenerator` |

---

## 4. Dữ liệu & lưu trữ

### 4.1 Room — `AppDatabase` (`gingerbread_podmanager.db`, `version = 1`, `exportSchema = false`)

| Bảng | Entity (package) | Cột | Quan hệ |
|---|---|---|---|
| `deliveries` | `DeliveryEntity` (`core.database.entity`) | `id` PK String, `recipientName`, `address`, `status` (tên enum `DeliveryStatus`), `createdAtEpochMillis` | cha |
| `signatures` | `SignatureEntity` (`feature_pod`) | `id` PK, `deliveryId` (index), `imageFilePath` (absolute), `capturedAtEpochMillis`, `latitude?`, `longitude?` | N–1 `deliveries`, `onDelete = CASCADE` |
| `photos` | `PhotoEntity` (`feature_pod`) | giống `signatures` | N–1 `deliveries`, `CASCADE` |
| `sync_queue` | `SyncQueueEntity` (`feature_sync`) | `id` PK, `deliveryId` (**unique** index), `status` (tên enum `SyncStatus`), `retryCount`, `lastAttemptEpochMillis?` | 1–1 `deliveries`, `CASCADE` |

DAO:
- `DeliveryDao`: `getById`, ⚠️ `@Upsert upsert` (xem §8 bug #1), `getByStatus`, `observeAll(): Flow`, `getAll`.
- `SignatureDao` / `PhotoDao`: `observeForDelivery(deliveryId)` **`ORDER BY capturedAtEpochMillis DESC`** (phần tử đầu = mới nhất — `ExportDeliveryCertificateUseCase` dựa vào điều này), `@Insert(REPLACE) insert`.
- `SyncQueueDao`: `getByDeliveryId`, `getByStatus`, `getAll`, `@Insert(REPLACE) upsert` (bảng lá, không có con nên REPLACE không gây cascade).

Enum dùng chung: `DeliveryStatus { PENDING, IN_TRANSIT, DELIVERED, FAILED }`. `SyncStatus { QUEUED, SYNCING, FAILED, DONE }`.

### 4.2 DataStore Preferences

- Tên file `"session"`, key `stringPreferencesKey("user_role")`, giá trị `UserRole.name` (`SHIPPER`/`MANAGER`).
- Đọc bằng `runCatching { UserRole.valueOf(stored) }.getOrNull()` → giá trị lạ trả `null` (về Login) thay vì crash.
- Tách hoàn toàn khỏi Room (role là session/UI state, và Room không có Migration nên không thêm bảng mới vào cuối kỳ).

### 4.3 File

| Loại | Vị trí | Tên file |
|---|---|---|
| Chữ ký PNG | `context.filesDir/pod/` (app-private) | `signature_<deliveryId>_<epochMillis>.png` |
| Ảnh proof JPG | `context.filesDir/pod/` | `photo_<deliveryId>_<epochMillis>.jpg` |
| PDF certificate | API 29+: `MediaStore.Downloads`, `RELATIVE_PATH = Download/GingerbreadPoD`; API 24–28: `Environment.getExternalStoragePublicDirectory(DOWNLOADS)/GingerbreadPoD/` | `delivery_certificate_<deliveryId>.pdf` |

### 4.4 Dữ liệu mock của Manifest (`ManifestRepositoryImpl.createInitialMockStops()`)

Manifest hiện sử dụng dữ liệu mock được hardcode để phục vụ việc demo và testing.

- Mỗi `DeliveryStop` có các thông tin chính:
  - `id`: mã stop.
  - `orderNumber`: mã đơn hàng (dạng ORD-XXXX-XXXX)
  - `trackingNumber`: mã tracking (dạng PH-XXXX) dùng cho OCR reconciliation.
  - `recipientName`, `recipientPhone`: thông tin người nhận.
  - `deliveryAddress`: địa chỉ giao hàng.
  - `latitude`, `longitude`: tọa độ GPS.
  - `stopOrder`: thứ tự giao hàng.
  - `estimatedArrival`: thời gian dự kiến đến (chuỗi tĩnh, không tính từ GPS).
  - `specialInstructions`: hướng dẫn đặc biệt cho tài xế.
  - `status`: trạng thái giao hàng, mặc định là `PENDING`.
  - `isVerifiedWithOcr`: trạng thái xác thực bằng OCR, mặc định là `false`.
- Mỗi stop chứa một danh sách `PackageItem`, với mỗi item gồm: `id` , `name` , `quantity` , `weightKg` , `description`
- Dữ liệu mock được dùng để khởi tạo `_stopsFlow` và đồng thời seed các thông tin cơ bản của delivery vào Room database.

Ngoài ra còn 1 bản ghi placeholder: `id = "sample-delivery-001"`, `recipientName = "Sample Recipient"`, `address = "123 Sample St"`, `status = PENDING` — được `PodCaptureViewModel.ensureDeliveryExists()` tạo khi mở tab PoD trực tiếp.

---

## 5. Flow đã triển khai (chi tiết theo code hiện tại)

### 5.0 Khởi động app

1. `GingerbreadApp` (`@HiltAndroidApp`) cung cấp `workManagerConfiguration` với `HiltWorkerFactory` → `SyncWorker` được inject dependency.
2. `MainActivity.onCreate`: `installSplashScreen()` → `enableEdgeToEdge()` → `enqueuePeriodicSync()` (unique periodic work `"delivery_sync"`, 15 phút, ràng buộc `NetworkType.CONNECTED`, `ExistingPeriodicWorkPolicy.KEEP`) → `handleIntent(intent)` → `setContent { Gingerbread_PoDManagerTheme { AppNavGraph(...) } }`.
3. `onStart`/`onStop`: đăng ký/huỷ `NetworkStateReceiver` với `ConnectivityManager.CONNECTIVITY_ACTION` (runtime-registered; action này deprecated nhưng vẫn dùng).
4. `onNewIntent` → `setIntent` + `handleIntent`: nếu có extra `TrackingNotificationHelper.EXTRA_OPEN_TRACKING = true` thì bật cờ `openTrackingRequested` (Compose state).
5. `AppNavGraph` lấy `SessionViewModel` (Activity-scoped qua `hiltViewModel()` không có NavBackStackEntry):
   - `SessionUiState.Loading` → không render gì (tránh nháy màn Login 1 frame lúc cold start).
   - `Ready(null)` → `LoginScreen`.
   - `Ready(SHIPPER)` → `ShipperFlow`.
   - `Ready(MANAGER)` → `ManagerFlow`.

### 5.1 Login / phân quyền (`feature_auth`)

- `LoginScreen`: bọc trong `Surface(color = background)` (vì không nằm trong `Scaffold` — thiếu thì nền trắng). Tiêu đề "Gingerbread PoD Manager" + "Choose how you're using this device". 2 nút `Continue as Shipper` (`Button`) và `Continue as Manager` (`FilledTonalButton`), bo `RoundedCornerShape(20.dp)`, icon 32dp, chữ `headlineSmall`.
- Chiều cao nút: `LocalConfiguration.current.screenHeightDp.dp * ROLE_BUTTON_SCREEN_HEIGHT_FRACTION`, tính **1 lần** rồi gán cho cả 2 nút. Hiện `ROLE_BUTTON_SCREEN_HEIGHT_FRACTION = 0.15f`.
- `SessionViewModel.selectRole(role)` → `SessionRepository.setRole` (DataStore). `logout()` → `clearRole()` → quay về Login.
- `ShipperFlow`: `Scaffold` + `CenterAlignedTopAppBar("Gingerbread PoD", actions = Logout)` + bottom nav 3 tab (Manifest / Tracking / PoD) + `NavHost` (start = `manifest`).
- `ManagerFlow`: `Scaffold` + `CenterAlignedTopAppBar("Dispatcher Dashboard", actions = Logout)` + `DispatcherDashboardScreen`, không bottom nav.
- Gate notification: `LaunchedEffect(openTrackingRequested, role)` — nếu role là SHIPPER thì navigate `tracking` (popUpTo start, saveState, singleTop, restoreState); **luôn** gọi `onTrackingRequestHandled()` để cờ không bị treo khi đổi role sau đó.
- Role sống qua kill app hẳn (DataStore); chỉ mất khi Logout.

### 5.2 UC-01 — Manifest & Interactive Map (`feature_manifest`, Member A)

**Màn hình** `ManifestScreen` (+ `ManifestViewModel`, `ManifestUiState`):
- Google Map (Maps Compose) với cluster (`DeliveryStopClusterItem`) và pin tuỳ chỉnh (`DeliveryPinMarker`, màu theo `DeliveryStatus`, IN_TRANSIT = `0xFF1565C0`). Có chế độ danh sách (`toggleViewMode`, `StopListItem`).
- `ManifestSearchBar`: lọc theo tên người nhận, địa chỉ, tracking number, order number, tên item (`ManifestUiState.filteredStops`). Hiển thị số đã giao / tổng (`completedCount` / `totalCount`).
- Chọn stop → quick card → `StopDetailSheet`: thông tin người nhận, item, special instructions, `StopStatusChip`, và các hành động:
  - **Directions**: mở ứng dụng Google Maps thật bằng `Intent.ACTION_VIEW` (không vẽ đường trong app).
  - **Call**: `Intent.ACTION_DIAL`.
  - **Start Transit** (disabled nếu đã IN_TRANSIT): `onStatusChange(stop.id, IN_TRANSIT)` rồi `onStartTransit(stop)` → `NavGraph` navigate `Screen.Tracking.createRoute(stop.deliveryAddress, stop.latitude, stop.longitude)`.
  - **Capture PoD**: `onNavigateToPod(stop.id)` → `pod/{deliveryId}`. Có 3 điểm gọi (Quick Card, Detail Sheet, List Item).
- **OCR** (`OcrScannerDialog` + `OcrScanner` bọc ML Kit `TextRecognition` Latin): CameraX preview; có thể quét theo stop đang chọn (`openOcrScanner(stop)`) hoặc **Quick Scan** không chọn stop (`openQuickScan`). Kết quả:
  - `Match` → xác nhận, nút "Capture PoD".
  - `WrongStopMatch` → cảnh báo gói thuộc stop khác + nút **"Switch to Stop #n"** (`switchToStop`: chọn stop mới, đóng scanner, mở detail sheet).
  - `Mismatch`, `NotFoundInManifest`, `NoTextFound`.
  - `confirmManualVerification(stopId)` → đánh dấu `isVerifiedWithOcr`.
  - 4 nút demo **Simulate Match / Simulate Wrong Stop / Simulate Stop #2 / Simulate Unknown (`PH-9999`)**: sinh bitmap nhãn giả (`createMockLabelBitmap`) rồi chạy **OCR thật** trên bitmap đó — để test/demo không cần nhãn in sẵn.

**Thuật toán `VerifyTrackingNumberUseCase(rawText, expectedTrackingNumber?, allStops)`** (USP):
1. Text rỗng → `NoTextFound`.
2. `normalizeCode` = bỏ ký tự không phải chữ/số + uppercase. Nếu raw chứa tracking hoặc order number của stop đang chọn → `Match`.
3. Regex `\b(PH-[A-Z0-9]{4,10})\b` và `\b(ORD-\d{4}-\d{4})\b` (ignore case) để lấy ứng viên; ứng viên tracking khớp expected → `Match`.
4. Dò từng dòng, dòng chứa expected → `Match`.
5. Đối soát với **toàn bộ manifest**: ứng viên/raw khớp tracking hoặc order của 1 stop khác stop đang chọn → `WrongStopMatch`; khớp mà không có stop đang chọn → `Match` với stop đó.
6. Không khớp: có expected → `Mismatch`, không có → `NotFoundInManifest`.

**Đồng bộ với Room (`ManifestRepositoryImpl`, @Singleton):**
- `_stopsFlow` giữ 10 stop mock trong bộ nhớ (`MutableStateFlow`).
- `init`: seed các stop chưa có vào bảng `deliveries` (`status = PENDING`), rồi `observeAll()` để phản chiếu status từ Room ngược lại UI (ví dụ UC-03 set DELIVERED → pin/chip Manifest đổi theo).
- `updateStopStatus` cập nhật cả `_stopsFlow` và Room (upsert).
- `isVerifiedWithOcr` chỉ nằm trong bộ nhớ (không lưu Room).
- Có constructor phụ không tham số dùng `DeliveryDao` giả — phục vụ unit test.

### 5.3 UC-02 — Active Navigation & Delivery Mode (`feature_tracking`, Member B)

**Route:** `tracking?destinationLabel={destinationLabel}&lat={lat}&lng={lng}` — cả 3 arg optional (nullable, default null) nên bấm tab "Tracking" (route trần `tracking`) vẫn vào cùng destination.

**`TrackingScreen`:**
- Quyền: `ACCESS_FINE_LOCATION` (+ `POST_NOTIFICATIONS` từ API 33) qua Accompanist. Chưa có quyền → nút "Grant location & notification permission".
- Đích hiệu lực: ưu tiên nav arg → nếu không có thì lấy `destinationLabel`/`destinationLocation` đang lưu trong repository → fallback label `"Next stop (TBD)"`.
- 🆕 `LaunchedEffect(destinationLabel, destinationLatLng)`: mỗi khi có nav arg mới (bấm Start Transit ở stop khác) thì gọi `viewModel.updateDestination(...)` **bất kể đang tracking hay không** → repository luôn giữ đúng stop đang active (commit `5bc1619`, PR #23, 11/09).
- Nút `Start Delivery Route` (khi idle) → `startDeliveryRoute(label, DestinationPoint?)`; `Stop Delivery Route` khi đang tracking.
- `TrackingStatusCard`: "Tracking"/"Idle", "Heading to: …", "Elapsed: mm:ss", "Lat: …, Lng: …" hoặc "Waiting for GPS fix...".
- Bản đồ: marker vị trí hiện tại + marker đích (màu cam) + `Polyline` **đường thẳng** 2 điểm (không dùng Directions API — quyết định bỏ qua).
- Camera: lần có GPS đầu tiên sau khi bắt đầu/đổi đích → `newLatLngBounds(current, destination, 120)` 1 lần (`hasShownRouteOverview`, `remember(effectiveDestinationLatLng)`); các lần sau → follow `newLatLngZoom(current, FOLLOW_ZOOM = 17f)`, animate 600ms.
- Đầu màn hình có `Text("Active Navigation & Delivery Mode")`.

**`TrackingViewModel`:** mirror `isTracking`, `currentLocation`, `destinationLabel`, `destinationLocation` từ repository (`stateIn WhileSubscribed(5_000)`). Bộ đếm `elapsedSeconds` nằm ở ViewModel (ticker `delay(1_000)` chạy khi `isTracking = true`, reset 0 khi dừng) → sống qua chuyển tab; là stopwatch thuần, không liên quan GPS.

**`LocationTrackingRepositoryImpl` (@Singleton, scope riêng `SupervisorJob + Dispatchers.Main.immediate`):**
- `startTracking(label, dest)`: `ContextCompat.startForegroundService` + `bindService(BIND_AUTO_CREATE)`, lưu label/dest, `isTracking = true`.
- `onServiceConnected`: mirror `service.currentLocation` (android `Location`) → `TrackedLocation(lat, lng, time)`.
- `stopTracking()`: huỷ mirror, unbind, `stopService`, xoá label/dest, `isTracking = false`.
- `updateDestination(label, dest)`: chỉ đổi 2 StateFlow, không đụng service/isTracking.

**`DeliveryTrackingService`** (`@AndroidEntryPoint`, started + bound): `onStartCommand` đọc `EXTRA_DESTINATION_LABEL`, dựng notification, `ServiceCompat.startForeground(..., FOREGROUND_SERVICE_TYPE_LOCATION)` từ API 29 (dưới thì `startForeground`), request `PRIORITY_HIGH_ACCURACY` mỗi `10_000L` ms; thiếu quyền FINE → `stopSelf()`; trả `START_STICKY`. `onDestroy` gỡ location updates.

**`TrackingNotificationHelper`:** channel `delivery_tracking_channel` (IMPORTANCE_LOW), `NOTIFICATION_ID = 1001`, title "Delivery route active", text "Heading to: %1$s", ongoing, only-alert-once. Tap → `PendingIntent` mở `MainActivity` với `FLAG_ACTIVITY_SINGLE_TOP | FLAG_ACTIVITY_CLEAR_TOP` + `EXTRA_OPEN_TRACKING = true` (tái sử dụng Activity, không reset ViewModel/timer).

**Bottom nav (`ShipperBottomNavigationBar`):** `popUpTo(start) { saveState = true }`, `launchSingleTop = true`, `restoreState = (route != manifest && route != tracking)`. Tracking bị loại khỏi restore vì nav-arg cũ sẽ đè lên đích đúng đang giữ trong repository.

**Phạm vi đã chốt:** tracking sống qua activity lifecycle (xoay màn hình, về nền) — đúng yêu cầu đề; **không** cam kết sống qua OS kill process (ngoài scope). Không có tự phát hiện "đã tới nơi" (không geofence/distanceTo).

### 5.4 UC-03 — PoD Capture (`feature_pod`, Member C)

**Route:** `pod/{deliveryId}` (từ Manifest) và route trần `pod` (tab bottom nav) → dùng `PLACEHOLDER_DELIVERY_ID = "sample-delivery-001"`. Khi rời PoD bằng nút back → navigate về `manifest` (`saveState = false`).

**`PodCaptureScreen`:**
- Gọi `ensureDeliveryExists(deliveryId)`: nếu chưa có trong Room thì tạo bản ghi placeholder (`Sample Recipient`, `PENDING`) — cần thiết vì `signatures`/`photos` có FK tới `deliveries`.
- Nếu `deliveryId == SAMPLE_DELIVERY_ID` (hằng số riêng trong file này, phải trùng tay với `NavGraph.kt`): phụ đề đỏ "Sample data (no stop selected)" + banner `errorContainer` icon `WarningAmber`: "No delivery selected. This is a sample test data - go to Manifest and pick a stop to capture proof for an actual delivery". Ngược lại phụ đề "Active Delivery Stop: #<id>".
- 2 tab: **Signature** / **Photo** (`PrimaryTabRow`).
- Nút **Complete delivery** → `CompleteDeliveryUseCase`; lỗi hiện rõ "Missing signature" / "Missing proof photo" / "Delivery not found"; thành công → "Delivery completed ✓".

**Signature** (`SignatureCaptureScreen` + `SignatureCanvas` + `SignatureCanvasState` + `SignaturePathUtil` + `SignatureRasterizer`):
- Custom Compose Canvas, nét vẽ làm mượt Bezier (`quadraticBezierTo`).
- Canvas cao 240dp, `.clip(SIGNATURE_CANVAS_SHAPE)` + `.border(...)` dùng chung `RoundedCornerShape(8.dp)` (để nét vẽ lúc ký không tràn khung — ảnh lưu vốn đã đúng khung), `.testTag(SIGNATURE_CANVAS_TEST_TAG = "signatureCanvas")`.
- Save: rasterize PNG theo **kích thước canvas thật** → `FileSignatureImageStore.savePng` → lấy GPS (`LocationProvider`) → `SaveSignatureUseCase` → Room → reset canvas. Có nút Clear.

**Photo** (`PhotoCaptureScreen` + `CameraPreview` + `PhotoCaptureViewModel`):
- Xin quyền CAMERA (Accompanist). CameraX `ImageCapture.takePicture` ghi vào đường dẫn từ `FilePhotoStore` → `onPhotoWritten` lấy GPS → `SavePhotoUseCase` → Room.
- Column ngoài cùng có `verticalScroll` để dòng "Photo saved (...)" không bị che (Sprint 3).

**GPS stamp:** `TrackingAwareLocationProvider` → nếu UC-02 đang tracking thì dùng `trackingRepository.currentLocation.value`, không thì fallback `FusedLocationProvider` (one-off). Lat/lng lưu nullable.

**Quan hệ dữ liệu:** Signature/Photo **1–nhiều** với Delivery (ký/chụp lại giữ lịch sử). `CompleteDeliveryUseCase` chỉ kiểm tra có ≥ 1 signature và ≥ 1 photo, rồi `saveDelivery(status = DELIVERED)` (→ `DeliveryDao.upsert`). Không cần Stop Delivery Route trước khi Complete (UC-02 và UC-03 độc lập).

### 5.5 UC-04 — Offline Sync & Dispatcher Dashboard (`feature_sync`, Member D / Hòa)

**Nền (không cần UI):**
- `SyncWorker` (`@HiltWorker`, `CoroutineWorker`): `EnqueuePendingDeliveriesUseCase()` rồi `ProcessSyncQueueUseCase()`; exception → `Result.retry()`.
- Kích hoạt: periodic 15 phút (khi có mạng) từ `MainActivity`, và one-off khi `NetworkStateReceiver` thấy `cm.activeNetwork != null`.
- `EnqueuePendingDeliveriesUseCase`: lấy mọi delivery `DELIVERED` chưa có queue item → tạo `SyncQueueItem(status = QUEUED)` (1–1 với delivery).
- `ProcessSyncQueueUseCase`: lấy `QUEUED + FAILED` → set `SYNCING` → `uploader.upload(deliveryId)` → thành công `DONE`; thất bại `FAILED`, `retryCount + 1`, `lastAttemptEpochMillis = now`.
- `StubDeliveryUploader.upload()` luôn trả `true` (TODO thay bằng network call thật).

**`DispatcherDashboardScreen` (Manager):**
- `DriverStatusCard`: "Driver: En route" / "Driver: Idle" + toạ độ — đọc thẳng `LocationTrackingRepository.isTracking`/`currentLocation` (cùng singleton trong process, nên chỉ đúng khi Shipper và Manager dùng chung 1 máy).
- Nút **Sync now** (`OutlinedButton`, căn giữa, rộng 75%) → `syncNow()` = enqueue + process + `refresh()`.
- Danh sách: `refresh()` load **IN_TRANSIT trước, DELIVERED sau**; mỗi dòng: tên người nhận và "In transit" (IN_TRANSIT, không có sync status) hoặc "Sync: QUEUED/SYNCING/FAILED/DONE/Not queued". PENDING/FAILED không hiện. Rỗng → "No deliveries yet.".
- **Export PDF** chỉ hiện cho DELIVERED → `ExportDeliveryCertificateUseCase` lấy signature/photo **mới nhất** (phần tử đầu, nhờ `ORDER BY ... DESC`) → `AndroidPdfGenerator`; hiện dòng "PDF saved: <path>" (`lastExportedPdfPath`). `exportCertificate()` bọc try/catch.
- Tiêu đề "Dispatcher Dashboard" chỉ nằm ở TopAppBar của `ManagerFlow` (đã bỏ bản trùng trong screen).

**`AndroidPdfGenerator`:** `PdfDocument` A4 595×842; dòng "Delivery Certificate", Delivery ID, Recipient, Address; ảnh chữ ký (box 200×100) và ảnh proof (box 200×160) vẽ theo `aspectFitRect` (giữ tỉ lệ) và `loadRotatedBitmap` (đọc EXIF `ExifInterface` + `Matrix.postRotate`). Thiếu ảnh → ghi rõ "not captured" hoặc "file missing on disk (<path>)". Lưu: API 29+ `saveToMediaStoreDownloads` (`@RequiresApi(Q)`, trả `content://` URI); API 24–28 `saveToLegacyDownloadsDir` (trả absolute path).

### 5.6 Luồng demo end-to-end (1 máy)

1. Mở app → Login → **Continue as Shipper**.
2. Manifest: chọn stop → (tuỳ chọn) Scan OCR / Simulate để thấy `Match` / `WrongStopMatch` → "Switch to Stop".
3. **Start Transit** → stop thành IN_TRANSIT (pin/chip đổi màu) → sang Tracking → **Start Delivery Route** → notification "Heading to: …", camera zoom route rồi follow.
4. Quay lại Manifest → **Capture PoD** → ký tên (Save) → chụp ảnh → **Complete delivery** → DELIVERED.
5. **Logout** → **Continue as Manager** → Dashboard: thấy driver En route, stop IN_TRANSIT/DELIVERED → **Sync now** (QUEUED → DONE) → **Export PDF** → file trong Downloads/GingerbreadPoD.
6. Tap notification khi đang Manager: không có gì xảy ra; khi đang Shipper: mở tab Tracking.

---

## 6. Lịch sử triển khai & các thay đổi trong quá trình làm

### 6.1 Sprint 0 — Setup & Architecture (19–21/08) · tag `v0.1-skeleton`

- Dựng Clean Architecture package trên `main`; `feature_*` rỗng với `.gitkeep` (quy tắc: xoá `.gitkeep` khi thêm code thật — hiện chỉ còn `core/common/.gitkeep`, `core/util/.gitkeep`).
- `compileSdk` 36 → 37 (do `core-ktx 1.19.0`).
- Hilt, Room (chưa có entity), Navigation Compose (4 route placeholder), Maps Compose, play-services-location, Accompanist.
- Version ban đầu: Kotlin 2.2.10, KSP 2.2.10-2.0.2, Hilt 2.59.2 (2.59.0/2.59.1 lỗi `ComponentTreeDeps` với AGP 9), Maps Compose 6.4.4 — **đã thay đổi ở Sprint 1**, xem §6.2.
- `gradle.properties` thêm `android.disallowKotlinSourceSets=false`.
- Maps API key: 1 key chung, restrict theo package + **SHA-1 debug fingerprint**, chỉ bật Maps SDK for Android; lưu trong `local.properties` từng máy.
- Tạo `develop` từ `main` sau tag. Branch protection **chưa bật** (❓ không có tài liệu nào xác nhận đã bật về sau).

### 6.2 Sprint 1 — Core Feature Build (22–29/08; merge thực tế 02–03/09) · tag dự kiến `v0.2-core-features`

- Nhánh `feature/uc01-manifest-map`, `feature/uc02-tracking-service`, `feature/uc03-pod-capture`, `feature/uc04-sync-dashboard`. Thứ tự merge: **C → D → A → B**.
- UC-03 (C): Signature Canvas Bezier, CameraX, Room entity Delivery/Signature/Photo, `CompleteDeliveryUseCase` + 4 test.
- UC-04 (D/Hòa, 10 bước tuần tự): `SyncStatus`, `SyncQueueItem`, `SyncQueueEntity/Dao`, `SyncRepository`, `DeliveryUploader`, `PdfGenerator`, 2 use case, `SyncWorker`, `NetworkStateReceiver`, `AndroidPdfGenerator`, `StubDeliveryUploader`, `SyncModule`, Dashboard, `ExportDeliveryCertificateUseCase`, 5 test.
- UC-01 (A): Maps Compose + Clustering, ML Kit OCR, search bar, detail sheet, mock repository 5 stop, 10 test.
- UC-02 (B): Foreground + bound service, Fused Location 10s, `START_STICKY`, notification.
- ⚠️ **Sự cố version:** A tự nâng Kotlin/KSP/Hilt/AGP/Compose BOM/Maps Compose → xung đột. Chốt bộ cuối: `agp 9.2.1` (giới hạn IDE), `kotlin 2.4.10`, `ksp 2.3.11`, `hilt 2.60.1`, `hiltNavigationCompose 1.4.0`, `composeBom 2026.08.00`, `mapsCompose 8.5.0`. Lỗi `incompatible metadata version 2.4.0 vs compiler 2.2.0` do đổi lẻ tẻ → phải đổi đồng bộ cả nhóm + Invalidate Caches.
- Bổ sung cuối sprint: **Bottom Navigation** 4 tab, pattern "single top + restore state"; `AppNavGraph` tự quản `Scaffold`, `MainActivity` bỏ `Scaffold` cũ.
- Thực tế repo cuối Sprint 1: 19 unit test (báo cáo ghi 9 — repo thật nhiều hơn), chưa có UI test, `Screen.Pod` vẫn route tĩnh + `PLACEHOLDER_DELIVERY_ID`.

### 6.3 Sprint 2 — Integration (30/08–05/09; merge thực tế 05–08/09) · tag dự kiến `v0.3-integrated`

- Nhánh `sprint2/uc0x-integration`. Thứ tự merge: **A → C → B → D** (C trước B được vì C dùng interface của B + fallback).
- ⚠️ Audit PR #9 (A) phát hiện 2 lỗi kiến trúc, Hòa sửa ở commit `6e5990c`:
  1. `ManifestRepositoryImpl` import thẳng `feature_pod.data.local.dao.DeliveryDao`/`DeliveryEntity` → chuyển 2 file sang `core/database/dao|entity/` bằng **Refactor → Move**.
  2. 2 enum `DeliveryStatus` trùng → gộp về `core/domain/model/DeliveryStatus.kt`, sửa ~17 file; ~40 lỗi build (thiếu import + 1 chỗ fully-qualified trong `ManifestUiState.kt`).
- UC-01: reconciliation `WrongStopMatch`/`NotFoundInManifest`, route `pod/{deliveryId}`, seed Room + observe 2 chiều, Quick Scan, stop-switching dialog, simulate buttons, +test.
- UC-03: `TrackingAwareLocationProvider` (DI qua `LocationTrackingRepository`), nhận `deliveryId` thật. Conflict `NavGraph.kt` với A → giữ bản A (có `onBack`).
- UC-02: `TrackingScreen` đầy đủ (map, marker, polyline, status card), service Hilt-managed, `core/di/LocationModule.kt` dùng chung, sửa `saveState` bất đối xứng ở bottom nav, nhận destination qua "Start Transit".
- UC-04: `DriverStatusCard` + 8 test `DispatcherDashboardViewModelTest`.
- CameraX 1.5.3 → 1.6.2 (leaf, được phép).
- Test tay end-to-end: Manifest → PoD → Dashboard → Sync (QUEUED → DONE) → PDF (kéo file qua Device File Explorer, nội dung đúng).

### 6.4 Sprint 3 (+4) — Testing, Hardening & Submission (08–11/09)

`Sprint3_Workflow.md` gộp Sprint 3 + 4 do chỉ còn 3 ngày; ưu tiên theo rubric; quyết định **không** làm Retrofit thật và backend upload.

| Ngày | Commit | Nội dung |
|---|---|---|
| 08/09 | `76cbaed` | Compose UI test `SignatureCaptureScreenTest` (sign → save → reset; clear) |
| 08/09 | `2d4683b`, `8c1d81b`, `b9db1aa` (Viet Anh) | Google Fonts + font certs; palette Style 1 (Outfit/Inter); fix chữ OCR Scan tràn dòng, padding nút |
| 08/09 | `48923e1` | Xoá test mẫu mặc định của Android Studio; splash screen thương hiệu |
| 09/09 | `78fa4e3` | Elapsed time + destination sống qua chuyển tab; fix tràn layout Dashboard; PDF lưu Downloads |
| 09/09 | `f481389` | `DeliveryDao` REPLACE → `@Upsert` (hết mất ảnh PoD); EXIF; PDF ra Downloads công khai |
| 09/09 | `a04972b`, `aee48e9` | Đổi `app_name` → "Gingerbread PoD"; đổi icon app |
| 09/09 | `05e704d` | Camera Tracking: overview 1 lần rồi follow zoom 17 |
| 09/09 | `2c777e0` | Hiện lại dòng "Photo saved", restyle nút Sync, bỏ header UC-02, PDF giữ tỉ lệ ảnh |
| 09/09 | `b37b873` | Thêm stop-06 → stop-10 |
| 09/09 | `ba0c56a` (PR #17, NoahPhung), `d2100d1` + PR #18 | Merge nhánh UC-03 vào `main`; đồng bộ `main` ↔ `develop`. Theo debug notes: 1 thành viên đẩy thẳng lên `main` phần `testTag` cho canvas chữ ký → conflict `SignatureCaptureScreen.kt` (import trùng, const trùng 2 giá trị) → giữ bản `main`: `SIGNATURE_CANVAS_TEST_TAG = "signatureCanvas"` |
| 10/09 | `5021a76`, `210cfec` (Quang Nguyen), PR #19 | Tap notification mở tab Tracking (`singleTop` + `onNewIntent`); đổi hành vi zoom. Snapshot `main` 10/09 sau đợt merge này không còn `hasShownRouteOverview` (❓ tài liệu không chỉ rõ commit nào gây mất) |
| 10/09 | `e8c1dbd` | Khôi phục `hasShownRouteOverview` (hồi quy do merge nhánh cũ) |
| 10/09 | `06c76e9` | Dashboard hiện thêm IN_TRANSIT; placeholder `sample-delivery-001` IN_TRANSIT → PENDING; banner sample data; cập nhật test |
| 10/09 | `122bfc6` | `.clip()` khung chữ ký |
| 11/09 | `ffa950c`, PR #21 | `feature_auth`: Login 2 role + DataStore + tách ShipperFlow/ManagerFlow + Logout + gate notification |
| 11/09 | `9f572b7`, PR #22 | "button size" — nút role 0.25f → **0.15f** chiều cao màn hình |
| 11/09 | `5bc1619`, PR #23 | `updateDestination()` — đồng bộ đích đến theo stop đang active khi chuyển tab (⚠️ làm gãy compile unit test, xem §9 mục 1) |

Hai commit cuối (`9f572b7`, `5bc1619`) **không được ghi trong bất kỳ tài liệu nào của Project** — mô tả ở trên lấy từ code + comment trong code.

### 6.5 Thay đổi đáng chú ý so với kế hoạch gốc (`Assignment2_PoD_Group_Plan.md`)

| Kế hoạch | Thực tế |
|---|---|
| Retrofit cho order list UC-01 | Không làm — mock hardcode + seed Room |
| Network client thật cho sync | `StubDeliveryUploader` luôn `true` |
| Package `database/` riêng ở root | Đặt trong `core/database/` |
| Signature/Photo 1–1 với Delivery (dự thảo Sprint 0) | 1–nhiều |
| 4 tab bottom nav (Manifest/Tracking/PoD/Dashboard) | Sprint 3: tách role — Shipper 3 tab, Manager chỉ Dashboard |
| Khoá version cho mọi thư viện | Chỉ khoá nhóm toolchain; leaf dependency tự do (phải báo nhóm khi đổi `libs.versions.toml`) |
| Sprint 3 (06–09/09) + Sprint 4 (10–11/09) | Gộp làm 1, bắt đầu 08/09 |
| Nhánh `feature/*` | Sprint 2 dùng `sprint2/*`, Sprint 3 dùng `sprint3/*` |
| Unit test PoD form validation + WorkManager retry; UI test sign → photo → save | Có `CompleteDeliveryUseCaseTest`, `ProcessSyncQueueUseCaseTest`; UI test chỉ phần Signature (CameraX khó automate) |

---

## 7. Quyết định đã chốt (không tự ý đổi lại)

### Quyết định đã chốt

1. Signature/Photo **1–nhiều** với Delivery (giữ lịch sử ký/chụp lại)
2. `DeliveryStatus` chỉ 4 giá trị, không có `COMPLETED`; `DELIVERED` là trạng thái kích hoạt sync
3. `imageFilePath` lưu **absolute path**
4. Sync queue **1–1** với Delivery (sync cả delivery, không sync từng ảnh)
5. Business logic nằm trong use case, Worker/Service chỉ là glue
6. Khoá version chỉ nhóm toolchain (AGP/Kotlin/KSP/Hilt/ComposeBom/Room/MapsCompose); leaf dependency được đổi nhưng phải báo nhóm
7. Shared entity/DAO đặt ở `core/`, feature chỉ phụ thuộc domain interface của nhau
8. Merge order linh hoạt nếu phụ thuộc đã abstract qua interface
9. Giữ mock Manifest + `StubDeliveryUploader`, ghi vào Readme thay vì làm backend trong những ngày cuối
10. Tracking chỉ cần sống qua activity lifecycle, **không** cần sống qua OS kill process
11. Không implement Directions API cho Tracking (polyline đường thẳng; nút Directions ở Manifest mở Google Maps app)
12. Không thêm dòng "Delivered!" trên Tracking sau Complete (Tracking không biết trạng thái từng đơn)
13. Không bắt buộc Stop Delivery Route trước khi Complete delivery
14. Dashboard hiện **IN_TRANSIT + DELIVERED** (IN_TRANSIT trước); Export PDF chỉ cho DELIVERED; PENDING/FAILED ẩn
15. Giữ cơ chế placeholder `sample-delivery-001` (bỏ đi thì tab PoD mở trực tiếp sẽ lỗi FK), seed với status PENDING + banner cảnh báo
16. `SIGNATURE_CANVAS_TEST_TAG = "signatureCanvas"` (bản `main`)
17. Role lưu bằng **DataStore**, không `rememberSaveable` (không sống qua kill app), không Room
18. Login không có ô nhập liệu; demo trên 1 máy, không đồng bộ đa thiết bị
19. Conventional Commits `type(scope): subject` + body gạch đầu dòng theo file

---

## 8. Bug đã sửa & root cause

| # | Triệu chứng | Root cause | Fix |
|---|---|---|---|
| 1 | ⚠️ PDF không có ảnh chữ ký/ảnh proof | `DeliveryDao.upsert` dùng `@Insert(onConflict = REPLACE)` → SQLite xoá row cũ rồi insert lại → FK `CASCADE` xoá luôn signatures/photos đúng lúc set DELIVERED | Đổi sang `@Upsert` (UPDATE thật). **Không được quay lại REPLACE cho bảng cha** |
| 2 | Ảnh proof trong PDF bị xoay ngang | CameraX ghi EXIF orientation, `BitmapFactory` bỏ qua | `exifinterface` + `loadRotatedBitmap()` |
| 3 | Ảnh trong PDF méo | Vẽ vào `RectF` cố định | `aspectFitRect()` ("contain") |
| 4 | Marker Tracking "đứng yên" dù toạ độ đổi | Camera `newLatLngBounds` lại mỗi lần có GPS → zoom quá xa | Overview 1 lần (`hasShownRouteOverview`) rồi follow zoom 17 |
| 5 | Bug #4 quay lại sau merge 10/09 | Merge nhánh cũ (stale branch) | Khôi phục (`e8c1dbd`) — ⚠️ bài học: diff kỹ file đã từng sửa bug sau mỗi merge |
| 6 | Elapsed time reset khi đổi tab | Bộ đếm nằm trong composable | Chuyển vào `TrackingViewModel` |
| 7 | "Heading to" / pin đích / polyline mất khi rời tab Tracking | Nav arg không sống qua đổi tab | `destinationLabel`/`destinationLocation` StateFlow trong repository |
| 8 | Chuyển sang stop khác khi đang tracking, quay lại tab thấy stop cũ | `startTracking()` chỉ gọi được khi idle nên repository không nhận stop mới; restore nav state cũ đè lên | Code hiện tại: `updateDestination()` + `LaunchedEffect` trên nav arg + bottom nav không `restoreState` cho Tracking (commit `5bc1619` theo message; lý do lấy từ comment trong code) |
| 9 | Nút "Export PDF" vỡ chữ dọc; tiêu đề Dashboard bị cắt | `Column` thiếu `weight` đẩy Button co về 0 | `weight(1f, fill = false)` + ellipsis; tách header |
| 10 | Dòng "Photo saved" bị che | Column không cuộn | `verticalScroll` |
| 11 | Dòng rác "Sample Recipient" trên Dashboard | Placeholder seed IN_TRANSIT, lộ ra khi Dashboard hiện IN_TRANSIT | Seed PENDING (bản ghi cũ phải xoá app data vì không có Migration) |
| 12 | Nét chữ ký tràn khỏi khung lúc vẽ | Compose `Canvas` không tự clip | `.clip(shape)` cùng shape với border |
| 13 | 2 nút Login cao khác nhau (~468px vs ~339px) | `fillMaxHeight(fraction)` trong `Column` tính trên phần còn lại theo thứ tự đo | Tính 1 lần từ `LocalConfiguration.screenHeightDp` |
| 14 | Login nền trắng | Không nằm trong `Scaffold` | Bọc `Surface(background)` |
| 15 | Tiêu đề Dashboard lặp 2 lần | TopAppBar của ManagerFlow + Text trong screen | Bỏ Text trong screen |
| 16 | Mất state tab khi quay lại (Sprint 2) | `saveState` bất đối xứng ở bottom nav | `saveState = true` luôn, chỉ `restoreState` quyết định |
| 17 | Build lỗi `incompatible metadata version` (Sprint 1) | Đổi version lẻ tẻ giữa các lần sửa | Đổi đồng bộ cả nhóm version, Invalidate Caches |
| 18 | Import `hiltViewModel` sai package (Sprint 1) | Báo cáo Sprint 1 ghi package `androidx.hilt.lifecycle.viewmodel.compose` "không tồn tại" | Đổi sang `androidx.hilt.navigation.compose`. Sprint 2 xác nhận package kia hợp lệ với `hiltNavigationCompose = 1.4.0` (§9 mục 10) |
| 19 | Lint error API level | `saveToMediaStoreDownloads()` dùng API 29 | `@RequiresApi(Build.VERSION_CODES.Q)` — ⚠️ dễ sót khi đọc diff |
| 20 | `FakePodRepository` thiếu method mới (Sprint 1) | Interface thêm hàm nhưng fake test chưa cập nhật | Implement trong fake — **lặp lại y hệt ở §9 mục 1** |

---

## 9. ⚠️ Những điểm QUAN TRỌNG cần lưu ý

1. ⚠️ **Unit test hiện KHÔNG compile.** Có method abstract `updateDestination` trong `LocationTrackingRepository`, nhưng `FakeLocationTrackingRepository` trong `DispatcherDashboardViewModelTest.kt` không override → `./gradlew :app:testDebugUnitTest` fail compile. `assembleDebug` không bị ảnh hưởng. **Cách sửa** — thêm vào `FakeLocationTrackingRepository`, ngay sau `stopTracking()`:

   ```kotlin
       override fun updateDestination(destinationLabel: String, destinationLocation: DestinationPoint?) {
           _destinationLabel.value = destinationLabel
           _destinationLocation.value = destinationLocation
       }
   ```

2. ⚠️ **Room không có Migration và cũng không có `fallbackToDestructiveMigration()`.**  Hệ quả: đổi bất kỳ entity nào mà không bump `version` + thêm `Migration` → app crash khi mở DB trên máy đã cài bản cũ. Cách tạm khi dev: xoá app data / gỡ app.

3. ⚠️ **Không đổi `DeliveryDao.upsert` về `@Insert(REPLACE)`**.

4. ⚠️ **Hằng số placeholder bị khai báo 2 nơi:** `PLACEHOLDER_DELIVERY_ID` (`NavGraph.kt`) và `SAMPLE_DELIVERY_ID` (`PodCaptureScreen.kt`) đều = `"sample-delivery-001"`, không share constant. Đổi 1 chỗ phải đổi chỗ kia.

5. ⚠️ **Nhóm version khoá**: không nâng AGP/Kotlin/KSP/Hilt/ComposeBom/Room/MapsCompose lẻ tẻ; AGP bị giới hạn bởi phiên bản Android Studio.

6. ⚠️ **Maps API key restrict theo package + SHA-1 debug**. Máy mới có debug keystore khác → SHA-1 khác → bản đồ trắng cho tới khi thêm SHA-1 đó vào key trên Google Cloud Console (project `gingerbread-podmanager-2026b`).

7. ⚠️ **`restoreState` của bottom nav cố ý loại Manifest và Tracking** — đừng "sửa cho đồng nhất".

8. ❓ **Notification có thể giữ tên stop cũ:** `updateDestination()` chỉ đổi StateFlow trong repository, không gửi lại intent cho `DeliveryTrackingService`, nên text "Heading to: …" trên notification vẫn là stop lúc bấm Start.

9. **Dashboard có thể hiển thị dữ liệu cũ sau khi đổi role:** `DispatcherDashboardViewModel` được lấy bằng `hiltViewModel()` ngoài NavHost → scope theo Activity; `refresh()` chỉ chạy trong `init` và sau `syncNow()`, không có `LaunchedEffect` refresh khi màn hình hiện lại. Nếu đã vào Manager trước đó trong cùng phiên Activity, rồi Logout → Shipper giao hàng → Logout → Manager, danh sách có thể chưa cập nhật cho tới khi bấm Sync now. Quan sát từ code, chưa kiểm chứng.

10. `hiltViewModel` dùng 2 package khác nhau: `androidx.hilt.lifecycle.viewmodel.compose` (`ManifestScreen`, `PodCaptureScreen`, `TrackingScreen`) và `androidx.hilt.navigation.compose` (các file còn lại). Cả hai hợp lệ với `hilt-navigation-compose 1.4.0`, không phải lỗi.

11. Deprecation còn tồn (không ảnh hưởng chức năng): `CONNECTIVITY_ACTION`, `hiltViewModel` cũ, `rememberMarkerState`, `LocalLifecycleOwner`, `quadraticBezierTo`.

12. `InitializationProvider` bị gỡ toàn bộ trong manifest — nếu thêm thư viện dựa vào App Startup cần xem lại.

13. Docstring `StubDeliveryUploader` nhắc "UC-01's Retrofit client" — thực tế repo không có Retrofit.

14. `README.txt` (mục TECH STACK) liệt kê Turbine, MockK nhưng không test nào dùng; mục UNIMPLEMENTED còn dòng "destructive fallback only" sai.

---

## 10. Test

| File | Loại | Số test | Phạm vi |
|---|---|---|---|
| `VerifyTrackingNumberUseCaseTest.kt` | JVM | 10 | Match (exact/noisy/lowercase/order no.), Mismatch, NoTextFound, WrongStopMatch (tracking/order), manifest match không expected, NotFoundInManifest |
| `ManifestViewModelTest.kt` | JVM | 8 | load 10 stop, select, search, update status, manual verify, open scanner, switchToStop, quick scan |
| `CompleteDeliveryUseCaseTest.kt` | JVM | 4 | not found, thiếu chữ ký, thiếu ảnh, thành công |
| `ProcessSyncQueueUseCaseTest.kt` | JVM | 5 | DONE, FAILED + retry, retry thành công, DONE không xử lý lại, nhiều item |
| `DispatcherDashboardViewModelTest.kt` ⚠️ | JVM | 9 | IN_TRANSIT + DELIVERED, ẩn PENDING/FAILED, sync status null, driver tracking, location, syncNow, export ok/lỗi, dismissError |
| `SignatureCaptureScreenTest.kt` | Compose UI (thiết bị/emulator) | 2 | `signatureCapture_drawThenSave_persistsSignatureAndResetsCanvas`, `signatureCapture_clearButton_removesDrawnStrokeWithoutSaving` |

Tổng: **36 unit test + 2 UI test**. Fake in-memory, không dùng mocking library. Không có test cho `feature_auth`. UI test không cover Photo (CameraX khó automate).

Lệnh: `./gradlew :app:testDebugUnitTest`, `./gradlew :app:connectedDebugAndroidTest`.

---

## 11. Giới hạn đã biết & việc chưa làm

| # | Việc | Trạng thái |
|---|---|---|
| 1 | Order list UC-01 thật (Retrofit) | Không làm — mock 10 stop |
| 2 | Uploader thật | Stub luôn thành công |
| 3 | Room Migration | Chưa có (và không có fallback) |
| 4 | Directions API cho Tracking | Chủ động bỏ |
| 5 | Tracking sống qua OS kill | Ngoài scope; `elapsedSeconds` luôn về 0 khi Activity bị huỷ thật |
| 6 | Test cho `feature_auth` | Chưa có |
| 7 | Login thật / đồng bộ đa thiết bị | Không làm — chỉ chọn role trên 1 máy |
| 8 | Dọn deprecation | Chưa |
| 9 | Dashboard hiển thị ảnh chữ ký/ảnh trực tiếp | Không có — dispatcher xem qua PDF |

**Ý tưởng tương lai:** chuyển Manifest sang Mock API để thêm stop không cần build lại APK + đồng bộ đa máy. Cần: Retrofit/OkHttp + DTO + mapper + loading/error state; cache Room cho offline (lại cần mở rộng `DeliveryEntity` — hiện thiếu toạ độ, tracking number, items — và Migration); dữ liệu phải giữ định dạng `PH-XXXX`, `ORD-YYYY-NNNN` để không phá OCR reconciliation; rủi ro uptime/rate limit dịch vụ miễn phí.

---

## 12. Hướng dẫn dựng lại project

### 12.1 Lấy source

**Repo GitHub** (ưu tiên — giữ lịch sử commit + file nhị phân gốc): `git clone` repo private `RMIT-Vietnam-Teaching/assignment-2-group-gingerbread-mad`, nhánh `main` (cần quyền truy cập org RMIT).

### 12.2 `local.properties`

Tạo ở thư mục gốc repo (Android Studio thường tự thêm `sdk.dir`):

```properties
sdk.dir=<đường dẫn Android SDK trên máy>
MAPS_API_KEY=<YOUR_MAPS_API_KEY>
```

- Key thật: xin người quản lý Google Cloud project `gingerbread-podmanager-2026b` qua kênh riêng.
- ⚠️ Key đang restrict theo package `com.example.gingerbread_podmanager` + SHA-1 → lấy SHA-1 debug của máy mới và thêm vào key, nếu không bản đồ sẽ trắng.

### 12.3 Sửa lỗi compile unit test

Áp đoạn code ở §9 mục 1 vào `FakeLocationTrackingRepository` trong `DispatcherDashboardViewModelTest.kt`.

### 12.4 Mở & build

1. Android Studio phiên bản hỗ trợ **AGP 9.2.1** (❓ tài liệu không ghi chính xác phiên bản Android Studio đã dùng).
2. Open thư mục repo → Gradle Sync. Gradle 9.7.1; JDK 21 toolchain được tải tự động qua foojay (`gradle-daemon-jvm.properties`).
3. `./gradlew :app:assembleDebug` → mong đợi BUILD SUCCESSFUL (có vài warning deprecation, §9 mục 11).

Nếu thiếu file nhị phân (ví dụ chép tay repo từ nguồn khác):
- `gradle/wrapper/gradle-wrapper.jar`: chạy `gradle wrapper --gradle-version 9.7.1` (cần Gradle cài sẵn) rồi khôi phục `gradle-wrapper.properties`, `gradlew`, `gradlew.bat` như bản gốc; hoặc copy jar từ một project Android Studio bất kỳ (phiên bản Gradle thực tế do `gradle-wrapper.properties` quyết định).
- Icon `mipmap-*dpi/*.webp`: ⚠️ bắt buộc để build (manifest + adaptive icon tham chiếu). Tạo lại: chuột phải `res` → New → Image Asset → Launcher Icons, tên `ic_launcher`, Foreground = ảnh logo (pin + xe tải — ❓ ảnh gốc Hòa giữ), Background = Color `#92400E`. Image Asset Studio sẽ ghi đè `mipmap-anydpi-v26/ic_launcher*.xml` và `values/ic_launcher_background.xml` → khôi phục như bản gốc (bản gốc có dòng `<monochrome>`). Giữ nguyên `res/drawable/ic_launcher_foreground.xml` (notification dùng).

### 12.5 Kiểm tra

- `./gradlew :app:testDebugUnitTest` → mong đợi **36 test pass**.
- `./gradlew :app:connectedDebugAndroidTest` (cần thiết bị/emulator) → **2 test pass**.
- Checklist test tay (thiết bị thật, không chỉ emulator):
  - [ ] Login → Shipper; Logout → Manager; kill app từ Recents → role vẫn giữ.
  - [ ] Manifest hiện 10 pin; search; list view; detail sheet; Directions mở Google Maps.
  - [ ] OCR: 4 nút Simulate ra đúng Match / WrongStopMatch (có "Switch to Stop") / NotFoundInManifest.
  - [ ] Start Transit → chip/pin IN_TRANSIT → Tracking nhận đúng đích → Start Delivery Route → notification; camera overview 1 lần rồi follow.
  - [ ] Chuyển tab qua lại: elapsed time, "Heading to", pin đích giữ nguyên; Start Transit stop khác → quay lại tab Tracking thấy stop mới.
  - [ ] Xoay màn hình / về nền khi đang tracking → vẫn tracking.
  - [ ] Tap notification: Shipper → mở tab Tracking; Manager → không làm gì.
  - [ ] PoD: ký (nét không tràn khung) → Save; chụp ảnh → "Photo saved"; Complete delivery → DELIVERED; thiếu 1 trong 2 → báo lỗi rõ.
  - [ ] Mở tab PoD trực tiếp → banner sample data.
  - [ ] Manager: Driver En route/Idle; IN_TRANSIT trước DELIVERED; Sync now → DONE; Export PDF → `Download/GingerbreadPoD/delivery_certificate_<id>.pdf` có chữ ký + ảnh đúng chiều, đúng tỉ lệ.
  - [ ] Bật/tắt chế độ máy bay → khi có mạng lại, sync tự chạy.

### 12.6 APK

- APK debug: `./gradlew :app:assembleDebug` → `app/build/outputs/apk/debug/app-debug.apk`.
- APK release đã ký: Build → Generate Signed App Bundle/APK với keystore gốc.

---

## 13. Cây repo tổng thể

Toàn bộ file trong zip, trừ phần source Kotlin. Cột bên phải tóm tắt nội dung chính của từng file; file nhị phân ghi kèm dung lượng.

```
assignment-2-group-gingerbread-mad-main/
├── .gitignore                    (*.iml, .gradle, local.properties, .idea/, /build, *.apk, *.ap_, *.dex, *.class,
│                                  *.log, *.jks, *.keystore, Thumbs.db ...)
├── README.md                     badge "Review Assignment Due Date" của GitHub Classroom
├── README.txt                    Readme nộp bài: team, app access, features theo UC, tech stack, testing,
│                                  unimplemented, how to run (cài APK), link video demo
├── build.gradle.kts              plugins apply false: android.application, kotlin.compose, ksp, hilt
├── settings.gradle.kts           repos google/mavenCentral/gradlePluginPortal, foojay-resolver 1.0.0,
│                                  rootProject.name = "gingerbread_PoDManager", include(":app")
├── gradle.properties             jvmargs -Xmx2048m, configuration-cache=true, kotlin.code.style=official,
│                                  ⚠️ android.disallowKotlinSourceSets=false
├── gradlew, gradlew.bat          Gradle wrapper script (trong zip gradlew không có quyền thực thi)
├── gingerbread_PoD.apk           (19.7 MB, nhị phân) APK release đã ký — force-add dù .gitignore có *.apk
├── gradle/
│   ├── libs.versions.toml        version catalog (bảng §2.1)
│   ├── gradle-daemon-jvm.properties   toolchainVersion=21 + URL foojay theo OS
│   └── wrapper/
│       ├── gradle-wrapper.properties  gradle-9.7.1-bin.zip
│       └── gradle-wrapper.jar         (46 KB, nhị phân)
└── app/
    ├── .gitignore                /build, Assignment Info Docs/
    ├── build.gradle.kts          android {…} + dependencies (§2.2); đọc MAPS_API_KEY từ local.properties
    └── src/
        ├── main/
        │   ├── AndroidManifest.xml    quyền, MainActivity singleTop + splash, DeliveryTrackingService
        │   │                          (location), meta-data Maps key, gỡ InitializationProvider (§2.3)
        │   ├── ic_launcher-playstore.png   (26 KB, nhị phân) — không tham gia build
        │   ├── keepRules/rules.keep        keep rules mặc định (chỉ comment)
        │   ├── java/com/example/gingerbread_podmanager/   → §14
        │   └── res/
        │       ├── drawable/
        │       │   ├── ic_launcher_background.xml   vector nền icon mặc định của template (#3DDC84)
        │       │   └── ic_launcher_foreground.xml   vector mặc định của template — dùng làm small icon notification tracking
        │       ├── mipmap-anydpi-v26/
        │       │   ├── ic_launcher.xml         adaptive icon: nền @color/ic_launcher_background,
        │       │   └── ic_launcher_round.xml   foreground + monochrome @mipmap/ic_launcher_foreground
        │       ├── mipmap-mdpi/ · mipmap-hdpi/ · mipmap-xhdpi/ · mipmap-xxhdpi/ · mipmap-xxxhdpi/
        │       │   └── ic_launcher.webp, ic_launcher_round.webp, ic_launcher_foreground.webp
        │       │       (15 file nhị phân, sinh bằng Image Asset Studio từ logo pin + xe tải)
        │       ├── values/
        │       │   ├── colors.xml               splash_background #B45309 + màu template
        │       │   ├── font_certs.xml           chứng chỉ Google Fonts provider (dev + prod)
        │       │   ├── ic_launcher_background.xml   #92400E
        │       │   ├── strings.xml              app_name "Gingerbread PoD", tracking_channel_name,
        │       │   │                            tracking_notification_title, tracking_notification_text
        │       │   └── themes.xml               Theme.Gingerbread_PoDManager (Material.Light.NoActionBar),
        │       │                                Theme.App.Starting (SplashScreen → postSplashScreenTheme)
        │       └── xml/
        │           ├── backup_rules.xml          template
        │           └── data_extraction_rules.xml template
        ├── test/java/com/example/gingerbread_podmanager/          → §14 (5 file, 36 unit test)
        └── androidTest/java/com/example/gingerbread_podmanager/   → §14 (1 file, 2 Compose UI test)
```

---

## 14. Cây source Kotlin chi tiết (class · fun · @Composable)

Quy ước: `val:` = property public/override · `fun:` = hàm public · `private:` = hàm private trong class · `@Composable:` = composable public · `private @Composable:` = composable nội bộ file · `top-level:` = hằng/biến cấp file · `[@…]` = annotation Hilt/Room chính. Data class ghi kèm danh sách field. Không liệt kê hàm cục bộ bên trong hàm/composable.

```
app/src/main/java/com/example/gingerbread_podmanager/   (main: 8,161 dòng Kotlin)
├── core/   (556 dòng)
│   ├── common/
│   │   └── .gitkeep   (rỗng)
│   ├── database/
│   │   ├── dao/
│   │   │   └── DeliveryDao.kt   (25 dòng)
│   │   │           interface DeliveryDao  [@Dao]
│   │   │             fun: getById(), upsert(), getByStatus(), observeAll(), getAll()
│   │   ├── entity/
│   │   │   └── DeliveryEntity.kt   (13 dòng)
│   │   │           data class DeliveryEntity(id, recipientName, address, status, createdAtEpochMillis)  [@Entity]
│   │   └── AppDatabase.kt   (45 dòng)
│   │           abstract class AppDatabase
│   │             fun: deliveryDao(), signatureDao(), photoDao(), syncQueueDao()
│   │             companion object
│   │               const: DATABASE_NAME
│   ├── di/
│   │   ├── DatabaseModule.kt   (46 dòng)
│   │   │       object DatabaseModule  [@Module]
│   │   │         fun: provideAppDatabase(), provideDeliveryDao(), provideSignatureDao(), providePhotoDao(),
│   │   │              provideSyncQueueDao()
│   │   └── LocationModule.kt   (23 dòng)
│   │           object LocationModule  [@Module]
│   │             fun: provideFusedLocationProviderClient()
│   ├── domain/
│   │   └── model/
│   │       ├── DeliveryStatus.kt   (15 dòng)
│   │       │       enum class DeliveryStatus { PENDING, IN_TRANSIT, DELIVERED, FAILED }
│   │       └── UserRole.kt   (15 dòng)
│   │               enum class UserRole { SHIPPER, MANAGER }
│   ├── theme/
│   │   ├── Color.kt   (218 dòng)
│   │   │       70 top-level val màu Material 3 (bộ <role>Light + <role>Dark)
│   │   ├── Theme.kt   (110 dòng)
│   │   │       @Composable: Gingerbread_PoDManagerTheme()
│   │   │       top-level: LightColorScheme, DarkColorScheme
│   │   └── Type.kt   (46 dòng)
│   │           top-level: fontProvider, interFont, displayFontFamily, outfitFont, bodyFontFamily, baseline, AppTypography
│   └── util/
│       └── .gitkeep   (rỗng)
├── di/   (96 dòng)
│   ├── ManifestModule.kt   (61 dòng)
│   │       abstract class ManifestBindingModule  [@Module]
│   │         fun: bindManifestRepository()
│   │       object ManifestUseCaseModule  [@Module]
│   │         fun: provideGetManifestUseCase(), provideGetStopDetailUseCase(), provideVerifyTrackingNumberUseCase(),
│   │              provideUpdateStopStatusUseCase(), provideVerifyStopOcrUseCase()
│   └── SessionModule.kt   (35 dòng)
│           object SessionDataStoreModule  [@Module]
│             fun: provideSessionDataStore()
│           abstract class SessionBindingModule  [@Module]
│             fun: bindSessionRepository()
│           top-level: Context.sessionDataStore
├── feature_auth/   (243 dòng, Sprint 3 · Login/role)
│   ├── data/
│   │   └── repository/
│   │       └── SessionRepositoryImpl.kt   (34 dòng)
│   │               class SessionRepositoryImpl
│   │                 val: userRole
│   │                 fun: setRole(), clearRole()
│   │               top-level: KEY_USER_ROLE
│   ├── domain/
│   │   └── repository/
│   │       └── SessionRepository.kt   (25 dòng)
│   │               interface SessionRepository
│   │                 val: userRole
│   │                 fun: setRole(), clearRole()
│   └── presentation/
│       ├── LoginScreen.kt   (133 dòng)
│       │       @Composable: LoginScreen()
│       │       top-level: ROLE_BUTTON_SCREEN_HEIGHT_FRACTION, ROLE_BUTTON_ICON_SIZE, ROLE_BUTTON_SHAPE
│       └── SessionViewModel.kt   (51 dòng)
│               sealed interface SessionUiState
│                 └ Loading · Ready
│               class SessionViewModel  [@HiltViewModel]
│                 val: uiState
│                 fun: selectRole(), logout()
├── feature_manifest/   (3,690 dòng, UC-01 · Member A)
│   ├── data/
│   │   ├── ocr/
│   │   │   └── OcrScanner.kt   (74 dòng)
│   │   │           class OcrScanner  [@Singleton]
│   │   │             fun: scanBitmap(), scanImageProxy()
│   │   └── repository/
│   │       └── ManifestRepositoryImpl.kt   (448 dòng)
│   │               class ManifestRepositoryImpl  [@Singleton]
│   │                 fun: getDeliveryStops(), getDeliveryStopById(), updateStopStatus(), markStopOcrVerified()
│   │                 private: seedInitialStopsIntoDatabase(), observeRoomDeliveryStatusUpdates()
│   │                 companion object
│   │                   private: createInitialMockStops()
│   ├── domain/
│   │   ├── model/
│   │   │   ├── DeliveryStop.kt   (25 dòng)
│   │   │   │       data class DeliveryStop(id, orderNumber, trackingNumber, recipientName, recipientPhone, deliveryAddress,
│   │   │   │         latitude, longitude, stopOrder, estimatedArrival, specialInstructions, items, status, isVerifiedWithOcr)
│   │   │   ├── OcrVerificationResult.kt   (44 dòng)
│   │   │   │       sealed class OcrVerificationResult
│   │   │   │         ├ Match(matchedTrackingNumber, rawRecognizedText, matchedStop)
│   │   │   │         ├ WrongStopMatch(matchedTrackingNumber, rawRecognizedText, actualStop, expectedStop)
│   │   │   │         ├ Mismatch(expectedTrackingNumber, foundCandidates, rawRecognizedText)
│   │   │   │         ├ NotFoundInManifest(scannedTrackingNumber, foundCandidates, rawRecognizedText)
│   │   │   │         └ NoTextFound(message)
│   │   │   └── PackageItem.kt   (13 dòng)
│   │   │           data class PackageItem(id, name, quantity, weightKg, description)
│   │   ├── repository/
│   │   │   └── ManifestRepository.kt   (16 dòng)
│   │   │           interface ManifestRepository
│   │   │             fun: getDeliveryStops(), getDeliveryStopById(), updateStopStatus(), markStopOcrVerified()
│   │   └── usecase/
│   │       ├── GetManifestUseCase.kt   (16 dòng)
│   │       │       class GetManifestUseCase
│   │       │         fun: invoke()
│   │       ├── GetStopDetailUseCase.kt   (16 dòng)
│   │       │       class GetStopDetailUseCase
│   │       │         fun: invoke()
│   │       ├── UpdateStopStatusUseCase.kt   (15 dòng)
│   │       │       class UpdateStopStatusUseCase
│   │       │         fun: invoke()
│   │       ├── VerifyStopOcrUseCase.kt   (14 dòng)
│   │       │       class VerifyStopOcrUseCase
│   │       │         fun: invoke()
│   │       └── VerifyTrackingNumberUseCase.kt   (151 dòng)
│   │               class VerifyTrackingNumberUseCase
│   │                 fun: invoke()
│   │                 private: normalizeCode()
│   └── presentation/
│       ├── components/
│       │   ├── DeliveryPinMarker.kt   (109 dòng)
│       │   │       @Composable: DeliveryPinMarker()
│       │   ├── ManifestSearchBar.kt   (338 dòng)
│       │   │       @Composable: ManifestSearchBar()
│       │   │       private @Composable: SearchSuggestionItem()
│       │   ├── OcrScannerDialog.kt   (670 dòng)
│       │   │       @Composable: OcrScannerDialog()
│       │   │       private @Composable: CameraLivePreview()
│       │   │       private fun: createMockLabelBitmap()
│       │   ├── StopDetailSheet.kt   (495 dòng)
│       │   │       @Composable: StopDetailSheet()
│       │   ├── StopListItem.kt   (251 dòng)
│       │   │       @Composable: StopListItem()
│       │   └── StopStatusChip.kt   (60 dòng)
│       │           @Composable: StopStatusChip()
│       ├── model/
│       │   └── DeliveryStopClusterItem.kt   (24 dòng)
│       │           data class DeliveryStopClusterItem(stop)
│       │             val: position, title, snippet, zIndex
│       ├── ManifestScreen.kt   (593 dòng)
│       │       @Composable: ManifestScreen()
│       │       private @Composable: SelectedStopQuickCard()
│       ├── ManifestUiState.kt   (40 dòng)
│       │       data class ManifestUiState(stops, selectedStop, isLoading, errorMessage, isDetailSheetVisible,
│       │         isOcrScannerVisible, isOcrProcessing, ocrResult, isListView, searchQuery)
│       │         val: filteredStops, completedCount, totalCount
│       └── ManifestViewModel.kt   (278 dòng)
│               class ManifestViewModel  [@HiltViewModel]
│                 val: uiState
│                 fun: selectStop(), openStopDetail(), closeStopDetail(), openOcrScanner(), openQuickScan(), closeOcrScanner(),
│                      switchToStop(), processOcrBitmap(), processOcrImageProxy(), confirmManualVerification(),
│                      updateStopStatus(), toggleViewMode(), onSearchQueryChange(), clearErrorMessage()
│                 private: loadManifest()
├── feature_pod/   (1,507 dòng, UC-03 · Member C)
│   ├── data/
│   │   ├── local/
│   │   │   ├── dao/
│   │   │   │   ├── PhotoDao.kt   (18 dòng)
│   │   │   │   │       interface PhotoDao  [@Dao]
│   │   │   │   │         fun: observeForDelivery(), insert()
│   │   │   │   └── SignatureDao.kt   (18 dòng)
│   │   │   │           interface SignatureDao  [@Dao]
│   │   │   │             fun: observeForDelivery(), insert()
│   │   │   ├── entity/
│   │   │   │   ├── PhotoEntity.kt   (29 dòng)
│   │   │   │   │       data class PhotoEntity(id, deliveryId, imageFilePath, capturedAtEpochMillis, latitude, longitude)
│   │   │   │   └── SignatureEntity.kt   (29 dòng)
│   │   │   │           data class SignatureEntity(id, deliveryId, imageFilePath, capturedAtEpochMillis, latitude, longitude)
│   │   │   ├── location/
│   │   │   │   ├── FusedLocationProvider.kt   (50 dòng)
│   │   │   │   │       class FusedLocationProvider
│   │   │   │   │         fun: getLastKnownLocation()
│   │   │   │   │         private: hasLocationPermission()
│   │   │   │   └── TrackingAwareLocationProvider.kt   (21 dòng)
│   │   │   │           class TrackingAwareLocationProvider
│   │   │   │             fun: getLastKnownLocation()
│   │   │   └── storage/
│   │   │       ├── FilePhotoStore.kt   (26 dòng)
│   │   │       │       class FilePhotoStore
│   │   │       │         fun: createOutputFilePath()
│   │   │       └── FileSignatureImageStore.kt   (28 dòng)
│   │   │               class FileSignatureImageStore
│   │   │                 fun: savePng()
│   │   ├── mapper/
│   │   │   └── PodMappers.kt   (71 dòng)
│   │   │           fun: DeliveryEntity.toDomain(), Delivery.toEntity(), SignatureEntity.toDomain(), Signature.toEntity(),
│   │   │                PhotoEntity.toDomain(), Photo.toEntity()
│   │   └── repository/
│   │       └── PodRepositoryImpl.kt   (46 dòng)
│   │               class PodRepositoryImpl
│   │                 fun: getDelivery(), getDeliveriesByStatus(), saveDelivery(), observeSignatures(), observePhotos(),
│   │                      saveSignature(), savePhoto()
│   ├── di/
│   │   └── PodModule.kt   (38 dòng)
│   │           abstract class PodModule  [@Module]
│   │             fun: bindPodRepository(), bindLocationProvider(), bindSignatureImageStore(), bindPhotoFileStore()
│   ├── domain/
│   │   ├── model/
│   │   │   ├── Delivery.kt   (16 dòng)
│   │   │   │       data class Delivery(id, recipientName, address, status, createdAtEpochMillis)
│   │   │   ├── GeoPoint.kt   (11 dòng)
│   │   │   │       data class GeoPoint(latitude, longitude)
│   │   │   ├── Photo.kt   (12 dòng)
│   │   │   │       data class Photo(id, deliveryId, imageFilePath, capturedAtEpochMillis, location)
│   │   │   └── Signature.kt   (14 dòng)
│   │   │           data class Signature(id, deliveryId, imageFilePath, capturedAtEpochMillis, location)
│   │   ├── repository/
│   │   │   ├── LocationProvider.kt   (13 dòng)
│   │   │   │       interface LocationProvider
│   │   │   │         fun: getLastKnownLocation()
│   │   │   ├── PhotoFileStore.kt   (13 dòng)
│   │   │   │       interface PhotoFileStore
│   │   │   │         fun: createOutputFilePath()
│   │   │   ├── PodRepository.kt   (29 dòng)
│   │   │   │       interface PodRepository
│   │   │   │         fun: getDelivery(), getDeliveriesByStatus(), saveDelivery(), observeSignatures(), observePhotos(),
│   │   │   │              saveSignature(), savePhoto()
│   │   │   └── SignatureImageStore.kt   (14 dòng)
│   │   │           interface SignatureImageStore
│   │   │             fun: savePng()
│   │   └── usecase/
│   │       ├── CompleteDeliveryUseCase.kt   (43 dòng)
│   │       │       class CompleteDeliveryUseCase
│   │       │         fun: invoke()
│   │       │         sealed class Result
│   │       │           └ Success · Failure
│   │       ├── SavePhotoUseCase.kt   (32 dòng)
│   │       │       class SavePhotoUseCase
│   │       │         fun: invoke()
│   │       └── SaveSignatureUseCase.kt   (32 dòng)
│   │               class SaveSignatureUseCase
│   │                 fun: invoke()
│   └── presentation/
│       ├── photo/
│       │   ├── CameraPreview.kt   (59 dòng)
│       │   │       @Composable: CameraPreview()
│       │   ├── PhotoCaptureScreen.kt   (111 dòng)
│       │   │       @Composable: PhotoCaptureScreen()
│       │   │       top-level: PREVIEW_HEIGHT_DP
│       │   └── PhotoCaptureViewModel.kt   (92 dòng)
│       │           data class PhotoCaptureUiState(isCapturing, lastSavedPhoto, error)
│       │           class PhotoCaptureViewModel  [@HiltViewModel]
│       │             val: uiState
│       │             fun: capturePhoto(), dismissError()
│       │             private: onPhotoWritten()
│       ├── signature/
│       │   ├── SignatureCanvas.kt   (67 dòng)
│       │   │       @Composable: SignatureCanvas()
│       │   ├── SignatureCanvasState.kt   (39 dòng)
│       │   │       class SignatureCanvasState
│       │   │         val: strokes, hasStrokes
│       │   │         fun: beginStroke(), appendToCurrentStroke(), clear()
│       │   ├── SignatureCaptureScreen.kt   (136 dòng)
│       │   │       @Composable: SignatureCaptureScreen()
│       │   │       top-level: SIGNATURE_STROKE_WIDTH_DP, SIGNATURE_CANVAS_TEST_TAG, SIGNATURE_CANVAS_SHAPE
│       │   ├── SignatureCaptureViewModel.kt   (66 dòng)
│       │   │       data class SignatureCaptureUiState(isSaving, lastSavedSignature, error)
│       │   │       class SignatureCaptureViewModel  [@HiltViewModel]
│       │   │         val: uiState
│       │   │         fun: saveSignature(), consumeSavedSignature(), dismissError()
│       │   ├── SignaturePathUtil.kt   (35 dòng)
│       │   │       fun: buildSmoothPath()
│       │   └── SignatureRasterizer.kt   (48 dòng)
│       │           fun: SignatureCanvasState.rasterizeToBitmap(), Bitmap.toPngBytes()
│       ├── PodCaptureScreen.kt   (168 dòng)
│       │       @Composable: PodCaptureScreen()
│       │       top-level: SAMPLE_DELIVERY_ID
│       └── PodCaptureViewModel.kt   (83 dòng)
│               data class PodCaptureUiState(isCompleting, isCompleted, completionError)
│               class PodCaptureViewModel  [@HiltViewModel]
│                 val: uiState
│                 fun: ensureDeliveryExists(), completeDelivery(), dismissCompletionError()
├── feature_sync/   (893 dòng, UC-04 · Member D (Hòa))
│   ├── data/
│   │   ├── local/
│   │   │   ├── dao/
│   │   │   │   └── SyncQueueDao.kt   (22 dòng)
│   │   │   │           interface SyncQueueDao  [@Dao]
│   │   │   │             fun: getByDeliveryId(), getByStatus(), getAll(), upsert()
│   │   │   └── entity/
│   │   │       └── SyncQueueEntity.kt   (33 dòng)
│   │   │               data class SyncQueueEntity(id, deliveryId, status, retryCount, lastAttemptEpochMillis)
│   │   ├── mapper/
│   │   │   └── SyncMappers.kt   (28 dòng)
│   │   │           fun: SyncQueueEntity.toDomain(), SyncQueueItem.toEntity()
│   │   ├── pdf/
│   │   │   └── AndroidPdfGenerator.kt   (180 dòng)
│   │   │           class AndroidPdfGenerator
│   │   │             fun: generateDeliveryCertificate()
│   │   │             private: aspectFitRect(), loadRotatedBitmap(), saveToMediaStoreDownloads(), saveToLegacyDownloadsDir()
│   │   ├── receiver/
│   │   │   └── NetworkStateReceiver.kt   (26 dòng)
│   │   │           class NetworkStateReceiver
│   │   │             fun: onReceive()
│   │   ├── repository/
│   │   │   └── SyncRepositoryImpl.kt   (26 dòng)
│   │   │           class SyncRepositoryImpl
│   │   │             fun: getQueueItem(), getItemsByStatus(), getAllItems(), saveItem()
│   │   ├── upload/
│   │   │   └── StubDeliveryUploader.kt   (17 dòng)
│   │   │           class StubDeliveryUploader
│   │   │             fun: upload()
│   │   └── worker/
│   │       └── SyncWorker.kt   (36 dòng)
│   │               class SyncWorker  [@HiltWorker]
│   │                 fun: doWork()
│   ├── di/
│   │   └── SyncModule.kt   (29 dòng)
│   │           abstract class SyncModule  [@Module]
│   │             fun: bindSyncRepository(), bindDeliveryUploader(), bindPdfGenerator()
│   ├── domain/
│   │   ├── model/
│   │   │   ├── SyncQueueItem.kt   (14 dòng)
│   │   │   │       data class SyncQueueItem(id, deliveryId, status, retryCount, lastAttemptEpochMillis)
│   │   │   └── SyncStatus.kt   (11 dòng)
│   │   │           enum class SyncStatus { QUEUED, SYNCING, FAILED, DONE }
│   │   ├── repository/
│   │   │   ├── DeliveryUploader.kt   (11 dòng)
│   │   │   │       interface DeliveryUploader
│   │   │   │         fun: upload()
│   │   │   ├── PdfGenerator.kt   (22 dòng)
│   │   │   │       interface PdfGenerator
│   │   │   │         fun: generateDeliveryCertificate()
│   │   │   └── SyncRepository.kt   (15 dòng)
│   │   │           interface SyncRepository
│   │   │             fun: getQueueItem(), getItemsByStatus(), getAllItems(), saveItem()
│   │   └── usecase/
│   │       ├── EnqueuePendingDeliveriesUseCase.kt   (34 dòng)
│   │       │       class EnqueuePendingDeliveriesUseCase
│   │       │         fun: invoke()
│   │       ├── ExportDeliveryCertificateUseCase.kt   (39 dòng)
│   │       │       class ExportDeliveryCertificateUseCase
│   │       │         fun: invoke()
│   │       │         sealed class Result
│   │       │           └ Success · Failure
│   │       └── ProcessSyncQueueUseCase.kt   (39 dòng)
│   │               class ProcessSyncQueueUseCase
│   │                 fun: invoke()
│   └── presentation/
│       ├── DispatcherDashboardScreen.kt   (200 dòng)
│       │       @Composable: DispatcherDashboardScreen()
│       │       private @Composable: DriverStatusCard(), DeliveryRow()
│       │       top-level: IN_TRANSIT_LABEL_COLOR
│       └── DispatcherDashboardViewModel.kt   (111 dòng)
│               data class DashboardDeliveryRow(delivery, syncStatus)
│               data class DashboardUiState(isLoading, isSyncing, rows, lastExportedPdfPath, error)
│               class DispatcherDashboardViewModel  [@HiltViewModel]
│                 val: uiState, isDriverTracking, driverCurrentLocation
│                 fun: refresh(), syncNow(), exportCertificate(), dismissError()
├── feature_tracking/   (721 dòng, UC-02 · Member B)
│   ├── data/
│   │   ├── notification/
│   │   │   └── TrackingNotificationHelper.kt   (55 dòng)
│   │   │           object TrackingNotificationHelper
│   │   │             const: CHANNEL_ID, NOTIFICATION_ID, EXTRA_OPEN_TRACKING
│   │   │             fun: ensureChannel(), buildNotification()
│   │   ├── repository/
│   │   │   └── LocationTrackingRepositoryImpl.kt   (100 dòng)
│   │   │           class LocationTrackingRepositoryImpl  [@Singleton]
│   │   │             val: isTracking, currentLocation, destinationLabel, destinationLocation
│   │   │             fun: startTracking(), stopTracking(), updateDestination()
│   │   └── service/
│   │       └── DeliveryTrackingService.kt   (101 dòng)
│   │               class DeliveryTrackingService  [@AndroidEntryPoint]
│   │                 val: fusedLocationClient, currentLocation
│   │                 fun: onCreate(), onStartCommand(), onBind(), onDestroy()
│   │                 private: startLocationUpdates()
│   │                 class LocalBinder
│   │                   fun: getService()
│   │                 companion object
│   │                   const: EXTRA_DESTINATION_LABEL, LOCATION_INTERVAL_MS
│   ├── di/
│   │   └── TrackingModule.kt   (21 dòng)
│   │           abstract class TrackingModule  [@Module]
│   │             fun: bindLocationTrackingRepository()
│   ├── domain/
│   │   ├── model/
│   │   │   ├── DestinationPoint.kt   (7 dòng)
│   │   │   │       data class DestinationPoint(latitude, longitude)
│   │   │   └── TrackedLocation.kt   (8 dòng)
│   │   │           data class TrackedLocation(latitude, longitude, timestampEpochMillis)
│   │   └── repository/
│   │       └── LocationTrackingRepository.kt   (31 dòng)
│   │               interface LocationTrackingRepository
│   │                 val: isTracking, currentLocation, destinationLabel, destinationLocation
│   │                 fun: startTracking(), stopTracking(), updateDestination()
│   └── presentation/
│       ├── TrackingScreen.kt   (309 dòng)
│       │       @Composable: TrackingScreen()
│       │       private @Composable: TrackingStatusCard()
│       │       private fun: formatElapsed(), formatLocation()
│       │       top-level: PLACEHOLDER_DESTINATION, DEFAULT_CAMERA_TARGET, FOLLOW_ZOOM
│       └── TrackingViewModel.kt   (89 dòng)
│               class TrackingViewModel  [@HiltViewModel]
│                 val: isTracking, currentLocation, destinationLabel, destinationLocation, elapsedSeconds
│                 fun: startDeliveryRoute(), stopDeliveryRoute(), updateDestination()
├── navigation/   (330 dòng)
│   └── NavGraph.kt   (330 dòng)
│           sealed class Screen
│             data object Manifest
│             data object Tracking
│               val: routeWithArgs
│               fun: createRoute()
│             data object Pod
│               val: routeWithArgs
│               fun: createRoute()
│             companion object
│               val: shipperBottomNavItems
│           @Composable: AppNavGraph()
│           private @Composable: ShipperFlow(), ManagerFlow(), LogoutAction(), ShipperBottomNavigationBar()
│           top-level: PLACEHOLDER_DELIVERY_ID
├── GingerbreadApp.kt   (27 dòng)
│       class GingerbreadApp  [@HiltAndroidApp]
│         val: workerFactory, workManagerConfiguration
└── MainActivity.kt   (98 dòng)
        class MainActivity  [@AndroidEntryPoint]
          fun: onCreate(), onNewIntent(), onStart(), onStop()
          private: handleIntent(), enqueuePeriodicSync()
        top-level: SYNC_WORK_NAME

app/src/test/java/com/example/gingerbread_podmanager/   (unit test JVM: 36 test)
├── feature_manifest/
│   ├── domain/
│   │   └── usecase/
│   │       └── VerifyTrackingNumberUseCaseTest.kt   (189 dòng)
│   │               class VerifyTrackingNumberUseCaseTest
│   │                 fun:
│   │                   - setUp()
│   │                   - `invoke with exact tracking number returns Match`
│   │                   - `invoke with noisy label text containing tracking number returns Match`
│   │                   - `invoke with lowercase or hyphen spacing returns Match`
│   │                   - `invoke with different tracking number returns Mismatch`
│   │                   - `invoke with empty or blank text returns NoTextFound`
│   │                   - `invoke with order number matching expected stop returns Match`
│   │                   - `invoke with package belonging to another stop returns WrongStopMatch`
│   │                   - `invoke with order number belonging to another stop returns WrongStopMatch`
│   │                   - `invoke without expected tracking number matches stop in manifest`
│   │                   - `invoke without expected tracking number returns NotFoundInManifest when not found`
│   │                 private: createSampleStop()
│   └── presentation/
│       └── ManifestViewModelTest.kt   (167 dòng)
│               class ManifestViewModelTest
│                 fun:
│                   - setUp()
│                   - tearDown()
│                   - `initial state loads manifest stops successfully`
│                   - `selectStop updates selectedStop in state`
│                   - `onSearchQueryChange filters stops by recipient name or address`
│                   - `updateStopStatus updates stop to DELIVERED and reflects in counts`
│                   - `confirmManualVerification sets isVerifiedWithOcr to true`
│                   - `openOcrScanner sets isOcrScannerVisible to true and closes detail sheet`
│                   - `switchToStop selects new stop, closes OCR scanner, and opens detail sheet`
│                   - `openQuickScan clears selectedStop and opens OCR scanner`
├── feature_pod/
│   └── domain/
│       └── usecase/
│           └── CompleteDeliveryUseCaseTest.kt   (131 dòng)
│                   class FakePodRepository
│                     fun: getDelivery(), getDeliveriesByStatus(), saveDelivery(), observeSignatures(), observePhotos(),
│                          saveSignature(), savePhoto()
│                   class CompleteDeliveryUseCaseTest
│                     fun:
│                       - setUp()
│                       - `fails when delivery does not exist`
│                       - `fails when signature missing`
│                       - `fails when photo missing`
│                       - `succeeds and marks delivery DELIVERED when both artifacts present`
│                     private: sampleSignature(), samplePhoto()
└── feature_sync/
    ├── domain/
    │   └── usecase/
    │       └── ProcessSyncQueueUseCaseTest.kt   (102 dòng)
    │               class FakeSyncRepository
    │                 fun: getQueueItem(), getItemsByStatus(), getAllItems(), saveItem()
    │               class FakeDeliveryUploader
    │                 fun: upload()
    │               class ProcessSyncQueueUseCaseTest
    │                 fun:
    │                   - setUp()
    │                   - `successful upload marks item DONE`
    │                   - `failed upload marks item FAILED and increments retryCount`
    │                   - `previously FAILED item is retried and can succeed`
    │                   - `DONE items are not reprocessed`
    │                   - `multiple queued items are processed independently`
    └── presentation/
        └── DispatcherDashboardViewModelTest.kt   (305 dòng)
                class FakePodRepository
                  fun: getDelivery(), getDeliveriesByStatus(), saveDelivery(), observeSignatures(), observePhotos(),
                       saveSignature(), savePhoto()
                class FakeSyncRepository
                  fun: getQueueItem(), getItemsByStatus(), getAllItems(), saveItem()
                class FakeDeliveryUploader
                  fun: upload()
                class FakePdfGenerator
                  fun: generateDeliveryCertificate()
                class FakeLocationTrackingRepository   ⚠️ thiếu override updateDestination() → test không compile (§9 mục 1)
                  fun: startTracking(), stopTracking(), emitLocation()
                class DispatcherDashboardViewModelTest
                  fun:
                    - setUp()
                    - tearDown()
                    - `refresh loads IN_TRANSIT and DELIVERED deliveries, only DELIVERED carries a sync status`
                    - `PENDING and FAILED deliveries are not shown on the dashboard`
                    - `row shows null sync status when delivery has no queue entry yet`
                    - `isDriverTracking reflects LocationTrackingRepository state`
                    - `driverCurrentLocation reflects latest GPS fix, null when not tracking`
                    - `syncNow enqueues and processes, then refreshes rows`
                    - `exportCertificate sets lastExportedPdfPath on success`
                    - `exportCertificate sets error when delivery not found`
                    - `dismissError clears error message`
                  private: createViewModel(), sampleDelivery()

app/src/androidTest/java/com/example/gingerbread_podmanager/   (Compose UI test: 2 test)
└── feature_pod/
    └── presentation/
        └── signature/
            └── SignatureCaptureScreenTest.kt   (170 dòng)
                    class FakePodRepository
                      fun: getDelivery(), getDeliveriesByStatus(), saveDelivery(), observeSignatures(), observePhotos(),
                           saveSignature(), savePhoto()
                    class FakeSignatureImageStore
                      fun: savePng()
                    class FakeLocationProvider
                      fun: getLastKnownLocation()
                    class SignatureCaptureScreenTest
                      fun: setUp(), signatureCapture_drawThenSave_persistsSignatureAndResetsCanvas(),
                           signatureCapture_clearButton_removesDrawnStrokeWithoutSaving()
```