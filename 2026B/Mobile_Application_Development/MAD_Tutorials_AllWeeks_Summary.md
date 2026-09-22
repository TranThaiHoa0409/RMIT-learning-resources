# MAD Tutorials Summary — All Weeks

## Tutorial 1 — Intro to Android

- **Goal**: get familiar with Android Studio and basic layout techniques ahead of Week 2.
- **Project setup**: create a project via *Projects → New Project*; template *Phone and Tablet → No Activity*; pick a moderate Minimum SDK (e.g. Android 7.0 Nougat, ~98% device reach).
- **Studio layout**: Explorer window (files/resources) on the left, Code window on the right; both Kotlin and Java are usable in the same project; `AndroidManifest.xml` is the app's configuration file.
- **Two UI approaches** introduced: XML-based UI (traditional, `res/layout/activity_main.xml`, drag-and-drop in the Design tab) and Jetpack Compose (modern, declarative, pure Kotlin) — course starts with XML and transitions to Compose.
- Create a new Activity via *New → Activity → Empty Views Activity*, choosing Kotlin and (optionally) Launcher Activity; the activity is auto-registered in `AndroidManifest.xml`.
- Running the app: use the emulator (Run button), creating a virtual device (e.g. Pixel 4, API 30) if none exists.
- **Layout practice**: drag LinearLayout (vertical/horizontal), Button, EditText, TextView onto the Design panel; inspect via the Component Tree. Default layout is ConstraintLayout — components need explicit constraints or they jump to (0,0) at runtime.
- **Coding practice**: set a button click listener programmatically with `findViewById` + `setOnClickListener`, show feedback via `Toast.makeText(...).show()`.
- **Exercises**: (1) marriage-oracle app using name/age inputs and a random-number rule; (2) BMI calculator with a metric/imperial unit toggle; (3) simple 1–10 lottery guessing game with restart; (4) basic calculator (add/subtract/multiply/divide).
- **Extra**: repeat the same exercise using a Jetpack Compose *Empty Activity* project, exploring Live Edit, Interactive Preview, and Compose Modifiers.

## Tutorial 2 — Basic Layout and Activity Interaction

- **Goal**: practice Activity communication and dynamic UI with Jetpack Compose, in preparation for Assignment 1.
- **App to build** — three activities:

| Screen | Description |
|---|---|
| `MainActivity` | Entry screen: name `TextField` + "Student Form" / "Student Services" buttons |
| `StudentFormActivity` | Form pre-filled with the name from `MainActivity`; Submit and Go to Services buttons |
| `StudentServicesActivity` | List of service buttons (e.g. Library, IT Support, Counseling) |

- **Behavior to implement**: `MainActivity` passes the entered name to `StudentFormActivity`; on Submit, `StudentFormActivity` returns to `MainActivity` showing "Thank you \<Student_Name\> for submitting your form."; selecting a service in `StudentServicesActivity` returns to `MainActivity` showing "Thank you for selecting \<Service_Name\> service." — exercising two-way activity communication (Intents/`ActivityResultLauncher`) and dynamic UI.

## Tutorial 3 — State Management and Theming

- **Goal**: practice state hoisting, Compose navigation, and MVVM + Clean Architecture by building a multi-screen task manager (preparation for upcoming assignments).
- **App to build** — three composable screens under a single Activity:

| Screen | Description |
|---|---|
| `HomeScreen` | Task list (`LazyColumn`), task count via `derivedStateOf`, `FloatingActionButton` "Add Task" |
| `AddTaskScreen` | Form: Title (required), Due Date (required), Description (optional); `Save` button |
| `TaskDetailScreen` | Shows task details; `Delete` button |

- **Behavior**: tapping a task navigates to `TaskDetailScreen` (passing the task ID); "Add Task" navigates to `AddTaskScreen`; `Save` is disabled until Title and Due Date are filled, then adds the task via the ViewModel, navigates back to `HomeScreen`, updates the task count, and shows a `Snackbar` ("Task '\<Task_Title\>' added"); `Delete` removes the task, navigates back, and shows a `Snackbar` ("Task '\<Task_Title\>' deleted").
- Exercises state hoisting, `NavController`/`NavHost` navigation, and separating logic via MVVM/Clean Architecture layers.

## Tutorial 4 — Networking and Web Services

- **Goal**: build an app consuming a pre-built RESTful web service (GET/POST provided as instructions; DELETE/PUT left as an exercise).
- **Preparation**: run a local JSON server (`json-server --watch db.json --port 3004`) and test endpoints (e.g. `/students`, `/comments`); test GET/POST/PUT/DELETE requests with **Postman** against the local service or a hosted fake API (`my-json-server.typicode.com`).
- **Implementation steps**:
  1. Define a `Student` data model with `@Serializable` (no `id` field — json-server auto-assigns one).
  2. Create a Retrofit `StudentApi` interface with `@GET`, `@POST`, `@DELETE`, `@PUT` suspend functions.
  3. Build a Retrofit instance using `10.0.2.2` (not `localhost`) as the base URL when calling a local server from the emulator, with troubleshooting notes (Airplane Mode toggle, HTTP proxy settings, or ngrok tunneling if `10.0.2.2` is unreachable).
  4. Adapt the lecture's user-list code to display students instead, adding a `FloatingActionButton` to add a new student.
  5. Build `AddStudentScreen` (name `TextField` + Submit button posting via the ViewModel).
  6. Implement `addStudent(name, onComplete)` in the ViewModel and wire up the navigation graph (from Week 3) across screens.
- **Exercise**: implement per-student **delete** and **update** operations in the list.

## Tutorial 5 — Location-based and Maps Integration

- **Goal**: build an app integrating Google Maps and Location Services — display an interactive map, customize it with markers/zoom/map types, handle runtime location permissions, retrieve current location via the Fused Location Provider, and implement periodic location updates with camera movement.
- **Preparation**: enable Maps SDK for Android and get an API key; add Maps/Location Services/Accompanist Permissions dependencies; display a map and practice markers, marker customization, click handling, and map-type switching in Compose; request FINE/COARSE location permissions; fetch the last known location; implement continuous location updates (testable via the Emulator's location settings).
- **Exercise**: extend the tutorial app to show nearby restaurants from a RESTful service —
  1. Prepare/use a REST endpoint (e.g. local JSON Server at `http://10.0.2.2:3004/restaurants`) with restaurant lat/lng data.
  2. Retrieve the restaurant list with Retrofit + coroutines.
  3. Display a marker per restaurant (name as title).
  4. Add a `FloatingActionButton` to refresh the list and markers.
  5. Implement `onMapClick` to add a new restaurant: show a dialog for the name, POST the new restaurant (with tapped lat/lng) to the server, and refresh markers on success.

## Tutorial 6 — Persistent Storage

- **Goal**: build a currency-management app using Room Database, Preferences DataStore, and Hilt — local Room storage, a coroutine-driven ViewModel, a reactive `LazyColumn` list, insert/delete/update operations, persisted sort-order preference, and search-by-country.
- **Preparation — Room**: add Room dependencies; create `Entity`, `Dao`, `AppDatabase` classes; build a `CurrencyViewModel` interacting with the DAO via coroutines; build a Compose UI listing currencies (state from the ViewModel), a form to add a currency (country + currency name), and per-item delete.
- **Preparation — DataStore & Hilt**: add DataStore dependency; create a `PreferencesManager` to store/read UI preferences (dark mode, font size) with `collectAsState()`; integrate Hilt into the project.
- **Exercise** — extend into a full currency manager:
  1. Show each currency in a `Card`; tapping opens a dialog to edit/update the currency name.
  2. Add a `TextField` to filter/search currencies by country.
  3. Add a sort-order toggle (A–Z / Z–A), persisted via Preferences DataStore.
  4. (Extra) Sync the currency list to Firebase Realtime Database/Firestore — post new currencies to Firebase and pull data into local Room on first app load.

## Tutorial 8 — Background Processing

- **Goal**: build an app that logs user actions and system events to scoped storage, periodically summarizes them via WorkManager, and delivers updates via Notifications, with a Compose UI to view the log and trigger custom broadcasts.
- **Setup**: add WorkManager dependency; add `POST_NOTIFICATIONS` permission; declare a manifest `<receiver>` for `SystemEventReceiver` on `BATTERY_LOW` (runtime receivers are registered programmatically instead).
- **Data layer**: an `Event` data class (`timestamp`, `source`, `type`, `message`); `EventLogRepository` appends/reads events to/from `event_log.csv` in scoped, app-specific external storage (`context.getExternalFilesDir(null)` — no extra permission needed) and counts recent events for summaries.
- **Notifications helper**: a `Notify` singleton with `ensureChannel()` (API 26+ channel creation) and `show(context, title, text)` (builds/posts a notification with a `PendingIntent` to `MainActivity`); request notification permission for API 33+.
- **Broadcast receivers**: `CustomEventReceiver` for custom `ACTION_HAPPY`/`ACTION_SAD` actions (registered/unregistered dynamically in `MainActivity`, logging + notifying on receipt); `SystemEventReceiver` for `BATTERY_LOW` (manifest-registered so it fires even when the app isn't running).
- **WorkManager**: `SummaryWorker` reads the log, counts the last hour's events, and sends a summary notification if any exist; scheduled every 15 minutes via `PeriodicWorkRequestBuilder` with a not-low-battery constraint, enqueued in `MainActivity`.
- **UI**: a `Scaffold` with "I'm Happy" / "I'm Sad" / "Refresh" buttons (sending broadcasts and refreshing state) and a `LazyColumn` showing all logged events (timestamp, type, source, message).
- **End-to-end flow**: button press → custom broadcast → logged → notification; simulated low battery → system broadcast → logged → notification; WorkManager wake → counts recent events → summary notification; UI reads the log file and displays all past events.

## Tutorial 9 — Services and Lifecycle Components

- **Goal**: build a simulated music player demonstrating a Foreground Service for background playback, a Bound Service for Play/Pause/Stop control, ViewModel + StateFlow UI state, a media-style notification, and downloading a song via WorkManager.
- **Setup**: permissions `POST_NOTIFICATIONS`, `FOREGROUND_SERVICE_MEDIA_PLAYBACK`, `INTERNET`; declare `MusicPlayerService` with `foregroundServiceType="mediaPlayback"`.
- **`MusicPlayerService`**: starts as a Foreground Service with a persistent notification, also acts as a Bound Service so the UI can call its methods directly; maintains `isPlaying` state; exposes `play()`, `pause()`, `stop()`; updates notification content (Playing vs Paused) and uses a MediaStyle notification with play/pause actions.
- **`PlayerViewModel`**: holds playback state via `MutableStateFlow`/`StateFlow`, with an `updateState(isPlaying: Boolean)` method.
- **MainActivity & UI**: binds to `MusicPlayerService` via `ServiceConnection` (starting in `onStart()` with `ContextCompat.startForegroundService()`, unbinding in `onStop()`); UI shows playback state and Play/Pause/Stop buttons, each calling the service method and updating the ViewModel.
- **Exercises**: add `nextTrack()`/`previousTrack()` with UI buttons; enhance the notification with MediaStyle play/pause/stop actions; keep notification and UI state in sync via the ViewModel; add and display a track name; add a WorkManager-based song download from a URL with progress shown in the notification.

## Tutorial 10 — Hardware Integration and Machine Learning

- **Goal**: build a Smart Scanner app combining the Proximity Sensor (pause/resume scanning), GPS via Fused Location Provider, a CameraX preview, real-time ML Kit object classification, and a Compose UI showing detected objects + location.
- **Setup**: permissions `CAMERA`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`; dependencies for CameraX (`camera-core`, `camera-camera2`, `camera-lifecycle`, `camera-view`), ML Kit image-labeling, and Play Services Location.
- **Runtime permissions**: since Android 6.0 (API 23), Camera and Location are "dangerous" permissions requested at runtime via `ActivityCompat.requestPermissions`, checked with `ContextCompat.checkSelfPermission`.
- **Proximity sensor**: reports NEAR (covered) or FAR (uncovered) via `onSensorChanged()`; used to pause camera-frame analysis when NEAR and resume when FAR, with a `Toast` for feedback.
- **Location**: `FusedLocationProviderClient` requests continuous updates every 2 seconds (`LocationRequest.Builder` with `PRIORITY_HIGH_ACCURACY`), displaying latitude/longitude alongside detection results.
- **Camera + object classification**: CameraX `ImageAnalysis` provides frames → wrapped as `InputImage` → processed by ML Kit's `ImageLabeler`, showing the top 3 labels with confidence scores; analysis is skipped while scanning is paused (proximity NEAR).
- **UI**: top half shows the camera preview; bottom half is a results panel showing detected objects and location text, or a "Scanning paused" message when applicable.
