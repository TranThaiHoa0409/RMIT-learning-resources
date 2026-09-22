# MAD Online Components Summary — All Weeks

## Week 1 — Kotlin vs Java: 10 điểm khác biệt chính

| # | Tính năng | Kotlin | Java |
|---|---|---|---|
| 1 | Null Safety | `String?` kiểm tra compile-time | Mọi reference đều có thể null, lỗi runtime (NPE) |
| 2 | Extension Function | Có, thêm hàm vào class có sẵn (`fun String.lastChar()`) | Không có, phải dùng static utility method |
| 3 | Data Class | `data class` tự sinh `equals/hashCode/toString/copy` | Phải viết tay hoặc dùng Lombok (~15-20 dòng) |
| 4 | Smart Cast | Tự động cast sau `is` | Phải cast thủ công (hoặc pattern matching từ Java 16+) |
| 5 | Coroutines | Có sẵn (`suspend`, `launch`, `delay`), nhẹ hơn thread thật | Không có sẵn, dùng Thread/CompletableFuture (hoặc Virtual Threads từ Java 21) |
| 6 | Sealed Class | Có từ đầu, `when` exhaustive không cần `else` | Chỉ có `sealed` từ Java 17+ |
| 7 | Default/Named Arguments | Có (`fun greet(name: String = "Guest")`) | Không có, phải overload method |
| 8 | Destructuring | Có (`val (name) = user`) | Không có, kể cả với `record` |
| 9 | Higher-Order Functions | Cú pháp gọn, trailing lambda ngoài ngoặc | Cần functional interface (`BiFunction`), cú pháp rườm rà hơn |
| 10 | Type Inference | `var`/`val` mặc định phân biệt mutable/immutable | Cần từ khóa `var` (Java 10+), cần `final var` để tương đương `val` |

## Tutorial 3 (Online) — Clean Architecture + Navigation Compose (Task App)

- Kiến trúc 3 lớp: `presentation → domain ← data` — cả `data` và `presentation` phụ thuộc `domain`, `domain` không phụ thuộc ngược lại (giữ business logic testable, độc lập UI/nguồn dữ liệu).
- **Domain**: `Task` (model), `TaskRepository` (interface), 3 use case (`GetTasksUseCase`, `AddTaskUseCase`, `DeleteTaskUseCase`) — ViewModel gọi qua use case, không gọi thẳng repository.
- **Data**: `FakeTaskDataSource` (in-memory, chạy được app end-to-end chưa cần backend thật) + `TaskRepositoryImpl` (implement interface domain, wire vào fake data source) — đây là lớp sẽ thay bằng Room/API thật sau này mà không đụng tới domain/presentation.
- **Presentation**: `TaskViewModel` (state + gọi use case) + Navigation Compose (`TaskNavRoutes`, `NavGraph` với `NavHost`) + 3 screen (`HomeScreen`, `AddTaskScreen`, `TaskDetailScreen`), mỗi screen nhận `ViewModel`/`NavController` để đọc state và điều hướng.
- **Setup cần thêm thủ công** cho project "No Activity" mới: 2 dependency `androidx-navigation-compose` và `androidx-lifecycle-viewmodel-compose` (cả trong `libs.versions.toml` và `app/build.gradle.kts`) — thiếu sẽ không build được Navigation Compose/ViewModel-Compose integration.

## Tutorial 4 (Online) — RESTful Student App

- Kiến trúc MVVM 3 lớp (`data`/`presentation`) quản lý Student qua REST API.
- **Data**: `Student(id, name)` + `NewStudent(name)` (model tách riêng cho POST không có id); `StudentApi` (Retrofit interface: GET/POST/DELETE/PUT `/students`); `ApiClient` (singleton Retrofit, `baseUrl = http://10.0.2.2:3004/`, converter kotlinx.serialization).
- **Presentation**: `StudentApp` (`NavHost` 3 route: `studentList → addStudent → editStudent/{id}`); `StudentViewModel` (`StateFlow<List<Student>>` + `SharedFlow<String>` cho snackbar message, 4 hàm CRUD có try/catch riêng); `StudentListScreen`/`AddStudentScreen`/`EditStudentScreen`.
- **Lưu ý**: `MainActivity` dùng `MaterialTheme` mặc định, **không** dùng theme custom `RESTfulTheme` đã định nghĩa sẵn (chưa được áp dụng thực tế).
- **Setup bắt buộc thêm thủ công** ở project mới: `<uses-permission android:name="android.permission.INTERNET" />` + `android:usesCleartextTraffic="true"` trong Manifest (thiếu → gọi API fail ngay); plugin `kotlin.serialization` + dependency Retrofit/kotlinx.serialization/coroutines/navigation-compose/lifecycle-viewmodel-compose trong Gradle.
- `baseUrl 10.0.2.2:3004` chỉ đúng khi chạy Android Emulator — cần đổi IP/port nếu chạy thiết bị thật hoặc backend khác.

## Lecture 5 (Online) — Google Maps Compose Demo

- App demo rời rạc (không theo kiến trúc data/domain/presentation) — 5 composable độc lập minh hoạ từng API riêng (chọn chạy 1 cái bằng comment/uncomment trong `MainActivity.setContent`): `MapScreen` (bản đồ tĩnh + 1 marker), `AdvancedMapFeaturesDemo` (marker icon tuỳ chỉnh, polyline, click event, camera animate + `MapType.SATELLITE`), `PermissionRequestScreen` (xin quyền vị trí qua Accompanist), `LocationMapScreen` (lấy vị trí 1 lần), `ContinuousLocationMapScreen` (theo dõi vị trí liên tục — đang được chọn chạy).
- Không có ViewModel/DI/network call — toàn bộ state là `remember { mutableStateOf() }` cục bộ trong từng composable.
- Manifest thêm: `ACCESS_FINE_LOCATION`/`ACCESS_COARSE_LOCATION`, API key Google Maps (hardcode), `windowSoftInputMode="adjustResize"`.
- Dependency thêm (khai trực tiếp string literal, không qua version catalog): `maps-compose`, `play-services-maps`, `accompanist-permissions`, `play-services-location`.

## Tutorial 5 (Online) — Interactive Map (Restaurant + Location)

- Kiến trúc Clean Architecture 3 lớp + MVVM đầy đủ: `presentation (UI + ViewModel) → domain (Restaurant, RestaurantRepository, LocationTracker, UseCase) ← data (RestaurantRepositoryImpl, DefaultLocationTracker, ApiClient/Retrofit)`.
- **Domain**: `Restaurant` (model), `RestaurantRepository` (interface, trả `Result<T>`), `LocationTracker` (interface trả `Flow<LatLng>`), `GetRestaurantsUseCase`/`AddRestaurantUseCase` (dùng `operator invoke`).
- **Data**: `RestaurantDto` (kèm `toDomain()`/`toDto()`), `RestaurantApi` (Retrofit: GET/POST restaurants), `ApiClient` (OkHttp timeout 30s + Retrofit + GsonConverterFactory, baseUrl `10.0.2.2:3004`), `RestaurantRepositoryImpl`, `DefaultLocationTracker` (dùng `callbackFlow` bọc `FusedLocationProviderClient`, interval 3s).
- **Presentation**: `MapViewModel` (state `restaurants`/`currentLocation`, tự load + track vị trí trong `init{}`), `MapViewModelFactory` (composition root — wiring thủ công, không dùng Hilt/Koin), `InteractiveMapScreen` (GoogleMap với marker user + từng restaurant, FAB refresh, `onMapClick` thêm restaurant tên random để test nhanh), `RequestLocationPermission` (Accompanist Permissions, chỉ render nội dung chính khi đã granted).
- Manifest thêm: `INTERNET`/`ACCESS_FINE_LOCATION`/`ACCESS_COARSE_LOCATION`, API key Maps (hardcode), `networkSecurityConfig` (file mới `network_security_config.xml` — cho phép cleartext traffic tới `10.0.2.2` vì gọi `http://`, không phải `https://`).
- Dependency thêm: `play-services-maps`, `maps-compose`, `play-services-location`, `accompanist-permissions`, `retrofit` + `converter-gson`, `kotlinx-coroutines-android` (OkHttpClient là transitive của retrofit2, không cần khai riêng).

## Lecture 6 (Online) — Room + Hilt + DataStore (Task List)

- App demo 3 thành phần Jetpack tách biệt: **Room** (lưu local), **Hilt** (DI), **DataStore Preferences** (dark mode) — không theo kiến trúc nhiều lớp, mọi class chung 1 package gốc.
- Luồng: `MyApp (@HiltAndroidApp)` → `MainActivity (@AndroidEntryPoint)` → `TaskListScreen` → `TaskViewModel (@HiltViewModel, inject TaskDao)` → `TaskDao → AppDatabase (Room) → Task (Entity)`; `DatabaseModule (@Module)` cung cấp `AppDatabase`+`TaskDao` cho Hilt graph.
- **Room**: `Task` (entity 3 field), `TaskDao` (5 query suspend: getAll/getById/insert-REPLACE/update/delete), `AppDatabase` (có singleton pattern thủ công qua `getDatabase()` nhưng **không thực sự dùng** — Hilt cung cấp instance qua `DatabaseModule` mới là nguồn thật).
- **DataStore**: `PreferencesManager` — key boolean `dark_mode`, `saveDarkMode()`/`getDarkMode()` (trả `Flow<Boolean>`).
- **Lưu ý đáng chú ý**:
  - `TaskListScreen.kt` không có dòng `package` (nằm ở default package) — khác các file còn lại đều khai `package com.example.lecture06`; `MainActivity` phải `import TaskListScreen` không prefix.
  - `MainActivity` tự dựng `MaterialTheme(colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme())` trực tiếp, **không** dùng composable `Lecture06Theme` có sẵn (theme Purple/Pink trong `Color.kt` bị bỏ qua).
  - `hilt-navigation-compose` được thêm dependency nhưng code lấy ViewModel bằng `viewModel()` thường, chưa dùng `hiltViewModel()` — dependency có mặt nhưng chưa thực sự dùng tới.
- Setup Hilt: plugin `com.google.dagger.hilt.android` (root `apply false` + app module) + `kotlin-kapt`; dependency Room (`room-runtime`/`room-ktx`/`room-compiler` qua kapt), `datastore-preferences`, `hilt-android`/`hilt-compiler`, `hilt-navigation-compose`. Manifest thêm `android:name=".MyApp"`, không có permission nào (không gọi network).

## Tutorial 8 (Online) — Background Processing (Event Log + Broadcast + WorkManager)

- App minh hoạ 3 cơ chế nền: **Broadcast Receiver** (runtime + manifest), **WorkManager** (job định kỳ), **Notification** — mọi event ghi vào file log CSV cục bộ (scoped storage) và hiển thị lại trên `AppScreen`.
- Luồng: nút "I'm Happy"/"I'm Sad" → `sendBroadcast()` → `CustomEventReceiver` (runtime-registered) hoặc `SystemEventReceiver` (manifest-declared, lắng nghe `BATTERY_LOW`) → cả hai đều ghi vào `EventLogRepository` (CSV) + gọi `Notify.show()`; song song, `SummaryWorker` (WorkManager, mỗi 15 phút, ràng buộc pin không thấp) đọc số event gần đây và gửi thông báo tổng hợp nếu có.
- **File chính**: `Event` (model), `EventLogRepository` (append/readAll/recentCount, dùng `context.getExternalFilesDir(null)` nên không cần xin quyền đọc/ghi), `Notify` (helper channel + show notification), `CustomEventReceiver`/`SystemEventReceiver`, `SummaryWorker`, `MainActivity` (đã di chuyển vào package con `presentation` — khác vị trí mặc định).
- **Lưu ý**: `setContent { AppScreen() }` gọi thẳng, không bọc qua `Tutorial08Theme` có sẵn — theme Purple/Pink không thực sự áp dụng. Manifest thêm `POST_NOTIFICATIONS` + khai báo tĩnh `SystemEventReceiver` (khớp `BATTERY_LOW` — một trong ít broadcast hệ thống còn được phép khai tĩnh); `CustomEventReceiver` không xuất hiện trong Manifest vì đăng ký runtime.
- Dependency thêm: `work-runtime-ktx`; ghi chú 2 điểm dư thừa: `core-ktx:1.13.1` khai trùng với bản `1.17.0` sẵn có trong catalog; `core-splashscreen` được thêm nhưng chưa thấy code gọi `installSplashScreen()`.

## Tutorial 9 (Online) — Music Player App

- Kiến trúc **chưa theo MVVM/data-domain-presentation đầy đủ**: gần như single-Activity (Compose UI) + `Service` (phát nhạc) + `Worker` (tải file), giao tiếp qua `Intent`/action string thay vì ViewModel/use case.
- **MainActivity**: bind/start `MusicPlayerService` (`ContextCompat.startForegroundService` + `bindService` qua `ServiceConnection`), `RequestNotificationPermission()` (API 33+), `enqueueDownload()` (build `OneTimeWorkRequest` cho `DownloadWorker`), `SongListScreen` (danh sách URL mẫu, nút Download/Play), `getLocalSongs()`, `PlayerScreen` (danh sách bài đã tải, observe `WorkInfo` theo tag `"song_download"` để refresh khi tải xong).
- **MusicPlayerService**: Foreground Service dùng `MediaPlayer`; xử lý action `ACTION_PLAY`/`PAUSE`/`STOP`/`NEXT`/`PREV` từ Intent; `updateNotification()` build notification điều khiển với các `PendingIntent` gọi ngược lại Service; có `LocalBinder` nhưng hiện `MainActivity` chưa gọi method nào qua binder (mọi điều khiển vẫn qua Intent).
- **DownloadWorker**: tải file qua `URL.openStream()` lưu vào `filesDir/music/`, bắn notification kết quả (channel riêng "download_channel"), trả `Result.success()`/`failure()`.
- **Lưu ý**: `PlayerViewModel` (giữ `isPlaying` qua `StateFlow`) **không được reference ở đâu khác trong code** — có vẻ là scaffold/leftover chưa tích hợp, nên kiểm tra lại đề bài.
- Manifest thêm: `POST_NOTIFICATIONS`, `FOREGROUND_SERVICE_MEDIA_PLAYBACK`, `INTERNET`, khai báo `<service>` với `foregroundServiceType="mediaPlayback"`. Dependency thêm: `lifecycle-viewmodel-compose`, `work-runtime-ktx`, `foundation`, `runtime-livedata` (cho `observeAsState`), `media` (MediaStyle notification); ghi chú `core-ktx:1.12.0` có thể dư thừa so với bản 1.17.0 sẵn có.

## Lecture 10 (Online) — Text Recognition Demo (CameraX + ML Kit)

- Demo CameraX + ML Kit **Text Recognition** thời gian thực, toàn bộ trong 1 file `MainActivity.kt`, không theo MVVM.
- Có đoạn proximity sensor chỉ để `Log.d` "Proximity: NEAR/FAR" — **không tác động gì lên UI** (khác Tutorial10, ở đây chỉ demo/log).
- `CameraPreview()` — composable preview đơn giản (chỉ bind `Preview` use case, chưa có `ImageAnalysis`) — hiện đã bị **comment lại**, không dùng.
- `TextRecognitionScreen()` (composable chính): `PreviewView` qua `AndroidView` + `ProcessCameraProvider`; `TextRecognition.getClient(DEFAULT_OPTIONS)`; `ImageAnalysis` (`STRATEGY_KEEP_ONLY_LATEST`) chạy trên thread riêng, convert frame → `InputImage` → cập nhật `detectedText`; bind vào lifecycle, camera sau.
- Manifest thêm: `<uses-feature camera required=false>` + permission `CAMERA`.
- **Lưu ý**: dependency CameraX bị khai ở 2 nơi khác version — `camera-core`/`camera-camera2` hardcode `1.3.0`, còn `camera-view`/`camera-lifecycle` qua catalog là `1.4.2` — nên thống nhất lại 1 version khi làm mới.

## Tutorial 10 (Online) — Smart Scanner App

- App "Smart Scanner": CameraX preview + ML Kit **Image Labeling** thời gian thực + proximity sensor (tự động pause/resume quét) + GPS song song — toàn bộ trong 1 Activity + 1 composable, chưa tách MVVM.
- **MainActivity**: xin 3 quyền 1 lượt (`CAMERA`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, không xử lý callback); proximity sensor set `pauseScanning` (`mutableStateOf` trong `companion object`, dùng làm state global) — vật ở gần → dừng quét, có Toast phản hồi.
- **SmartScannerScreen**: `FusedLocationProviderClient` riêng (request update liên tục, interval 2s, `PRIORITY_HIGH_ACCURACY`) hiển thị lat/lng; camera preview + `ImageAnalysis` (bỏ qua frame nếu `pauseScanning == true`, ngược lại chạy `ImageLabeling` lấy top 3 label + confidence); UI 2 nửa: preview trên, kết quả (detected objects + location) dưới.
- Manifest thêm: `<uses-feature camera>`, `CAMERA`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`.
- **Lưu ý**: dependency `image-labeling-common:18.1.0` và `material3:1.3.2` khai qua catalog có vẻ dư/trùng với `com.google.mlkit:image-labeling:17.0.9` (dùng thật trong code) và `material3` mặc định — nên đối chiếu lại đề bài.

## Lecture 11 (Online) — Login MVVM + Use Case Demo (kèm test)

- Demo kiến trúc MVVM + Use Case tách lớp rõ ràng: `UserRepository` (interface) → `LoginUseCase` (domain) → `LoginViewModel` (presentation, giữ `LoginUiState`) → `LoginScreen` (UI Compose) — có kèm bộ test khá đầy đủ (unit/integration/Compose UI test).
- **File custom**: `UserRepository` (interface, `suspend fun login(email, password): Boolean`), `FakeUserRepository` (chỉ chấp nhận `user@email.com`/`1234`, dùng cho test/demo), `LoginUseCase` (nhận `UserRepository` qua constructor, `operator invoke`), `LoginUiState` (sealed class: Idle/Loading/Success/Error), `LoginViewModel` (nhận `LoginUseCase` + dispatcher optional để test được; `login()` chạy trong `viewModelScope`, set Loading → gọi use case → Success/Error), `LoginScreen` (nhận `ViewModel` làm tham số, 2 field + nút Login, đều có `testTag` riêng).
- **Test đi kèm**: `LoginViewModelUnitTest` (mock `LoginUseCase` bằng Mockito-Kotlin, `StandardTestDispatcher`+`runTest`), `LoginIntegrationTest` (dùng `FakeUserRepository` thật, kiểm tra luồng end-to-end), `LoginScreenTest` (Compose UI test qua `testTag`, `createAndroidComposeRule`).
- **⚠️ Lưu ý quan trọng**: project **không có file `MainActivity.kt`** dù Manifest vẫn khai `<activity android:name=".MainActivity">` làm launcher — **project hiện tại sẽ không build/chạy được**; có thể bị sót khi copy/nộp bài, cần kiểm tra lại.
- Dependency thêm: `kotlinx-coroutines-core` + bộ test (`kotlinx-coroutines-test`, `core-testing`, `mockito-core`, `mockito-kotlin`, `kotlin("test")`); ghi chú Espresso/JUnit/composeBom đã được nâng version thủ công để fix lỗi ("CRITICAL FIX" trong comment gốc).
