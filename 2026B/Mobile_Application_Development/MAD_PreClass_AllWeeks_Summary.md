# MAD Pre-Class Summary — All Weeks

## Week 1 — An Overview of the Current Mobile Market

- A **mobile OS** is software that lets phones/tablets run apps and bridges hardware and software. OS market cycle: More users → More developers → More apps → Even more users, which let Android/iOS dominate and lock out new entrants (BlackBerry OS, Symbian, Windows Phone failed to keep up).

| Feature | Android | iOS |
|---|---|---|
| Device Range | Many manufacturers | Apple only |
| Fragmentation | Bad (many OS versions/hardware) | Good (mostly recent OS) |
| OS | Core open source, Google services proprietary | Fully proprietary |
| Languages | Java, Kotlin, Scala, Dart, C, HTML-5 | Swift, Objective-C, HTML-5 |
| Development Machine | Windows/Mac/Linux | Mac/Hackintosh only |
| Main Market | Low–mid range | High-end/business |

- **Android** is Linux-based, not built from scratch; it also powers tablets, smartwatches, TVs, cars.
- Android versions: dessert-named until Android 9 (Pie), numeric from Android 10 onward.
- **Android architecture layers** (bottom → top): Linux Kernel (memory/process/hardware access) → Android API + ART (runs/compiles apps, memory management) → Application Framework (UI components, storage, notifications toolkit) → System Applications (default apps: dialer, messaging, camera).
- **Android Studio** is the official IDE for writing, designing, and testing Android apps.
- **Kotlin** is a modern, statically-typed language interoperable with Java; >60% of Android apps use it, officially recommended by Google since 2019.

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

## Week 2 — Activity, Activity Lifecycle, and Layout Basics

- An **Activity** is a self-contained window/screen a user interacts with; most apps have one main activity plus several supporting activities (e.g., inbox → compose → view message).
- **Activity Lifecycle** — the system manages transitions automatically:

| Callback | Description |
|---|---|
| `onCreate()` | First callback; initialises UI (`setContentView()`); runs once unless destroyed/recreated. |
| `onStart()` | Activity becomes visible but not yet interactive. |
| `onResume()` | Activity is in the foreground, receiving input; most active functionality runs here. |
| `onPause()` | Loses focus but stays partially visible (must be quick). |
| `onStop()` | No longer visible; stays in memory but should release unneeded resources. |
| `onDestroy()` | Final callback before destruction; cleans up remaining resources. |

- **Canonical layout types**:
  - **List-detail**: two side-by-side panes (list + item detail) — e.g. messages & conversation, inbox & selected email.
  - **Feed**: grid of content cards — e.g. news, photos, social media.
  - **Supporting pane**: primary content area + secondary supporting panel — e.g. document editing with comments.
- **Jetpack Compose layouts** are defined in Kotlin, not XML. Composables are functions describing *what* the UI looks like.
  - `Column` — children stacked vertically.
  - `Row` — children placed horizontally.
  - `Box` — children stacked on top of each other (overlays).
- **Modifiers** style/position composables and are chainable: `padding()`, `size()`/`fillMaxSize()`, `background()`, `clickable()`, `horizontalArrangement`/`verticalArrangement`, `Alignment`.
- When analysing a UI design: identify main sections first, then break each down into smaller composable building blocks (e.g. search bar, horizontal scroll row, row/column section).

## Week 3 — State, Navigation, and Code Organisation

- **State** is information that can change over time and affects what's shown (e.g. an "expanded" dropdown, a button's idle/hovered state).
- **Navigation Component**: modern replacement for activity-based screen switching. Each screen in the nav graph is a Composable; the component manages the back stack, data passing between screens, and deep links.
- **Theming** in Jetpack Compose defines colours, fonts, shapes, and spacing once and applies them app-wide for visual consistency and easier updates (vs. styling each element individually).
- **MVVM Pattern** — separates an app into three layers:

| Component | Responsibilities |
|---|---|
| Model | Data + retrieval logic (data classes, repositories, network calls). No UI logic. |
| View | Compose screens; observes state and renders UI. |
| ViewModel | Holds UI state, responds to input, updates data, notifies the View. |

  Benefits: separation of concerns, testability (ViewModel tested independently), reusability, scalability.

- **Clean Architecture** organises the whole codebase (not just presentation) into nested layers:
  - **Domain Layer** (centre) — Entities.
  - **Application Layer** — Use Cases.
  - **Adapter Layer** ("Interface adapters") — APIs, Gateways, UI, Databases.
  - **Infrastructure Layer** ("Frameworks and drivers", outer edge) — Web, Devices, Network, I/O, External Interfaces.
  - Goal: easier to understand, modify, test, and adapt to changing requirements/technology.

## Week 4 — Web Services and Asynchronous Tasks

- A **Web Service** lets computers share information over the internet: a provider offers a service, consumers use it, regardless of underlying system/language differences.
- Common protocols: HTTP/HTTPS (web communication), FTP (file transfer), SMTP (email), XML/JSON (structuring data).
- **RESTful services** use standard HTTP verbs to access/modify resources:

| Action | HTTP Verb | Example |
|---|---|---|
| Retrieve data | GET | Get a list of photos |
| Add new data | POST | Submit a new photo |
| Update existing data | PUT | Edit a photo description |
| Remove data | DELETE | Delete a saved photo |

- **Asynchronous tasks**: background jobs that run separately from the main UI thread so the app doesn't freeze during network calls.
  - **Synchronous**: app sends request and blocks until a response arrives.
  - **Asynchronous**: app sends request and keeps running other tasks (scrolling, tapping) while waiting.
  - `AsyncTask` was the older way to wrap HTTP requests for background work.
- **Retrofit**: type-safe HTTP client library; handles making requests, converting JSON responses into Kotlin objects, and supports GET/POST/PUT/DELETE.
- **Kotlin Coroutines**: modern way to write asynchronous code that reads like sequential code, avoiding callback complexity and AsyncTask issues; used together with Retrofit.

## Week 5 — Maps and Permissions

- **Google Maps Android API** (Maps SDK for Android): lets apps display maps, access location data, and handle user interactions; bridges the app to Google's mapping services since maps can't be fully stored locally. Provides map data (tiles, imagery), interactive controls (zoom/pan/tap), location services (GPS, place detection), and custom overlays (markers, routes).
- **Location-based services** continuously track user location to update app experience (e.g. centre map on user, load nearby content, trigger location events — as in Pokémon GO).
- Typical integration steps: Setup (API key, SDK, permissions) → Display (MapFragment, load data) → Interact (markers, gestures) → Location (track position, respond to movement).
- Reminders: always show proper attribution, respect Google's copyright notices, consider enterprise licensing for commercial use, and handle location permissions responsibly for user privacy.
- **Android Permissions**:

| Permission Type | Description |
|---|---|
| Normal | Auto-granted at install (e.g. network state, vibration). |
| Dangerous | Require explicit user approval (e.g. camera, contacts, location). |

- **Accompanist Permissions**: a Compose-friendly library providing Composable functions to handle runtime permissions declaratively (state management + auto recomposition), replacing complex imperative callback patterns.

```kotlin
@Composable
fun CameraScreen() {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    when {
        cameraPermissionState.status.isGranted -> {
            // Show camera UI
        }
        cameraPermissionState.status.shouldShowRationale -> {
            // Show explanation UI
        }
        else -> {
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Grant Camera Permission")
            }
        }
    }
}
```

## Week 6 — Data Persistence and Dependency Injection

- **SQLite**: built-in lightweight local database, no server/internet needed; requires writing raw SQL manually (verbose, error-prone).
- **Room Database**: Google's wrapper over SQLite using Kotlin data classes and annotations instead of raw SQL.

| Feature | SQLite | Room |
|---|---|---|
| Query Validation | Runtime errors | Compile-time errors |
| Boilerplate | Raw SQL + manual connections | DAO methods abstract SQL |
| Integration | No direct LiveData/Coroutines support | Works seamlessly with LiveData/Coroutines |
| Learning Curve | Requires SQL knowledge | Simple |

- **Preferences DataStore**: modern replacement for SharedPreferences; stores simple key-value data (dark mode setting, app version, username, feature flags) — not full databases.
- **Firebase**: example of a NoSQL, BaaS (Backend as a Service) option.
- **Dependency Injection (DI)**: objects receive dependencies from external sources instead of creating them internally.

| Aspect | Without DI | With DI |
|---|---|---|
| Testability | Hard (can't swap real DB for a test one) | Easy (inject mocks) |
| Coupling | Tight | Flexible/swappable |
| Clarity | Unclear dependencies | Explicit about what each class needs |

- **Hilt**: Google's DI library — auto-generates dependency-providing code, manages object lifecycles, reduces boilerplate, integrates with Android components.

## Week 8 — Background Processing and System Communication

- Background processing is needed for: long-running tasks (downloads, sync, image processing), keeping the UI responsive (not blocking the main thread), and reacting to system events (low battery, connectivity change, new data). Android 8+/10+ impose strict limits on background activity to save battery/performance.
- Real-world examples: Google Maps (GPS tracking/rerouting), Grab (location/ride/payment tracking), Messenger (message sync), Spotify (streaming/downloads), Dropbox (auto-sync).
- **Four components for system communication**:

| Component | Description | Example |
|---|---|---|
| BroadcastReceiver | Listens for system/app broadcasts | Banking app listens for "transaction completed" |
| Scoped Storage | Secure, permission-based file access | Photo editor requests gallery access |
| WorkManager | Deferrable, guaranteed background work; survives app kill/device restart | Grab keeps searching for a driver even if app is force-closed |
| Notifications | Alerts users outside the main UI | News app sends a breaking news lock-screen alert |

## Week 9 — Services and Lifecycle-Aware Components

- **Services** are one of the four main Android components (with Activities, BroadcastReceivers, ContentProviders); they let apps keep running when the user switches away.

| Service Type | Description | Example |
|---|---|---|
| Foreground Service | User-visible, persistent notification | Google Maps giving directions |
| Background Service | Runs silently (restricted from Android 8+) | Data syncing |
| Bound Service | Components bind and interact with it | Fitness tracker showing live step count |

- **WorkManager vs Foreground Service**: WorkManager suits deferrable, guaranteed work surviving restarts; Foreground Service suits real-time, user-visible work needing an immediate persistent notification. Choice depends on company practice, product needs, and OS restrictions.
- **Lifecycle-Aware Components** (part of Android Jetpack: ViewModel, StateFlow, LifecycleObserver) automatically respond to lifecycle events and live only as long as their scope exists.

## Week 10 — Sensors and Hardware

- A **sensor** detects/measures physical properties (environment or device) and converts them into digital data. Can be hardware-based (real chip, e.g. gyroscope) or software-based (derived from multiple hardware sources).

| Category | Sensor | Description |
|---|---|---|
| Motion | Accelerometer | Detects acceleration/tilt |
| Motion | Gyroscope | Measures rotation |
| Motion | Step Counter | Counts steps |
| Environmental | Ambient Light Sensor | Measures brightness |
| Environmental | Barometer | Measures air pressure (assists GPS) |
| Environmental | Thermometer | Reads temperature |
| Position | Magnetometer | Detects magnetic fields (compass) |
| Position | GPS | Precise geographic location |

- Hardware components beyond sensors: GPS chip, camera, microphone.
- **ML Kit**: Google's on-device ML SDK; fast + private processing, integrates with CameraX. Popular APIs: Text Recognition, Face Detection, Object Detection & Tracking, Barcode Scanning (e.g. a shopping app scanning barcodes for product details).

## Week 11 — Testing Android Applications

- Testing catches bugs early, verifies business logic and UI behaviour, and increases confidence when refactoring or adding features.

| Testing Type | Description | Tools | Example |
|---|---|---|---|
| Unit Testing | Tests business logic in isolation | JUnit (assertions), Mockito (mocking dependencies) | Testing `calculateDiscount()` without the full checkout UI |
| UI Testing | Tests UI behaviour (buttons, text, navigation) | Espresso (View-based UI) | Simulate tapping through a shopping app; verify "Add to Cart"/"Checkout" |
| Compose Testing | UI testing for Compose apps | Compose Testing APIs | Check a Compose "Login" button appears and triggers navigation |

## Week 12 — Introduction to Cross-Platform Development

| Approach | Description | Pros | Cons |
|---|---|---|---|
| Native | Built per-platform with official language/SDK (Android: Kotlin/Java; iOS: Swift/Objective-C) | Best performance, full hardware API access, consistent UX | Separate codebases, more time/resources |
| Cross-Platform | Shared codebase across platforms; framework handles rendering/bridging | Faster dev, single team, lower cost | May not match native look/feel; can be less optimised |

| Framework | Language | Approach | Strengths | Limitations |
|---|---|---|---|---|
| Flutter (Google) | Dart | Renders its own widgets | Fast dev (hot reload), strong ecosystem | UI may feel less "native" |
| React Native (Meta) | JavaScript | Bridges JS to native components | Large community, reuses web dev skills | Performance drops on complex UIs |
| Kotlin Multiplatform (JetBrains) | Kotlin | Shares business logic, keeps native UI | Good for Android teams, Kotlin synergy | Still evolving, less UI abstraction |
| .NET MAUI (Microsoft) | C# | Unified framework for Android/iOS/Windows/macOS | Strong enterprise fit, MS ecosystem | Smaller mobile dev community |
