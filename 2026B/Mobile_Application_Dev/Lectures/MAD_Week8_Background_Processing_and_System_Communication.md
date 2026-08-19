# Background Processing and System Communication

**Course:** COSC2657/COSC2543/COSC2729 — Android Development
**Lecturer:** Minh T. Vu

---

## Week Agenda

- Introduction to Background Processing
- WorkManager
- Broadcast Receiver
- Scoped Storage & Background Limitations
- Notifications

---

## Why Background Processing?

- Applications cannot always handle everything on the main thread (bad UX, missed events, unsynced, etc.) → **Heavy** or **long-running** tasks must run **outside** the main thread
- Background tasks are essential for:
  - Listening to system events (network, battery, SMS, etc.)
  - Running tasks reliably (sync, uploads, reminders)
  - Communicating with the user outside the app (notifications)

---

## 1. WorkManager

- WorkManager is a Jetpack library that schedules and executes **deferrable** & **asynchronous** tasks flexibly in the background
- Best choice when tasks must:
  - Run even if app is killed or device restarts
  - Follow some constraints (only on Wifi, only when charging)
- Able to handle **Doze Mode** and **App Standby** automatically
- Typical tasks:
  - Periodic sync with server
  - Upload logs, images, or analytical data
  - Deferrable reminders & scheduled jobs

### WorkManager Example – Define the Work

**Dependencies:**
```kotlin
implementation("androidx.work:work-runtime-ktx:2.9.0")
```

**Worker class:**
```kotlin
class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("WorkManagerTesting", "Task running...")

        // Indicate whether the work finished successfully with the Result
        // or Result.failure() if it failed
        // You can also use Result.retry() if you want to retry the work later
        return Result.success()
    }
}
```

### Create a WorkRequest and Submit to the System

```kotlin
val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
    .setInitialDelay(5, TimeUnit.SECONDS)
    .build()
WorkManager.getInstance(context).enqueue(workRequest)
```

- `WorkManager` offers a lot of flexibility in how you schedule your work. You can schedule it to run periodically over an interval of time instead using `PeriodicWorkRequestBuilder` — note that the **minimum interval is 15 minutes**
- The `WorkRequest` can include additional information, including the running constraints, input, a delay, and backoff policy for retrying

---

## 2. Broadcast Receiver

### What is a Broadcast Receiver?

- Broadcast Receiver is one of the four main Android components. It allows apps to respond to broadcasts triggered from the system or from other apps
- Examples:
  - Low battery warning
  - New SMS received
  - Wi-Fi or mobile data connectivity changes
- Broadcast Receivers help your apps **stay aware of important events**, even when running in the background

### Types of Broadcast Receivers

**Manifest-declared receivers:**
- Registered in `AndroidManifest.xml`
- Works even if the app is **not running**
- Used for **system events** (e.g., device boot)

**Context-registered receivers:**
- Registered **at runtime** in an activity or service
- Active only while the **app is running**
- Used for **dynamic event handling**

### Broadcast Receiver — Restrictions

- Starting with Android 8.0 (API 26, Oreo), Google restricted a large set of implicit broadcasts because they can work even when the app is not running → wake up too many apps at once → drain battery
- Therefore, the best practice is preferring **runtime registration as the default**, and only using manifest registration for some allowed broadcasts where background awareness is necessary
- **Rule of thumb:**
  - **Runtime:** dynamic, UI-lifecycle aware, battery-friendly → use whenever possible
  - **Manifest:** only when you need the app to wake up on system events while not running

### Broadcast Receiver – Runtime Registration

**Broadcast Receiver class:**
```kotlin
class AirplaneModeChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeOn = intent?.getBooleanExtra("state", false) ?: false
        val message = if (isAirplaneModeOn) "Airplane Mode ON" else "Airplane Mode OFF"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
```

**Register Receiver and unregister on dispose:**
```kotlin
@Composable
fun AirplaneModeStatusScreen() {
    val context = LocalContext.current
    val receiver = AirplaneModeChangeReceiver()

    DisposableEffect(Unit) {
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        context.registerReceiver(receiver, filter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}
```

- Toggle the Airplane Mode option in the emulator to see the Toast

### Broadcast Receiver – Manifest Registration

**Declare the receiver in `manifest.xml`:**
```xml
<receiver
    android:name=".BootCompletedReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
    </intent-filter>
</receiver>
```

- This event needs permission:
```xml
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
```

**Broadcast Receiver class:**
```kotlin
class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d("BootReceiver", "Device rebooted! App was woken up.")
        }
    }
}
```

- Reboot the emulator to see the Log in Logcat

---

## 3. Scoped Storage & Background Limitations

### Pre-Scoped (Before Android 10)

```kotlin
// Direct file path access (not recommended now)
val file = File(Environment.getExternalStorageDirectory(), "myfile.txt")
file.writeText("Legacy access example")
```

- Required `READ_EXTERNAL_STORAGE` and `WRITE_EXTERNAL_STORAGE` permissions
- App could freely read/write anywhere on external storage
- Therefore, high risk of accidental data exposure

### What is Scoped Storage?

- Introduced in **Android 10 (API 29)**
- Changes how apps access **external storage**
- Apps get a **sandboxed area** in external storage
- Access to shared storage requires:
  - **MediaStore API** → for images, videos, audio
  - **Storage Access Framework (SAF)** → for documents
- Benefits:
  - Provides better privacy and security: apps cannot freely access other apps' files
  - Simplifies file management for the user
  - Reduces risk of accidental data leaks

### App Access Restrictions

Apps using scoped storage can have the following levels of access (actual access is implementation specific):

- **Read** and **write** access to their own files with no permissions
- **Read** access to other apps' media files with `READ_EXTERNAL_STORAGE` permission
- **Write** access to other apps' media files is allowed only with direct user consent (exceptions granted to System Gallery and apps that are eligible for All Files access)
- **No read or write** access to other apps' external app data directories

### Background Execution Limitation (Android 8+)

- To improve battery life and overall system performance, Android 8.0 (Oreo) **restricts** what apps can do while **running in the background**
  - → Affects background services and broadcast receivers, preventing apps from using resources when not in the foreground
- Example:
  - Apps can no longer register for most implicit broadcasts in the manifest file
  - Apps can receive location updates only a few times per hour while in the background
- Suggested Solutions:
  - **Foreground services** for ongoing tasks like music, GPS
  - **WorkManager** for deferred work

### Notes for Developers

Apps must:
- Use **scoped storage APIs** for file access
- Use **WorkManager** for background tasks
- Ensure **proper user permissions**

Always check **version compatibility** using `Build.VERSION`:

```kotlin
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
    // Scoped storage enforced
    Log.d("ScopedStorage", "Running on Android 10 or higher")
} else {
    Log.d("ScopedStorage", "Legacy storage model applies")
}
```

---

## 4. Notifications

### What are Notifications?

- Notifications are messages shown **outside the app UI**
- Can be useful for:
  - Reminding users
  - Showing background updates
  - Communicating system events
- Notification Components:
  - **Notification Channel** (Android 8.0+): Groups notifications
  - **Notification Builder:** Creates notifications with title, text, actions
  - **Notification Manager:** Sends notification to system tray

### Notification – Create Channel

**Dependencies:**
```xml
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

**Channel creation:**
```kotlin
object Notify {
    const val CHANNEL_ID = "demo"

    // This function ensures that the notification channel is created if it doesn't already exist.
    fun ensureChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (mgr.getNotificationChannel(CHANNEL_ID) == null) {
                mgr.createNotificationChannel(
                    NotificationChannel(CHANNEL_ID, "Demo", NotificationManager.IMPORTANCE_DEFAULT)
                )
            }
        }
    }
}
```

### Create Notifications and Send to Tray

Make sure the `POST_NOTIFICATIONS` permission is granted, then create the channel once:

```kotlin
@Composable
fun NotificationDemoScreen() {
    val context = LocalContext.current

    // Ask for permission (API 33+)
    var granted by remember {
        mutableStateOf(
            Build.VERSION.SDK_INT < 33 ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { ok ->
        granted = ok
    }

    // Create channel once
    LaunchedEffect(Unit) { Notify.ensureChannel(context) }

    Column(Modifier.padding(16.dp)) {
        Button(onClick = {
            if (Build.VERSION.SDK_INT >= 33 && !granted) {
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }) { Text("Request notification permission") }

        Spacer(Modifier.height(12.dp))

        Button(onClick = {
            // Build a PendingIntent to open a URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rmit.edu.vn"))
            val pi = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            // Build and show a simple notification
            val n = NotificationCompat.Builder(context, Notify.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Hello, students")
                .setContentText("This is the simplest notification")
                .setContentIntent(pi) // now opens RMIT website
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(context).notify(1, n)
        }) { Text("Show notification") }
    }
}
```

- Wrap an `Intent` with `PendingIntent` for the notification's action
- Build and show a simple notification
