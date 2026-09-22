# MAD Lectures Summary — All Weeks

## Week 1 — Introduction to Android

- **Mobile OS market**: Android and iOS hold ~72% global share (~27% in premium markets); Windows Mobile, Symbian, BlackBerry, Palm, Brew, Java ME are discontinued/dead. Tizen and WebOS survive mainly in TVs/wearables. Cross-platform options: Flutter (Google), React Native (Meta), Xamarin (merging into .NET MAUI).

| Feature | Android | iOS |
|---|---|---|
| Device Range | Large (many manufacturers) | Small (Apple only) |
| Fragmentation | Very bad (OS versions/hardware) | Good |
| OS | Sort of open source | Proprietary |
| Languages | Java, Scala, Dart, C, Kotlin, HTML-5 | Swift, HTML-5 |
| Development Machine | Any | Mac or Hackintosh |
| Main Market | Business & low-end consumer | High-end consumer & business |

- **Share types**: Market Share and Press/Mind Share differ from Usage/Profit/Spend Share (what developers want for ROI) — Android leads Market Share, iOS leads Usage/Profit/ROI.
- **App market strategy**: trade-off between maximum customer coverage (lowest-common-denominator) vs. maximum device coverage (paying-customer approach, targeting latest phones).
- **Android**: mobile OS by Google, acquired from Android Inc. in 2005, first release 2008; runs on phones, tablets, watches, cameras, consoles, TVs, PCs. Current version Android 16, 3B+ active users, 3.5M+ Play Store apps.
- Android uses **only the Linux kernel** (not GNU shell/GNOME etc.), runs on **Android Runtime (ART)**, replacing Dalvik since Android 5.0.
- **Android Software Stack** (bottom → top): Linux Kernel → Android API + ART → Application Framework → System Applications.
- **JDK vs SDK**: JDK compiles/runs Java (`javac`, `java`, `jar`, `keytool`); Android SDK = JDK + Android libraries, built on OpenJDK since 2015 (previously Apache Harmony).
- **Android Studio**: official IDE since 2013 (JetBrains + Google, based on IntelliJ IDEA); includes emulator, Gradle build system, modern UI tools, AI-powered features.

| Feature | Kotlin | Java |
|---|---|---|
| Syntax | Concise, modern | Verbose |
| Null Safety | Built-in | Manual checks |
| Coroutines | Native async | Needs external libraries |
| UI Toolkit | Optimised for Jetpack Compose | Requires XML views |

- **Kotlin unique features**: null safety (`var name: String? = null`), extension functions, data classes, smart casts, coroutines, sealed classes, default/named args, destructuring, higher-order functions, type inference.
- **Jetpack Compose**: Google's declarative Kotlin UI toolkit replacing XML.

| Approach | Imperative (Java/XML) | Declarative (Kotlin/Compose) |
|---|---|---|
| Focus | How to do it | What to display |
| Example | `button.setText("OK")` | `Text("OK")` |

- **MVVM Architecture**: separates UI from business logic/data.

| Layer | Responsibility |
|---|---|
| Model | Data layer — business logic, data sources (Room, network); UI-agnostic |
| View | UI layer (Compose/XML) — displays data, forwards input; stateless & reactive |
| ViewModel | Bridge between View and Model — holds UI state via LiveData/StateFlow, survives config changes |

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

## Week 2 — Android UI Foundations

- Class hierarchy: `View` (base UI control) → `ViewGroup` (holds many views) → `Layout` (visual structure for an activity/widgets).
- **Static layouts**: defined in XML (`res/layout/activity_name.xml`), bound via `setContentView(R.layout.layout_name)` in `onCreate()`. `layout_width/height`: `match_parent` (fills parent) vs `wrap_content` (fits own content).
- **Common traditional layouts**: FrameLayout (single child), LinearLayout (horizontal/vertical), RelativeLayout (relative positioning), GridView, ListView.
- **Dynamic layouts**: views/components can be created and added programmatically at runtime (`findViewById`, `LayoutParams`, `addView()`).
- **Jetpack Compose layouts**: declarative, pure Kotlin, live preview, dynamic/reactive (vs. XML's imperative, static XML preview).
- Core containers: `Column` (vertical stack), `Row` (horizontal stack), `Box` (overlay/stacking, default top-left alignment).
- **Modifiers**: chainable — `padding()`, `size()`/`fillMaxSize()`, `background()`, `clickable()`.
- **Arrangement vs Alignment**: Arrangement distributes space *between* children; Alignment positions children *within* the parent.
- An `Activity` is a single focused screen; apps can have any number of activities.
- **Activity Lifecycle**: `onCreate()` → `onStart()` → `onResume()` (foreground) → `onPause()` → `onStop()` → `onDestroy()`, with `onRestart()` returning to `onStart()`. Must usually implement `onCreate()` (init view) and `onPause()` (commit changes).
- **Tasks & Backstack**: activities of one app share a task; launching a new activity pushes the previous one to the backstack.
- **Activity states**: Active/Resumed (top, interactive), Paused (visible, no focus), Stopped (not visible), Inactive (removed from stack). Paused/Stopped activities can be killed by the system anytime.
- **Intent**: messaging object for communication between components; used to start activities (`startActivity(intent)`) and pass data (`putExtra()` / `getStringExtra()`).
- `finish()` removes/destroys the current activity (triggers `onPause()`→`onStop()`→`onDestroy()`), returning to the previous activity.
- **ActivityResultLauncher** (Jetpack Activity library) replaces deprecated `startActivityForResult()`/`onActivityResult()` — lifecycle-aware, type-safe:

```kotlin
val launcher = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
) { result ->
    if (result.resultCode == RESULT_OK) {
        val data = result.data?.getStringExtra("result_key")
    }
}
```

- In Compose, use `rememberLauncherForActivityResult()` for the same pattern.

## Week 3 — State Management and Architecture

- **State**: data that can change over time and that the UI reacts to; when state changes, Compose UI **automatically** recomposes.
- **Local state**: owned by one composable, via `remember { mutableStateOf(...) }`.
- **State hoisting**: moving state to a higher composable for centralized management → improves reusability and testability of stateless children.
- `remember` persists a value across recompositions; `rememberSaveable` also survives configuration changes (e.g. rotation) via `SavedStateHandle`.
- `derivedStateOf` recomputes a value only when its dependencies change (optimizes recomposition).
- `LaunchedEffect` runs suspend side effects (e.g. data fetch) scoped to the composable's lifecycle; `DisposableEffect` handles setup/cleanup (e.g. register/unregister listeners) via `onDispose {}`.
- `mutableStateListOf` manages reactive dynamic lists.
- **MVVM**: industry-standard pattern overcoming MVC/MVP drawbacks — Model (data/business logic), View (UI), ViewModel (middle layer exposing state to View).

| # | Reason to prefer MVVM over MVC | Description |
|---|---|---|
| 1 | Better separation of code | Business logic separated from UI |
| 2 | Easier testing | ViewModel testable without UI |
| 3 | Supports data binding | Automatic UI updates |
| 4 | Team collaboration | Model/View/ViewModel worked on independently |
| 5 | Reusable logic | One ViewModel reused across views |

- Not ideal for small projects; complex data-binding logic can make debugging harder.
- **Jetpack Navigation**: `NavController` (manages navigation), `NavHost` (defines destinations), `composable()` (links routes to UI); supports passing data via route arguments.
- **Clean Architecture**: organizes the whole codebase into independent layers — Presentation (UI + ViewModels), Domain (business rules/use cases, pure Kotlin, Android-free), Data (APIs/DB/repositories), Framework (Retrofit/Ktor, Room, Android SDK).
- **Dependency Rule**: outer layers depend on inner layers, never the reverse (Presentation → Domain allowed; Domain → Presentation not allowed).

```kotlin
// Domain
interface UserRepository { suspend fun getUser(id: Int): User }
class GetUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Int): User = repository.getUser(id)
}
```

- Tips: use `sealed class`/`Result` for state (Success/Error/Loading); keep Domain layer Android-free; expose data via `StateFlow`/`LiveData`; modularize by feature as the app grows.
- **Theming**: `MaterialTheme` controls colors, typography, shapes app-wide; supports light/dark theme switching.

## Week 4 — Networking and Web Services

- **Coroutines**: lightweight threads for asynchronous, non-blocking, sequential-style code. Key concepts: `suspend` function, `CoroutineScope`, `launch` (no result), `async` (returns `Deferred`), `runBlocking{}` (bridges non-coroutine and coroutine code), `delay` (non-blocking sleep).
- **Flow**: cold, asynchronous, reactive stream of sequentially emitted values (like a lightweight RxJava). `flow{}` builds it, `emit()` sends values, `.collect()` receives them, `.catch{}` handles upstream exceptions; nothing runs until collected.
- **Web Service**: consumer-to-provider collaboration over a network, independent of OS/browser/platform/language; uses standard protocols (HTTP/HTTPS, FTP, SMTP, XML/JSON). Functions are implemented once on the server, called many times by remote clients.
- **REST architecture**: server provides access to resources; client accesses/presents them. Resources are identified via URIs and represented as Text/JSON/XML. A REST URL commonly includes transport, provider location, action (service/function), and arguments.

| HTTP Verb | CRUD | Entire Collection | Specific Item |
|---|---|---|---|
| POST | Create | 201 Created, Location header | 404/409 |
| GET | Read | 200 OK, list (pagination/sorting/filtering) | 200 OK single item, 404 if not found |
| PUT | Update/Replace | 405 (unless replacing whole collection) | 200/204, 404 if not found |
| PATCH | Update/Modify | 405 (unless modifying collection) | 200/204, 404 if not found |
| DELETE | Delete | 405 (rarely desirable) | 200 OK, 404 if not found |

- **JSON**: standard text-based data interchange format for exchanging data over a network.
- **Retrofit**: type-safe HTTP client (Square) — auto-converts JSON↔Kotlin objects, supports coroutines/Flow, reduces boilerplate; converters: Gson, Moshi, Kotlinx.serialization.

```kotlin
interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://my-json-server.typicode.com/.../")
    .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
    .build()
```

- Repository exposes results as `Flow` (supports streaming, works naturally with Compose/StateFlow, easy `.catch{}` error handling); ViewModel collects via `viewModelScope.launch`; Compose UI observes with `collectAsState()`, rendered in a `LazyColumn`.
- **JSON parsing alternatives**: Kotlinx.serialization (native, lightweight, multiplatform); Moshi (default for Retrofit 2, flexible, `@Json` annotations); Gson (older, less Kotlin-optimized).
- Testing/dev tool: **Postman**, for building/testing API requests (methods, headers, bodies).

## Week 5 — Location-based Services and Maps Integration

- **Google Maps Android API (v2)**: part of Google Play services; provides map server access, data download, map display, gesture response; supports Markers, Polylines, Polygons, Ground Overlays, Tile Overlays. v1 is deprecated.
- Required: Google account, attribution notice, possibly an enterprise license for commercial use; must not re-implement Maps/Earth, wrap the API for redistribution, or hide copyright/ads notices.
- **API key**: identifies/tracks the calling app; obtained via Google Cloud Console (enable "Maps SDK for Android"), added to `AndroidManifest.xml` as `com.google.android.geo.API_KEY` meta-data.

```kotlin
@Composable
fun MapScreen() {
    val singapore = LatLng(1.35, 103.87)
    GoogleMap(
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 12f)
        }
    ) {
        Marker(state = MarkerState(position = singapore), title = "Singapore")
    }
}
```

- Key Compose operations: adding/customizing Markers, handling `onClick`, animating the camera (`cameraPositionState.animate`), switching `MapType` (NORMAL/SATELLITE/TERRAIN/HYBRID/NONE), drawing `Polyline`/`Circle`/`Polygon`.
- **Android Permissions**:

| Type | Description |
|---|---|
| Normal | Auto-granted at install (e.g. network state, vibration) |
| Dangerous | Require explicit runtime approval (e.g. camera, contacts, location) |

- **Accompanist Permissions**: Compose-friendly runtime permission handling (`rememberMultiplePermissionsState`, `allPermissionsGranted`, `shouldShowRationale`, `launchMultiplePermissionRequest()`), replacing imperative callback patterns.
- **Google Location Services**: `FusedLocationProviderClient` fetches last known location (`lastLocation`) or continuous updates (`LocationRequest` + `LocationCallback` via `requestLocationUpdates`/`removeLocationUpdates`), started/stopped in sync with the composable lifecycle (`LaunchedEffect`/`DisposableEffect`). Requires `ACCESS_FINE_LOCATION`/`ACCESS_COARSE_LOCATION`.

## Week 6 — Data Persistence and Dependency Management

- Apps must retain data after closing (non-volatile storage); DI makes apps modular/testable and avoids manual object creation. **Room + DataStore + Hilt** form a scalable-app foundation (local DB + settings storage + DI).
- **SQLite**: lightweight relational DB (tables of rows/columns) built into Android OS; the raw database file lives in internal storage (viewable via Device Explorer + SQLiteBrowser after pulling the file).
- **Room**: Jetpack persistence library, abstraction over SQLite — no raw SQL needed, compile-time query validation, easy Coroutines/LiveData/Flow integration.

| Feature | SQLite | Room |
|---|---|---|
| Query Validation | Runtime errors | Compile-time errors |
| Boilerplate | Raw SQL + manual connections | DAO methods abstract SQL |
| Integration | No direct LiveData/Coroutines support | Works seamlessly |
| Learning Curve | Requires SQL knowledge | Simple |

```kotlin
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val completed: Boolean = false
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks") suspend fun getAllTasks(): List<Task>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertTask(task: Task)
    @Update suspend fun updateTask(task: Task)
    @Query("DELETE FROM tasks WHERE id = :taskId") suspend fun deleteTaskById(taskId: Int)
}
```

- `@Database` links entities + DAOs and holds the SQLite connection; a singleton `getDatabase()` builder is standard. `@TypeConverter` lets Room serialize unsupported types (e.g. `Date`).
- **Preferences DataStore**: modern, async, thread-safe replacement for SharedPreferences; stores simple key-value data (dark mode, username, feature flags) via a reactive Flow-based API — ideal for Compose (`collectAsState()`).
- **Firebase**: example BaaS — Realtime Database, Cloud Firestore (NoSQL, offline support), Authentication, Storage, Hosting; can sync local Room data to the cloud for multi-device access/backup.
- **Dependency Injection (DI)**: objects receive dependencies from an external source instead of creating them internally.

| Aspect | Without DI | With DI |
|---|---|---|
| Testability | Hard (can't swap real DB for test) | Easy (inject mocks) |
| Coupling | Tight | Flexible/swappable |
| Clarity | Unclear dependencies | Explicit dependencies |

- **Hilt**: Google's DI library — auto-generates dependency-providing code, manages object lifecycles. Key annotations: `@HiltAndroidApp` (app-level setup, root of the dependency graph), `@Module` + `@Provides` (define dependencies), `@Inject` (request a dependency), `@HiltViewModel` (ViewModels), `@AndroidEntryPoint` (Activities).

## Week 8 — Background Processing and System Communication

- Heavy/long-running tasks must run outside the main thread (bad UX otherwise); background work is needed to listen for system events, run tasks reliably, and notify users outside the app.
- **WorkManager**: Jetpack library for **deferrable**, guaranteed background work — survives app kill/device restart, supports constraints (Wi-Fi only, charging only), handles Doze Mode/App Standby automatically. Typical uses: periodic sync, uploads, deferrable reminders.

```kotlin
class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        return Result.success() // or Result.failure() / Result.retry()
    }
}

val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
    .setInitialDelay(5, TimeUnit.SECONDS).build()
WorkManager.getInstance(context).enqueue(workRequest)
```

- `PeriodicWorkRequestBuilder` schedules recurring work (minimum interval **15 minutes**).
- **Broadcast Receiver**: one of the four main Android components; responds to system/app broadcasts (low battery, new SMS, connectivity changes) even in the background.

| Type | Registration | Works when app not running? | Use case |
|---|---|---|---|
| Manifest-declared | `AndroidManifest.xml` | Yes | System events (e.g. device boot) |
| Context-registered | At runtime (Activity/Service) | No (app must be running) | Dynamic, UI-lifecycle-aware handling |

- Since Android 8.0 (API 26), most implicit broadcasts are restricted from manifest registration (battery drain concerns) → prefer runtime registration by default.
- **Scoped Storage** (Android 10+/API 29): apps get a sandboxed external-storage area; shared media access needs the **MediaStore API** (images/video/audio) or **Storage Access Framework** (documents) — improves privacy, reduces accidental data leaks. Legacy full external-storage access (pre-scoped) required `READ/WRITE_EXTERNAL_STORAGE` and is high-risk.
- **Background execution limits** (Android 8+): restricts implicit-broadcast registration and background location update frequency to save battery. Solutions: **Foreground Services** for ongoing user-visible tasks (music, GPS), **WorkManager** for deferred work.
- **Notifications**: shown outside the app UI via Notification Channel (Android 8+, groups notifications), Notification Builder (title/text/actions), Notification Manager (sends to system tray). Requires `POST_NOTIFICATIONS` permission (API 33+) and channel creation before posting.

## Week 9 — Services and Lifecycle-Aware Components

- **Service**: background component with no UI, keeps running when the user switches away.

| Type | Description | Example |
|---|---|---|
| Started Service | Runs until stopped (`startService`/`stopSelf`) | Legacy background task |
| Bound Service | Other components bind and interact via `IBinder` | Music player, download manager |
| Foreground Service | Runs with a persistent, visible notification (higher priority) | Google Maps navigation |

- Service lifecycle: `onCreate()` → `onStartCommand()` (started services) or `onBind()`/`onUnbind()` (bound services) → `onDestroy()`.
- Started Service must call `stopSelf()`/`stopService()`; Android 8+ background limits mean the system often kills long-running started services → prefer Foreground Service (visible work) or WorkManager (deferred work).
- Bound Service is about **interaction**, not background execution — lives only while something is bound (`ServiceConnection`, `onServiceConnected`/`onServiceDisconnected`, `bindService`/`unbindService`).
- Foreground Service must call `startForeground(id, notification)` within 5 seconds of starting, showing an ongoing notification.
- **WorkManager vs Foreground Service**: WorkManager suits deferrable/guaranteed work surviving restarts; Foreground Service suits real-time, user-visible work needing an immediate persistent notification. WorkManager can also run foreground work via `setForegroundAsync()`.
- **Lifecycle-Aware Components** (Jetpack): `ViewModel` (survives config changes), `LiveData`/`StateFlow` (observable, lifecycle-aware), `LifecycleOwner` (Activity/Fragment), `LifecycleObserver` (reacts to lifecycle events) — avoid memory leaks, update UI only when visible, fit MVVM.

```kotlin
class CounterVM : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count
    fun increment() { _count.value++ }
}
```

## Week 10 — Hardware Integration and Machine Learning in Android

- **Sensors**: Motion (accelerometer, gyroscope, gravity), Position (magnetometer, proximity, rotation vector), Environmental (light, thermometer, barometer, humidity) — enable fitness/health, gaming, navigation, accessibility, AR/VR features.
- Access via `SensorManager`: register/unregister listeners respecting the Activity lifecycle (register in `onResume()`, unregister in `onPause()`); override `onSensorChanged()` and `onAccuracyChanged()`.
- **Camera APIs**: legacy Camera API (deprecated), Camera2 (powerful but low-level/complex), **CameraX** (modern Jetpack library on top of Camera2, simpler, lifecycle-aware) — used for face detection, barcode scanning, text recognition.
- CameraX use cases: `Preview` (live preview), `ImageCapture` (photos), `ImageAnalysis` (frame analysis, e.g. connecting to ML Kit), `VideoCapture` (recording, since v1.2).
- **ML on Android**: computers learn patterns from data to predict/recognize features; common in Vision (camera) and NLP (text/language) tasks.

| On-device ML | Cloud ML |
|---|---|
| Fast, works offline, privacy-friendly | More powerful models, needs internet (higher latency) |
| Limited by phone hardware | — |

- **ML Kit**: Google's ready-to-use mobile ML SDK — Vision APIs (barcode, face detection, image labeling, text recognition) and NLP APIs (language ID, translation, smart reply); supports custom TensorFlow Lite models; integrates easily with CameraX.
- **ML Kit Analyzer pipeline**: CameraX `ImageAnalysis` captures a frame → convert to `InputImage` → pass to an ML Kit API (e.g. `TextRecognition`) → handle the success/failure callback (e.g. update UI state with recognized text), closing the `ImageProxy` when done.

## Week 11 — Testing Android Applications

- Testing matters for reliability, maintainability, scalability, CI/CD automation, reduced manual QA effort, and avoiding technical debt (tests act as living documentation).

| Testing Type | Scope | Tools | Example |
|---|---|---|---|
| Unit Testing | Isolated logic (UseCase, ViewModel state) | JUnit, Mockito | Testing `calculateDiscount()` without UI |
| Integration Testing | Interactions between modules (ViewModel + Repository) | Fakes/mocks | ViewModel + fake repository flow |
| UI Testing | End-to-end user flows on device/emulator | Espresso (XML), Compose Testing | Tap-through checkout flow |

- **Testing Pyramid**: Unit tests (base, most tests) → Integration tests (middle) → UI tests (top, only key flows) — effort focused where tests are fastest/most valuable.
- Local (JVM) tests run without an emulator: Unit tests mock direct dependencies (Mockito); local integration tests use **Fakes** (e.g. `FakeUserRepository`) to verify cross-layer behavior. Use `StandardTestDispatcher` + `runTest`, and `advanceUntilIdle()` to let coroutines finish before assertions.
- **Compose UI Testing**: declarative API — `composeTestRule.setContent {}`; selectors `onNodeWithText()`/`onNodeWithTag()`; actions `.performClick()`/`.performTextInput()`; assertions `.assertIsDisplayed()`/`.assertTextEquals()`.
- **Testing in MVVM + Clean Architecture**: Entity/UseCase → unit tests; Repository → unit/integration tests (fakes); ViewModel → state verification; UI layer → Compose tests. Test each layer in isolation, then integrations. Prefer fakes over mocks when possible; mirror test folder structure to main code; run in CI/CD; write tests early.

## Week 12 — Course Review and Introduction to Cross-platform Development

- **Course recap** (Weeks 1–11): Mobile ecosystem & Android basics → UI foundations (Views/Layouts/Compose/Activities) → State & Architecture (MVVM, Navigation, Clean Architecture) → Networking (Coroutines, Flow, Retrofit, REST) → Location & Maps → Data Persistence & DI (Room, DataStore, Hilt, Firebase) → Background Processing & System Communication → Services & Lifecycle-Aware Components → Hardware & ML (Sensors, CameraX, ML Kit) → Testing.
- **Core stack used throughout**: Kotlin, Jetpack Compose, MVVM + Clean Architecture, Jetpack libraries (Navigation, Lifecycle, WorkManager, Hilt, DataStore, Accompanist), Android Studio/Gradle.
- **React Native**: framework for building natively-rendering mobile apps using only JavaScript, based on Facebook's React; renders real native UI components (not WebViews) by invoking native rendering APIs (Objective-C/Java); exposes JS interfaces for platform APIs. Examples: Facebook, Instagram, Skype, Tesla, Airbnb, SoundCloud.

| React Native | Native Development |
|---|---|
| Cross-platform (write once) | Platform-specific (write twice) |
| Simple performance, low dev cost | Complex performance, high dev cost |
| Better scalability, large community | Better UX/UI, more native module support |
| Less secure | Plenty of APIs/third-party libraries |

- Choose **Native** for: complex apps (e.g. messengers), frequent updates, native-UX focus, apps relying on native device features, IoT apps, single-platform apps.
- Choose **React Native** for: simple/uniform apps, cross-platform launches, low budget, social/e-commerce apps, startups.
- **Project setup**: requires Node.js/NPM, JDK 8, Expo/Yarn (optional); `npx create-expo-app@latest ProjectName` or `react-native init ProjectName`.
- **Running as Android (Windows)**: create `android/local.properties` with `sdk.dir=...`, run `npm start`, launch an emulator, build from `App.js`.
