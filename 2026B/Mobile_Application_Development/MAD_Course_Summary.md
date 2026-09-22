# MAD Course Summary — All Weeks (Pre-Class + Lecture + Tutorial + Online Components)

## Week 1 — Introduction to Android / Mobile Market Overview

### Pre-Class

- A **mobile OS** is software that lets phones/tablets run apps and bridges hardware and software. OS market cycle: More users → More developers → More apps → Even more users, which let Android/iOS dominate and lock out new entrants (BlackBerry OS, Symbian, Windows Phone failed to keep up).

| Feature | Android | iOS |
|---|---|---|
| Device Range | Many manufacturers | Apple only |
| Fragmentation | Bad (many OS versions/hardware) | Good (mostly recent OS) |
| OS | Core open source, Google services proprietary | Fully proprietary |
| Languages | Java, Kotlin, Scala, Dart, C, HTML-5 | Swift, Objective-C, HTML-5 |
| Development Machine | Windows/Mac/Linux | Mac/Hackintosh only |
| Main Market | Low–mid range | High-end/business |

- **Android** is Linux-based, not built from scratch; also powers tablets, smartwatches, TVs, cars. Versions: dessert-named until Android 9 (Pie), numeric from Android 10 onward.
- **Android architecture layers** (bottom → top): Linux Kernel → Android API + ART → Application Framework → System Applications.
- **Android Studio**: official IDE. **Kotlin**: modern, statically-typed, interoperable with Java; >60% of Android apps use it, officially recommended since 2019.

| Feature | Kotlin | Java |
|---|---|---|
| Syntax | Concise, modern | Verbose |
| Null Safety | Built-in | Manual checks |
| Coroutines | Native async | Needs external libraries |
| UI Toolkit | Optimised for Jetpack Compose | Requires XML views |

- **Jetpack Compose**: Google's declarative, Kotlin-based UI toolkit replacing XML layouts.

| Approach | Imperative (Java/XML) | Declarative (Kotlin/Compose) |
|---|---|---|
| Focus | How to do it | What to display |
| Example | `button.setText("OK")` | `Text("OK")` |

### Lecture

- **Mobile OS market**: Android and iOS hold ~72% global share (~27% in premium markets); Windows Mobile, Symbian, BlackBerry, Palm, Brew, Java ME are discontinued/dead. Cross-platform options: Flutter, React Native, Xamarin (merging into .NET MAUI).
- **Share types**: Market Share and Press/Mind Share differ from Usage/Profit/Spend Share (what developers want for ROI) — Android leads Market Share, iOS leads Usage/Profit/ROI.
- **App market strategy**: trade-off between maximum customer coverage (lowest-common-denominator) vs. maximum device coverage (paying-customer approach).
- **Android**: mobile OS by Google, acquired from Android Inc. in 2005, first release 2008; runs on phones, tablets, watches, cameras, consoles, TVs, PCs. Current version Android 16, 3B+ active users, 3.5M+ Play Store apps.
- Android uses **only the Linux kernel**, runs on **ART**, replacing Dalvik since Android 5.0.
- **Android Software Stack** (bottom → top): Linux Kernel → Android API + ART → Application Framework → System Applications.
- **JDK vs SDK**: JDK compiles/runs Java (`javac`, `java`, `jar`, `keytool`); Android SDK = JDK + Android libraries, built on OpenJDK since 2015.
- **Android Studio**: official IDE since 2013 (JetBrains + Google, based on IntelliJ IDEA); includes emulator, Gradle build, AI-powered features.
- **Kotlin unique features**: null safety, extension functions, data classes, smart casts, coroutines, sealed classes, default/named args, destructuring, higher-order functions, type inference.
- **MVVM Architecture**:

| Layer | Responsibility |
|---|---|
| Model | Data layer — business logic, data sources; UI-agnostic |
| View | UI layer — displays data, forwards input; stateless & reactive |
| ViewModel | Bridge — holds UI state via LiveData/StateFlow, survives config changes |

```kotlin
class UserViewModel : ViewModel() {
    private val _user = MutableStateFlow(User("Minh", 21))
    val user: StateFlow<User> = _user
}
```

| Aspect | ViewModel (MVVM) | Controller (MVC) |
|---|---|---|
| UI Awareness | UI-agnostic | Often tightly coupled |
| Lifecycle Awareness | Survives config changes | Not lifecycle-aware |
| Data Flow | One-way (View observes ViewModel) | Two-way |
| Testability | Highly testable | Harder to test |

### Tutorial

- **Goal**: get familiar with Android Studio and basic layout techniques.
- **Project setup**: *Phone and Tablet → No Activity*; moderate Minimum SDK (e.g. Android 7.0 Nougat).
- **Two UI approaches**: XML-based UI (`res/layout/activity_main.xml`, drag-and-drop) and Jetpack Compose (declarative, pure Kotlin) — course starts with XML, transitions to Compose.
- Create Activity via *New → Activity → Empty Views Activity*; auto-registered in `AndroidManifest.xml`.
- **Layout practice**: LinearLayout (vertical/horizontal), Button, EditText, TextView; default ConstraintLayout needs explicit constraints or components jump to (0,0) at runtime.
- **Coding practice**: `findViewById` + `setOnClickListener`, `Toast.makeText(...).show()`.
- **Exercises**: (1) marriage-oracle app; (2) BMI calculator with unit toggle; (3) 1–10 lottery game; (4) basic calculator.
- **Extra**: repeat using Jetpack Compose *Empty Activity*, exploring Live Edit, Interactive Preview, Modifiers.

### Online Components — Kotlin vs Java: 10 điểm khác biệt chính

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

## Week 2 — Android UI Foundations / Activity Lifecycle

### Pre-Class

- An **Activity** is a self-contained window/screen; most apps have one main activity plus supporting activities.
- **Activity Lifecycle**:

| Callback | Description |
|---|---|
| `onCreate()` | First callback; initialises UI (`setContentView()`) |
| `onStart()` | Becomes visible but not yet interactive |
| `onResume()` | In the foreground, receiving input |
| `onPause()` | Loses focus but stays partially visible |
| `onStop()` | No longer visible; should release unneeded resources |
| `onDestroy()` | Final callback before destruction |

- **Canonical layout types**: List-detail, Feed (grid of cards), Supporting pane.
- **Jetpack Compose layouts**: `Column` (vertical), `Row` (horizontal), `Box` (overlay).
- **Modifiers**: `padding()`, `size()`/`fillMaxSize()`, `background()`, `clickable()`, arrangement/alignment.
- When analysing a UI design: identify main sections first, then break each down into smaller composable building blocks.

### Lecture

- Class hierarchy: `View` → `ViewGroup` → `Layout`.
- **Static layouts**: XML, bound via `setContentView(R.layout.layout_name)`. `match_parent` vs `wrap_content`.
- **Common traditional layouts**: FrameLayout, LinearLayout, RelativeLayout, GridView, ListView.
- **Dynamic layouts**: views created/added programmatically at runtime (`findViewById`, `LayoutParams`, `addView()`).
- **Compose layouts**: declarative, pure Kotlin, live preview, dynamic/reactive.
- **Arrangement vs Alignment**: Arrangement distributes space *between* children; Alignment positions children *within* the parent.
- **Activity Lifecycle**: `onCreate()` → `onStart()` → `onResume()` (foreground) → `onPause()` → `onStop()` → `onDestroy()`, with `onRestart()`.
- **Tasks & Backstack**: activities of one app share a task; launching a new activity pushes the previous one to the backstack.
- **Activity states**: Active/Resumed, Paused, Stopped, Inactive — Paused/Stopped can be killed by the system anytime.
- **Intent**: messaging object for inter-component communication; starts activities (`startActivity(intent)`), passes data (`putExtra()`/`getStringExtra()`).
- `finish()` removes/destroys the current activity, returning to the previous one.
- **ActivityResultLauncher** replaces deprecated `startActivityForResult()`:

```kotlin
val launcher = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
) { result -> if (result.resultCode == RESULT_OK) { /* use result.data */ } }
```

- In Compose: `rememberLauncherForActivityResult()`.

### Tutorial

- **Goal**: practice Activity communication and dynamic UI with Compose (prep for Assignment 1).
- **App** — three activities:

| Screen | Description |
|---|---|
| `MainActivity` | Name `TextField` + "Student Form" / "Student Services" buttons |
| `StudentFormActivity` | Form pre-filled with the name; Submit + Go to Services buttons |
| `StudentServicesActivity` | List of service buttons |

- **Behavior**: `MainActivity` passes the name to `StudentFormActivity`; Submit returns with a thank-you message; selecting a service returns with a confirmation message — exercising two-way Intent communication.

## Week 3 — State Management and Architecture

### Pre-Class

- **State** is information that can change over time and affects what's shown.
- **Navigation Component**: manages the back stack, data passing, deep links; each screen is a Composable.
- **Theming**: defines colours/fonts/shapes/spacing once, applied app-wide.
- **MVVM Pattern**:

| Component | Responsibilities |
|---|---|
| Model | Data + retrieval logic. No UI logic |
| View | Compose screens; observes state and renders UI |
| ViewModel | Holds UI state, responds to input, updates data, notifies View |

- **Clean Architecture**: Domain Layer (Entities) → Application Layer (Use Cases) → Adapter Layer (APIs, Gateways, UI, DB) → Infrastructure Layer (Web, Devices, Network).

### Lecture

- **State**: data that can change over time; when it changes, Compose UI **automatically** recomposes.
- **Local state**: `remember { mutableStateOf(...) }`. **State hoisting**: moving state to a higher composable for centralized management.
- `remember` persists across recompositions; `rememberSaveable` also survives config changes.
- `derivedStateOf` recomputes only when dependencies change. `LaunchedEffect` runs suspend side effects; `DisposableEffect` handles setup/cleanup via `onDispose {}`. `mutableStateListOf` manages reactive lists.
- **MVVM**: Model/View/ViewModel; better separation, easier testing, data binding, team collaboration, reusable logic — not ideal for small projects.
- **Jetpack Navigation**: `NavController`, `NavHost`, `composable()`; data passed via route arguments.
- **Clean Architecture**: Presentation → Domain (Android-free) → Data → Framework. **Dependency Rule**: outer depends on inner, never reverse.

```kotlin
interface UserRepository { suspend fun getUser(id: Int): User }
class GetUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Int): User = repository.getUser(id)
}
```

- Tips: `sealed class`/`Result` for state; keep Domain Android-free; expose via `StateFlow`/`LiveData`; modularize by feature.
- **Theming**: `MaterialTheme` controls colors/typography/shapes; light/dark switching.

### Tutorial

- **Goal**: practice state hoisting, Compose navigation, MVVM + Clean Architecture via a multi-screen task manager.
- **App** — three screens:

| Screen | Description |
|---|---|
| `HomeScreen` | Task list (`LazyColumn`), count via `derivedStateOf`, FAB "Add Task" |
| `AddTaskScreen` | Title (required), Due Date (required), Description (optional); Save button |
| `TaskDetailScreen` | Task details; Delete button |

- **Behavior**: `Save` disabled until Title+Due Date filled; adds via ViewModel, navigates back, shows Snackbar; `Delete` removes task, navigates back, shows Snackbar.

### Online Components — Tutorial 3: Clean Architecture + Navigation Compose (Task App)

- Kiến trúc 3 lớp: `presentation → domain ← data` — cả `data` và `presentation` phụ thuộc `domain`, `domain` không phụ thuộc ngược lại.
- **Domain**: `Task` (model), `TaskRepository` (interface), 3 use case (`GetTasksUseCase`, `AddTaskUseCase`, `DeleteTaskUseCase`) — ViewModel gọi qua use case, không gọi thẳng repository.
- **Data**: `FakeTaskDataSource` (in-memory) + `TaskRepositoryImpl` (implement interface domain) — lớp sẽ thay bằng Room/API thật sau này mà không đụng domain/presentation.
- **Presentation**: `TaskViewModel` + Navigation Compose (`TaskNavRoutes`, `NavGraph`/`NavHost`) + 3 screen (`HomeScreen`, `AddTaskScreen`, `TaskDetailScreen`).
- **Setup cần thêm thủ công** cho project mới: dependency `androidx-navigation-compose` + `androidx-lifecycle-viewmodel-compose` — thiếu sẽ không build được.

## Week 4 — Networking and Web Services

### Pre-Class

- A **Web Service** lets computers share information over the internet, regardless of underlying system/language.
- Common protocols: HTTP/HTTPS, FTP, SMTP, XML/JSON.
- **RESTful services**:

| Action | HTTP Verb | Example |
|---|---|---|
| Retrieve data | GET | Get a list of photos |
| Add new data | POST | Submit a new photo |
| Update existing data | PUT | Edit a photo description |
| Remove data | DELETE | Delete a saved photo |

- **Asynchronous tasks**: run separately from the main UI thread. Synchronous blocks until response; Asynchronous keeps running other tasks.
- **Retrofit**: type-safe HTTP client; converts JSON responses into Kotlin objects.
- **Kotlin Coroutines**: writes async code sequentially, avoiding callback complexity.

### Lecture

- **Coroutines**: lightweight threads for async, non-blocking, sequential-style code. `suspend`, `CoroutineScope`, `launch`, `async`, `runBlocking{}`, `delay`.
- **Flow**: cold, async, reactive stream. `flow{}`, `emit()`, `.collect()`, `.catch{}` — nothing runs until collected.
- **Web Service**: consumer-to-provider collaboration; functions implemented once, called many times.
- **REST architecture**: server provides resources; client accesses/presents them; resources identified via URIs.

| HTTP Verb | CRUD | Entire Collection | Specific Item |
|---|---|---|---|
| POST | Create | 201 Created, Location header | 404/409 |
| GET | Read | 200 OK, list | 200 OK single item, 404 |
| PUT | Update/Replace | 405 (usually) | 200/204, 404 |
| PATCH | Update/Modify | 405 (usually) | 200/204, 404 |
| DELETE | Delete | 405 (usually) | 200 OK, 404 |

- **Retrofit**: type-safe HTTP client (Square) — auto JSON↔Kotlin conversion, coroutines/Flow support.

```kotlin
interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}
```

- Repository exposes results as `Flow`; ViewModel collects via `viewModelScope.launch`; Compose observes with `collectAsState()`.
- **JSON parsing alternatives**: Kotlinx.serialization (native), Moshi (Retrofit 2 default), Gson (older).
- **Postman**: tool for testing API requests.

### Tutorial

- **Goal**: build an app consuming a RESTful web service (GET/POST provided; DELETE/PUT as exercise).
- **Preparation**: run local JSON server (`json-server --watch db.json --port 3004`); test with Postman.
- **Steps**: define `Student` model (`@Serializable`, no id); Retrofit `StudentApi` interface (GET/POST/DELETE/PUT); Retrofit instance with `10.0.2.2` base URL for emulator (troubleshooting: Airplane Mode toggle, proxy settings, ngrok); display student list with FAB to add; `AddStudentScreen`; wire navigation graph.
- **Exercise**: implement per-student delete and update.

### Online Components — Tutorial 4: RESTful Student App

- Kiến trúc MVVM 3 lớp quản lý Student qua REST API.
- **Data**: `Student(id, name)` + `NewStudent(name)`; `StudentApi` (Retrofit: GET/POST/DELETE/PUT `/students`); `ApiClient` (singleton, `baseUrl = http://10.0.2.2:3004/`, kotlinx.serialization converter).
- **Presentation**: `StudentApp` (`NavHost` 3 route); `StudentViewModel` (`StateFlow<List<Student>>` + `SharedFlow<String>` snackbar); `StudentListScreen`/`AddStudentScreen`/`EditStudentScreen`.
- **Lưu ý**: `MainActivity` dùng `MaterialTheme` mặc định, không dùng theme custom `RESTfulTheme` đã định nghĩa sẵn.
- **Setup bắt buộc thêm thủ công**: `INTERNET` permission + `usesCleartextTraffic="true"` (thiếu → API fail ngay); plugin `kotlin.serialization` + Retrofit/kotlinx.serialization/coroutines/navigation-compose dependencies.
- `baseUrl 10.0.2.2:3004` chỉ đúng cho Android Emulator.

## Week 5 — Location-based Services and Maps Integration

### Pre-Class

- **Google Maps Android API**: displays maps, accesses location data, handles interactions; provides map data, interactive controls, location services, custom overlays.
- Typical integration: Setup (API key, SDK, permissions) → Display → Interact → Location.
- **Android Permissions**:

| Permission Type | Description |
|---|---|
| Normal | Auto-granted at install |
| Dangerous | Require explicit user approval |

- **Accompanist Permissions**: Composable functions for declarative runtime permission handling.

```kotlin
@Composable
fun CameraScreen() {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    when {
        cameraPermissionState.status.isGranted -> { /* Show camera UI */ }
        cameraPermissionState.status.shouldShowRationale -> { /* Show explanation UI */ }
        else -> { Button(onClick = { cameraPermissionState.launchPermissionRequest() }) { Text("Grant Camera Permission") } }
    }
}
```

### Lecture

- **Google Maps Android API (v2)**: part of Google Play services; Markers, Polylines, Polygons, Ground Overlays, Tile Overlays. v1 deprecated.
- Must: Google account, attribution notice, possibly enterprise license. Must not: re-implement Maps/Earth, wrap for redistribution, hide copyright.
- **API key**: obtained via Google Cloud Console, added to `AndroidManifest.xml`.

```kotlin
@Composable
fun MapScreen() {
    val singapore = LatLng(1.35, 103.87)
    GoogleMap(cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 12f)
    }) { Marker(state = MarkerState(position = singapore), title = "Singapore") }
}
```

- Key operations: Markers, click events, camera animation, `MapType` switching, `Polyline`/`Circle`/`Polygon`.
- **Accompanist Permissions**: `rememberMultiplePermissionsState`, `allPermissionsGranted`, `shouldShowRationale`, `launchMultiplePermissionRequest()`.
- **Google Location Services**: `FusedLocationProviderClient` — last known location or continuous updates (`LocationRequest`/`LocationCallback`), synced with composable lifecycle.

### Tutorial

- **Goal**: build an app with interactive map + Location Services — markers, zoom/map types, runtime permissions, current location, periodic updates.
- **Preparation**: enable Maps SDK, get API key; add dependencies; practice markers, click handling, map-type switching; request FINE/COARSE permissions; fetch last known location; continuous updates.
- **Exercise**: extend to show nearby restaurants from REST — retrieve list via Retrofit, marker per restaurant, FAB refresh, `onMapClick` adds a new restaurant via POST.

### Online Components — Lecture 5: Google Maps Compose Demo

- App demo rời rạc (không theo kiến trúc data/domain/presentation) — 5 composable độc lập: `MapScreen` (bản đồ tĩnh), `AdvancedMapFeaturesDemo` (marker icon tuỳ chỉnh, polyline, click, `MapType.SATELLITE`), `PermissionRequestScreen`, `LocationMapScreen` (vị trí 1 lần), `ContinuousLocationMapScreen` (theo dõi liên tục — đang chọn chạy).
- Không có ViewModel/DI/network — state là `remember { mutableStateOf() }` cục bộ.
- Manifest thêm: `ACCESS_FINE_LOCATION`/`ACCESS_COARSE_LOCATION`, API key hardcode, `windowSoftInputMode="adjustResize"`.
- Dependency: `maps-compose`, `play-services-maps`, `accompanist-permissions`, `play-services-location`.

### Online Components — Tutorial 5: Interactive Map (Restaurant + Location)

- Kiến trúc Clean Architecture 3 lớp + MVVM đầy đủ: `presentation ← domain → data`.
- **Domain**: `Restaurant`, `RestaurantRepository` (interface, trả `Result<T>`), `LocationTracker` (trả `Flow<LatLng>`), 2 use case.
- **Data**: `RestaurantDto` (`toDomain()`/`toDto()`), `RestaurantApi` (Retrofit), `ApiClient` (OkHttp + Retrofit + Gson, `10.0.2.2:3004`), `RestaurantRepositoryImpl`, `DefaultLocationTracker` (`callbackFlow` bọc `FusedLocationProviderClient`).
- **Presentation**: `MapViewModel`, `MapViewModelFactory` (composition root, wiring thủ công, không Hilt/Koin), `InteractiveMapScreen` (GoogleMap + FAB refresh + `onMapClick` thêm restaurant), `RequestLocationPermission`.
- Manifest thêm: `INTERNET`/location permissions, API key, `networkSecurityConfig` (cleartext traffic tới `10.0.2.2`).
- Dependency: `play-services-maps`, `maps-compose`, `play-services-location`, `accompanist-permissions`, `retrofit`+`converter-gson`, `kotlinx-coroutines-android`.

## Week 6 — Data Persistence and Dependency Management

### Pre-Class

- **SQLite**: built-in lightweight local database, requires raw SQL.
- **Room Database**: wrapper over SQLite using Kotlin data classes/annotations.

| Feature | SQLite | Room |
|---|---|---|
| Query Validation | Runtime errors | Compile-time errors |
| Boilerplate | Raw SQL + manual connections | DAO methods abstract SQL |
| Integration | No direct LiveData/Coroutines support | Works seamlessly |
| Learning Curve | Requires SQL knowledge | Simple |

- **Preferences DataStore**: modern replacement for SharedPreferences.
- **Firebase**: NoSQL, BaaS option.
- **Dependency Injection (DI)**: objects receive dependencies from external sources.

| Aspect | Without DI | With DI |
|---|---|---|
| Testability | Hard | Easy (inject mocks) |
| Coupling | Tight | Flexible/swappable |
| Clarity | Unclear dependencies | Explicit |

- **Hilt**: Google's DI library.

### Lecture

- Apps must retain data after closing; DI makes apps modular/testable. **Room + DataStore + Hilt** form a scalable-app foundation.
- **SQLite**: lightweight relational DB built into Android OS.
- **Room**: abstraction over SQLite, compile-time query validation, easy Coroutines/Flow integration.

```kotlin
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String, val completed: Boolean = false
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks") suspend fun getAllTasks(): List<Task>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertTask(task: Task)
}
```

- `@Database` links entities + DAOs; `@TypeConverter` serializes unsupported types.
- **Preferences DataStore**: async, thread-safe, key-value, Flow-based API.
- **Firebase**: Realtime Database, Cloud Firestore, Authentication, Storage.
- **Hilt**: `@HiltAndroidApp` (app setup), `@Module`+`@Provides` (define deps), `@Inject` (request dep), `@HiltViewModel`, `@AndroidEntryPoint`.

### Tutorial

- **Goal**: build a currency-management app using Room, DataStore, Hilt.
- **Preparation — Room**: `Entity`, `Dao`, `AppDatabase`; `CurrencyViewModel`; Compose UI listing currencies + add form + delete.
- **Preparation — DataStore & Hilt**: `PreferencesManager` (dark mode, font size); integrate Hilt.
- **Exercise**: `Card` per currency with tap-to-edit; search by country; sort-order toggle persisted via DataStore; (extra) Firebase sync.

### Online Components — Lecture 6: Room + Hilt + DataStore (Task List)

- App demo 3 thành phần Jetpack tách biệt (Room, Hilt, DataStore) — không theo kiến trúc nhiều lớp.
- Luồng: `MyApp (@HiltAndroidApp)` → `MainActivity (@AndroidEntryPoint)` → `TaskListScreen` → `TaskViewModel (@HiltViewModel)` → `TaskDao → AppDatabase → Task`; `DatabaseModule` cung cấp cho Hilt graph.
- **Room**: `Task` entity, `TaskDao` (5 query suspend), `AppDatabase` (singleton thủ công qua `getDatabase()` nhưng **không thực sự dùng** — Hilt cung cấp qua `DatabaseModule` mới là nguồn thật).
- **DataStore**: `PreferencesManager` (`dark_mode` key).
- **Lưu ý**: `TaskListScreen.kt` không có dòng `package` (default package); `MainActivity` tự dựng `MaterialTheme` trực tiếp, không dùng `Lecture06Theme` có sẵn; `hilt-navigation-compose` có dependency nhưng chưa thực sự dùng (`viewModel()` thường, chưa `hiltViewModel()`).
- Setup Hilt: plugin `com.google.dagger.hilt.android` + `kotlin-kapt`; Room/DataStore/Hilt dependencies.

## Week 8 — Background Processing and System Communication

### Pre-Class

- Background processing needed for: long-running tasks, keeping UI responsive, reacting to system events. Android 8+/10+ impose strict background limits.
- **Four components for system communication**:

| Component | Description | Example |
|---|---|---|
| BroadcastReceiver | Listens for system/app broadcasts | Banking app "transaction completed" |
| Scoped Storage | Secure, permission-based file access | Photo editor gallery access |
| WorkManager | Deferrable, guaranteed background work | Grab keeps searching for driver even if closed |
| Notifications | Alerts users outside main UI | News app breaking-news alert |

### Lecture

- **WorkManager**: schedules deferrable/async background tasks flexibly; survives kill/restart; handles constraints, Doze Mode.

```kotlin
class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result { return Result.success() }
}
val workRequest = OneTimeWorkRequestBuilder<MyWorker>().setInitialDelay(5, TimeUnit.SECONDS).build()
WorkManager.getInstance(context).enqueue(workRequest)
```

- `PeriodicWorkRequestBuilder` — minimum interval **15 minutes**.
- **Broadcast Receiver**: Manifest-declared (works when app not running, system events) vs Context-registered (runtime, dynamic). Android 8+ restricts most implicit broadcasts from manifest registration.
- **Scoped Storage** (Android 10+): sandboxed external-storage area; MediaStore API (media) or SAF (documents).
- **Background execution limits** (Android 8+): restricts implicit broadcasts and background location updates. Solutions: Foreground Services, WorkManager.
- **Notifications**: Notification Channel (Android 8+), Notification Builder, Notification Manager; requires `POST_NOTIFICATIONS` (API 33+).

### Tutorial

- **Goal**: log user actions/system events to scoped storage, summarize periodically via WorkManager, deliver via Notifications.
- **Setup**: WorkManager dependency; `POST_NOTIFICATIONS`; `<receiver>` for `SystemEventReceiver` on `BATTERY_LOW`.
- **Data layer**: `Event` model; `EventLogRepository` appends/reads `event_log.csv` in scoped storage.
- **Notifications helper**: `Notify` singleton (`ensureChannel()`, `show()`).
- **Receivers**: `CustomEventReceiver` (runtime, `ACTION_HAPPY`/`ACTION_SAD`); `SystemEventReceiver` (manifest, `BATTERY_LOW`).
- **WorkManager**: `SummaryWorker` counts last-hour events every 15 minutes, sends summary notification.
- **UI**: buttons for broadcasts + `LazyColumn` of events.

### Online Components — Tutorial 8: Background Processing (Event Log + Broadcast + WorkManager)

- App minh hoạ 3 cơ chế nền: Broadcast Receiver (runtime + manifest), WorkManager, Notification — event ghi vào CSV log (scoped storage).
- Luồng: nút Happy/Sad → `sendBroadcast()` → `CustomEventReceiver`/`SystemEventReceiver` → ghi log + `Notify.show()`; song song `SummaryWorker` (15 phút) gửi thông báo tổng hợp.
- **File chính**: `Event`, `EventLogRepository` (`getExternalFilesDir(null)` — không cần xin quyền), `Notify`, 2 receiver, `SummaryWorker`, `MainActivity` (di chuyển vào package `presentation`).
- **Lưu ý**: `setContent { AppScreen() }` không bọc `Tutorial08Theme` có sẵn. Manifest thêm `POST_NOTIFICATIONS` + khai tĩnh `SystemEventReceiver`.
- Dependency: `work-runtime-ktx`; ghi chú 2 điểm dư thừa (`core-ktx` trùng version, `core-splashscreen` chưa dùng).

## Week 9 — Services and Lifecycle-Aware Components

### Pre-Class

- **Services** let apps keep running when the user switches away.

| Service Type | Description | Example |
|---|---|---|
| Foreground Service | User-visible, persistent notification | Google Maps directions |
| Background Service | Runs silently (restricted from Android 8+) | Data syncing |
| Bound Service | Components bind and interact | Fitness tracker live step count |

- **WorkManager vs Foreground Service**: deferrable/guaranteed vs real-time/user-visible.
- **Lifecycle-Aware Components** (ViewModel, StateFlow, LifecycleObserver) automatically respond to lifecycle events.

### Lecture

- **Service**: no UI, runs even if user switches apps. Started (`startService`/`stopSelf`), Bound (`IBinder`), Foreground (persistent notification).
- Service Lifecycle: `onCreate()` → `onStartCommand()`/`onBind()` → `onDestroy()`.
- Started Service must call `stopSelf()`; Android 8+ often kills long-running ones → prefer Foreground Service or WorkManager.
- Bound Service: `ServiceConnection`, `onServiceConnected`/`onServiceDisconnected`, `bindService`/`unbindService`.
- Foreground Service must call `startForeground(id, notification)` within 5 seconds.
- **Lifecycle-Aware Components**: `ViewModel`, `LiveData`/`StateFlow`, `LifecycleOwner`, `LifecycleObserver`.

```kotlin
class CounterVM : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count
    fun increment() { _count.value++ }
}
```

### Tutorial

- **Goal**: build a simulated music player — Foreground Service playback, Bound Service control, ViewModel+StateFlow, media-style notification, WorkManager download.
- **Setup**: `POST_NOTIFICATIONS`, `FOREGROUND_SERVICE_MEDIA_PLAYBACK`, `INTERNET`; `MusicPlayerService` with `foregroundServiceType="mediaPlayback"`.
- **`MusicPlayerService`**: Foreground + Bound; `play()`/`pause()`/`stop()`; MediaStyle notification.
- **`PlayerViewModel`**: `isPlaying` state.
- **MainActivity & UI**: bind/unbind service; Play/Pause/Stop buttons.
- **Exercises**: next/prev track, sync notification with UI state, download via WorkManager.

### Online Components — Tutorial 9: Music Player App

- Kiến trúc **chưa theo MVVM đầy đủ**: single-Activity + `Service` (phát nhạc) + `Worker` (tải file), giao tiếp qua `Intent`/action string.
- **MainActivity**: bind/start `MusicPlayerService`, `RequestNotificationPermission()`, `enqueueDownload()`, `SongListScreen`, `PlayerScreen` (observe `WorkInfo` để refresh).
- **MusicPlayerService**: Foreground Service dùng `MediaPlayer`; xử lý action PLAY/PAUSE/STOP/NEXT/PREV; có `LocalBinder` nhưng chưa được gọi trực tiếp (điều khiển vẫn qua Intent).
- **DownloadWorker**: tải file qua `URL.openStream()`, lưu `filesDir/music/`.
- **Lưu ý**: `PlayerViewModel` **không được reference ở đâu khác** — có vẻ leftover chưa tích hợp.
- Manifest + dependency: `POST_NOTIFICATIONS`, `FOREGROUND_SERVICE_MEDIA_PLAYBACK`, `INTERNET`, `work-runtime-ktx`, `runtime-livedata`, `media`.

## Week 10 — Hardware Integration and Machine Learning in Android

### Pre-Class

- A **sensor** detects/measures physical properties.

| Category | Sensor | Description |
|---|---|---|
| Motion | Accelerometer | Detects acceleration/tilt |
| Motion | Gyroscope | Measures rotation |
| Environmental | Ambient Light Sensor | Measures brightness |
| Position | Magnetometer | Detects magnetic fields |
| Position | GPS | Precise geographic location |

- **ML Kit**: Google's on-device ML SDK — Text Recognition, Face Detection, Object Detection, Barcode Scanning.

### Lecture

- **Sensors**: Motion, Position, Environmental. Access via `SensorManager` — register in `onResume()`, unregister in `onPause()`.
- **Camera APIs**: legacy (deprecated), Camera2 (complex), **CameraX** (modern, simpler, lifecycle-aware).
- CameraX use cases: `Preview`, `ImageCapture`, `ImageAnalysis`, `VideoCapture`.
- **ML on Android**: Vision (camera) and NLP (text/language) use cases.

| On-device ML | Cloud ML |
|---|---|
| Fast, offline, privacy-friendly | More powerful, needs internet |
| Limited by phone hardware | — |

- **ML Kit**: Vision APIs (barcode, face, image labeling, text recognition) + NLP APIs; supports custom TFLite models.
- **ML Kit Analyzer pipeline**: `ImageAnalysis` frame → `InputImage` → ML Kit API → success/failure callback.

### Tutorial

- **Goal**: build a Smart Scanner — Proximity Sensor pause/resume, GPS, CameraX preview, ML Kit Object Classification.
- **Setup**: `CAMERA`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`; CameraX + ML Kit image-labeling + Location Services dependencies.
- **Proximity sensor**: NEAR → stop analyzing frames; FAR → resume.
- **Location**: `FusedLocationProviderClient`, continuous updates every 2s.
- **Camera + classification**: `ImageAnalysis` → `InputImage` → `ImageLabeler`, top 3 labels with confidence.
- **UI**: top camera preview, bottom results panel.

### Online Components — Lecture 10: Text Recognition Demo (CameraX + ML Kit)

- Demo CameraX + ML Kit Text Recognition thời gian thực, toàn bộ trong 1 file `MainActivity.kt`, không theo MVVM.
- Proximity sensor chỉ để `Log.d` — không tác động UI (khác Tutorial10).
- `CameraPreview()` (preview đơn giản) đã bị comment, không dùng.
- `TextRecognitionScreen()`: `PreviewView` + `ProcessCameraProvider`; `TextRecognition.getClient`; `ImageAnalysis` convert frame → `InputImage` → cập nhật `detectedText`.
- **Lưu ý**: dependency CameraX khai ở 2 nơi khác version (`1.3.0` vs `1.4.2`) — nên thống nhất.

### Online Components — Tutorial 10: Smart Scanner App

- CameraX preview + ML Kit Image Labeling + proximity sensor (pause/resume) + GPS song song — 1 Activity + 1 composable, chưa tách MVVM.
- **MainActivity**: xin 3 quyền 1 lượt; proximity sensor set `pauseScanning` (state global trong `companion object`).
- **SmartScannerScreen**: location update liên tục (2s); `ImageAnalysis` bỏ qua frame nếu paused, ngược lại chạy `ImageLabeling` lấy top 3 label.
- **Lưu ý**: `image-labeling-common` và `material3` khai qua catalog có vẻ dư/trùng với dependency hardcode khác — nên đối chiếu lại đề bài.

## Week 11 — Testing Android Applications

### Pre-Class

- Testing catches bugs early, verifies logic/UI behaviour, increases refactoring confidence.

| Testing Type | Description | Tools | Example |
|---|---|---|---|
| Unit Testing | Tests business logic in isolation | JUnit, Mockito | Testing `calculateDiscount()` |
| UI Testing | Tests UI behaviour | Espresso | Tap through shopping app |
| Compose Testing | UI testing for Compose | Compose Testing APIs | Check Login button navigation |

### Lecture

- Testing matters for reliability, maintainability, scalability, CI/CD, avoiding technical debt.

| Testing Type | Scope | Tools |
|---|---|---|
| Unit Testing | Isolated logic | JUnit, Mockito |
| Integration Testing | Interactions between modules | Fakes/mocks |
| UI Testing | End-to-end flows | Espresso, Compose Testing |

- **Testing Pyramid**: Unit (base) → Integration (middle) → UI (top).
- Local tests use `StandardTestDispatcher` + `runTest`, `advanceUntilIdle()`.
- **Compose UI Testing**: `composeTestRule.setContent {}`; `onNodeWithText()`/`onNodeWithTag()`; `.performClick()`/`.performTextInput()`; `.assertIsDisplayed()`.
- **MVVM + Clean Architecture testing**: Entity/UseCase → unit; Repository → unit/integration (fakes); ViewModel → state verification; UI → Compose tests. Prefer fakes over mocks; run in CI/CD.

### Online Components — Lecture 11: Login MVVM + Use Case Demo (kèm test)

- Demo MVVM + Use Case tách lớp rõ ràng: `UserRepository` → `LoginUseCase` → `LoginViewModel` (`LoginUiState`) → `LoginScreen`.
- `FakeUserRepository` (hardcode `user@email.com`/`1234`) dùng cho test/demo.
- **Test đi kèm**: `LoginViewModelUnitTest` (mock Mockito-Kotlin), `LoginIntegrationTest` (fake thật, end-to-end), `LoginScreenTest` (Compose UI test qua `testTag`).
- **⚠️ Lưu ý quan trọng**: project **không có file `MainActivity.kt`** dù Manifest vẫn khai launcher activity — **sẽ không build/chạy được**, cần kiểm tra lại.
- Dependency: `kotlinx-coroutines-core` + bộ test; Espresso/JUnit/composeBom đã nâng version thủ công để fix lỗi.

## Week 12 — Course Review and Introduction to Cross-platform Development

### Pre-Class

| Approach | Description | Pros | Cons |
|---|---|---|---|
| Native | Built per-platform | Best performance, full hardware access | Separate codebases, more resources |
| Cross-Platform | Shared codebase | Faster dev, lower cost | May not match native look/feel |

| Framework | Language | Strengths | Limitations |
|---|---|---|---|
| Flutter (Google) | Dart | Fast dev, strong ecosystem | UI may feel less "native" |
| React Native (Meta) | JavaScript | Large community, reuses web skills | Performance drops on complex UIs |
| Kotlin Multiplatform (JetBrains) | Kotlin | Good for Android teams | Still evolving |
| .NET MAUI (Microsoft) | C# | Strong enterprise fit | Smaller mobile dev community |

### Lecture

- **Course recap** (Weeks 1–11): Mobile ecosystem → UI foundations → State/Architecture (MVVM, Clean Architecture) → Networking (Coroutines, Flow, Retrofit) → Location & Maps → Persistence & DI (Room, DataStore, Hilt, Firebase) → Background Processing → Services & Lifecycle → Hardware & ML → Testing.
- **Core stack**: Kotlin, Jetpack Compose, MVVM + Clean Architecture, Jetpack libraries, Android Studio/Gradle.
- **React Native**: builds natively-rendering apps using only JavaScript (React-based); renders real native UI (not WebViews).

| React Native | Native Development |
|---|---|
| Cross-platform, low dev cost | Platform-specific, higher dev cost |
| Better scalability, large community | Better UX/UI, more native module support |
| Less secure | Plenty of APIs/third-party libraries |

- Choose Native for: complex apps, frequent updates, native-UX focus, native device features, IoT, single-platform. Choose React Native for: simple/uniform apps, cross-platform launches, low budget, social/e-commerce, startups.
- **Project setup**: Node.js/NPM, JDK 8, Expo/Yarn; `npx create-expo-app@latest ProjectName` or `react-native init ProjectName`.
