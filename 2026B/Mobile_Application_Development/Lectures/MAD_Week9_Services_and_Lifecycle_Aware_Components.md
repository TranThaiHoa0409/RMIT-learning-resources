# Services and Lifecycle-Aware Components

## Week Agenda

- Introduction to Service
- Use Foreground Services and WorkManager
- Introduction to Lifecycle-aware components

## Introduction to Services

### What is a Service?

- A **Service** is a component that supports performing **long-running operations** in the background
- Unlike Activities:
  - No UI
  - Runs even if user switches apps/app is killed
- Types of Services:
  - **Started Service** -> runs until stopped
  - **Bound Service** -> allows other components (Activities/Fragments) to bind and interact
  - **Foreground Service** -> runs with a visible **notification** (higher priority)

### Service Lifecycle

- **onCreate()** -> called when service is created
- **onStartCommand()** -> called when service is started with `startService()`
- **onBind()** -> returns binder for bound services
- **onDestroy()** -> called when service is stopped/destroyed

![Service Lifecycle diagram showing the unbounded service flow (call to startService → onCreate → onStartCommand → Service running → onDestroy → Service shut down) and the bounded service flow (call to bindService → onCreate → onBind → Clients are bound to service → onUnbind → onDestroy → Service shut down)](images/Week9/05_service_lifecycle_diagram.png)

### Started Service (legacy)

- Started by calling `startService(Intent)` or `ContextCompat.startForegroundService(Intent)`
- Runs **independently** of the component that started it
- Must stop itself with `stopSelf()` or be stopped with `stopService()`
- However, from Android 8.0+, background limits -> System restrictions often kill it -> use Foreground Service (for user-visible tasks) or WorkManager (for deferred work)

```kotlin
class MyStartedService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d(tag = "MyStartedService", msg = "onCreate() called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag = "MyStartedService", msg = "onStartCommand() called")

        // Simulate background work
        Thread {
            for (i in 1..5) {
                Thread.sleep(millis = 2000)
                Log.d(tag = "MyStartedService", msg = "Working... step $i")
            }
            Log.d(tag = "MyStartedService", msg = "Work finished → calling stopSelf()")
            stopSelf() // Explicitly stop service after work is done
        }.start()

        // START_NOT_STICKY: don't recreate if killed
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag = "MyStartedService", msg = "onDestroy() called — Service terminated")
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
```

```kotlin
@Composable
fun StartedServiceDemo() {
    val context = LocalContext.current

    // One intent reused for both start/stop
    val serviceIntent = remember { Intent(packageContext = context, cls = MyStartedService::class.java) }

    Column(Modifier.fillMaxSize().padding(all = 16.dp)) {
        Button(onClick = {
            context.startService(p0 = serviceIntent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Start StartedService")
        }

        Spacer(Modifier.height(height = 16.dp))

        Button(onClick = {
            context.stopService(p0 = serviceIntent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Stop StartedService")
        }
    }
}
```

### Bound Service (not common in Compose-based app)

- Allows **components to bind** (e.g., Activity <-> Service communication)
- Clients interact with the service via an **IBinder interface**
- Service lives only as long as something is bound to it
- Useful when multiple components need to talk to the same long-running service
- Not about background execution - it's about **interaction** between components
- Example:
  - Music players (Activity controls playback)
  - Download manager (Activity queries progress)

#### Bound Service – MyBoundService.kt

```kotlin
class MyBoundService : Service() {

    // Binder given to clients
    private val binder = LocalBinder()

    // Coroutine scope for background work
    private val serviceScope = CoroutineScope(context = SupervisorJob() + Dispatchers.Default)

    inner class LocalBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(tag = "MyBoundService", msg = "onCreate() called")
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(tag = "MyBoundService", msg = "onBind() called")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(tag = "MyBoundService", msg = "onUnbind() called")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag = "MyBoundService", msg = "onDestroy() called — cleaning up work")
        serviceScope.cancel() // cancel all coroutines
    }

    // Example function exposed to clients
    fun startRepeatingTask() {
        serviceScope.launch {
            repeat(times = 5) { step ->
                delay(timeMillis = 2000)
                Log.d(tag = "MyBoundService", msg = "Working... step ${step + 1}")
            }
            Log.d(tag = "MyBoundService", msg = "Work finished in BoundService")
        }
    }

    fun getTimestamp(): String = System.currentTimeMillis().toString()
}
```

#### Bound Service – Set up Connection

```kotlin
class MainActivity : ComponentActivity() {
    private var isBound = false
    private val _boundService = mutableStateOf<MyBoundService?>(value = null)
    private val boundService: MyBoundService? get() = _boundService.value

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyBoundService.LocalBinder
            _boundService.value = binder.getService()
            isBound = true
            Log.d(tag = "BoundServiceDemo", msg = "Service connected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            _boundService.value = null
            isBound = false
            Log.d(tag = "BoundServiceDemo", msg = "Service disconnected")
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(packageContext = this, cls = MyBoundService::class.java).also { intent ->
            bindService(service = intent, conn = connection, flags = Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(conn = connection)
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BoundServiceDemo(boundService = _boundService) }
    }
}
```

#### Bound Service - Composable

```kotlin
@Composable
fun BoundServiceDemo(boundService: State<MyBoundService?>) {
    Column(Modifier.fillMaxSize().padding(all = 16.dp)) {
        Button(
            onClick = {
                boundService.value?.startRepeatingTask()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start Work in BoundService (check Logcat)")
        }

        Spacer(Modifier.height(height = 16.dp))

        Button(
            onClick = {
                boundService.value?.let {
                    val time = it.getTimestamp()
                    Log.d(tag = "BoundServiceDemo", msg = "Timestamp from service: $time")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Get Timestamp from BoundService")
        }
    }
}
```

## Use Foreground Services and WorkManager

### Foreground Service – MyForegroundService.kt

- Runs with a **persistent notification**
- System gives it **higher priority**
- Must show notification to user

```kotlin
class MyForegroundService : Service() {

    private val serviceScope = CoroutineScope(context = SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        Log.d(tag = "MyForegroundService", msg = "onCreate() called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag = "MyForegroundService", msg = "onStartCommand() called")

        // Build the required persistent notification
        val notification: Notification = NotificationCompat.Builder(context = this, channelId = CHANNEL_ID)
            .setContentTitle("Foreground Service Running")
            .setContentText("Performing background work…")
            .setSmallIcon(android.R.drawable.ic_menu_upload)
            .setOngoing(true) // makes it persistent
            .build()

        // Must call within 5s of starting service
        startForeground(id = NOTIF_ID, notification)

        // Simulate some background work
        serviceScope.launch {
            repeat(times = 5) { i ->
                delay(timeMillis = 2000)
                Log.d(tag = "MyForegroundService", msg = "Work step ${i + 1}")
            }
            Log.d(tag = "MyForegroundService", msg = "Work finished → stopSelf()")
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        Log.d(tag = "MyForegroundService", msg = "onDestroy() called — Service stopped")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                id = CHANNEL_ID,
                name = "Foreground Service Channel",
                importance = NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(name = Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "fgs_channel"
        private const val NOTIF_ID = 1001
    }
}
```

### Foreground Service – Composable

```kotlin
@Composable
fun ForegroundServiceDemo() {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        Log.d(tag = "Permission", msg = "POST_NOTIFICATIONS granted = $granted")
    }

    Column(Modifier.fillMaxSize().padding(all = 16.dp)) {
        Button(onClick = {
            if (Build.VERSION.SDK_INT >= 33) {
                launcher.launch(input = Manifest.permission.POST_NOTIFICATIONS)
            }
        }) {
            Text(text = "Request Notification Permission")
        }

        Spacer(Modifier.height(height = 16.dp))

        Button(
            onClick = {
                val intent = Intent(packageContext = context, cls = MyForegroundService::class.java)
                // Safe way to start FGS on API 26+
                ContextCompat.startForegroundService(context, intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start Foreground Service")
        }

        Spacer(Modifier.height(height = 16.dp))

        Button(
            onClick = {
                val intent = Intent(packageContext = context, cls = MyForegroundService::class.java)
                context.stopService(p0 = intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Stop Foreground Service")
        }
    }
}
```

### WorkManager vs Foreground Service

- **WorkManager**
  - For **deferrable**, scheduled background tasks
  - Good for syncing data, uploading logs, etc.
- **Foreground Service**
  - For **immediate, user-visible tasks**
  - Must show a **notification**
- Together:
  - WorkManager can run foreground work via `setForegroundAsync()`

## Lifecycle-Aware Components

### Lifecycle-Aware Components

- Android Jetpack's libraries
- Components that **automatically align** with Activity/Fragment lifecycle
- Common classes:
  - **ViewModel** (survives config changes)
  - **LiveData / StateFlow** (observable, lifecycle-aware)
  - **LifecycleOwner** (Activity/Fragment)
  - **LifecycleObserver** (listens to lifecycle events)
- Why Lifecycle-Aware Components?
  - Avoids memory leaks (no dangling observers)
  - UI updates only when visible
  - ViewModel reduces boilerplate for config changes
  - Fits into MVVM architecture (Model-View-ViewModel)

### LifecycleOwner and Observer

- `LifecycleOwner` -> anything with lifecycle (e.g., Activity)
- `LifecycleObserver` -> reacts to lifecycle changes

```kotlin
class MyObserver : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        Log.d(tag = "Lifecycle", msg = "Activity started")
    }
    override fun onStop(owner: LifecycleOwner) {
        Log.d(tag = "Lifecycle", msg = "Activity stopped")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(observer = MyObserver())
    }
}
```

### ViewModel & StateFlow

- **ViewModel**
  - Holds UI data that survives configuration changes
  - Scoped to Activity/Navigation destination
  - Exposes state to UI (Compose) in a lifecycle-safe way
- **StateFlow (or LiveData)**
  - Lifecycle-aware observable data holder
  - UI can **collectAsState()** in Compose for automatic updates
  - Preferred in Kotlin-first projects (LiveData still common in legacy apps)

```kotlin
class CounterVM : ViewModel() {
    private val _count = MutableStateFlow(value = 0)
    val count: StateFlow<Int> = _count
    fun increment() { _count.value++ }
}

@Composable
fun CounterScreen(vm: CounterVM = viewModel()) {
    val c by vm.count.collectAsState()
    Text(text = "Count = $c")
}
```
