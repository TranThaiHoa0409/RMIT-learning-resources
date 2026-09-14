# MAD Week 9 — Services and Lifecycle-Aware Components

**Môn học:** COSC2657/COSC2543/COSC2729 — Android Development
**Giảng viên:** Minh T. Vu

## Agenda
- Introduction to Service
- Use Foreground Services and WorkManager
- Introduction to Lifecycle-aware components

---

## 1. Introduction to Services

### 1.1 Service là gì?
- Một **Service** là component hỗ trợ thực hiện **long-running operations** (tác vụ chạy lâu) ở background.
- Khác với Activity:
  - **Không có UI**
  - Vẫn chạy được kể cả khi người dùng chuyển app khác hoặc app bị kill.

### 1.2 Các loại Service
| Loại | Mô tả |
|---|---|
| **Started Service** | Chạy cho tới khi bị stop |
| **Bound Service** | Cho phép các component khác (Activity/Fragment) bind vào và tương tác |
| **Foreground Service** | Chạy kèm notification hiển thị cho người dùng (ưu tiên cao hơn) |

### 1.3 Service Lifecycle
- `onCreate()` → gọi khi service được tạo
- `onStartCommand()` → gọi khi service được start bằng `startService()`
- `onBind()` → trả về binder cho bound service
- `onDestroy()` → gọi khi service bị stop/destroy

Hai luồng lifecycle khác nhau:
- **Unbounded service:** `startService()` → `onCreate()` → `onStartCommand()` → *Service running* → `onDestroy()` → *Service shut down*
- **Bounded service:** `bindService()` → `onCreate()` → `onBind()` → *Clients are bound to service* → (unbind) → `onUnbind()` → `onDestroy()` → *Service shut down*

---

## 2. Started Service (legacy)

### 2.1 Đặc điểm
- Được khởi động bằng `startService(Intent)` hoặc `ContextCompat.startForegroundService(Intent)`.
- Chạy **độc lập** với component đã khởi động nó.
- Phải tự dừng bằng `stopSelf()` hoặc bị dừng bởi `stopService()`.
- **Lưu ý:** từ Android 8.0+, có background limits → hệ thống hay kill service loại này → nên dùng **Foreground Service** (cho task hiển thị cho người dùng) hoặc **WorkManager** (cho deferred work).

### 2.2 Code mẫu

```kotlin
class MyStartedService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MyStartedService", "onCreate() called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyStartedService", "onStartCommand() called")

        // Simulate background work
        Thread {
            for (i in 1..5) {
                Thread.sleep(2000)
                Log.d("MyStartedService", "Working... step $i")
            }
            Log.d("MyStartedService", "Work finished -> calling stopSelf()")
            stopSelf() // Explicitly stop service after work is done
        }.start()

        // START_NOT_STICKY: don't recreate if killed
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyStartedService", "onDestroy() called — Service terminated")
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
```

```kotlin
@Composable
fun StartedServiceDemo() {
    val context = LocalContext.current

    // One intent reused for both start/stop
    val serviceIntent = remember { Intent(context, MyStartedService::class.java) }

    Column(Modifier.fillMaxSize().padding(all = 16.dp)) {
        Button(onClick = {
            context.startService(serviceIntent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Start StartedService")
        }

        Spacer(Modifier.height(height = 16.dp))

        Button(onClick = {
            context.stopService(serviceIntent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Stop StartedService")
        }
    }
}
```

---

## 3. Bound Service (ít dùng trong app dùng Compose)

### 3.1 Đặc điểm
- Cho phép các component **bind** vào (VD: giao tiếp Activity <-> Service).
- Client tương tác với service qua **IBinder interface**.
- Service chỉ sống khi còn có thứ bind vào nó.
- Hữu ích khi nhiều component cần nói chuyện với cùng một service chạy lâu.
- **Không phải là chạy background** — mà là về **tương tác giữa các component**.
- Ví dụ: Music player (Activity điều khiển playback), Download manager (Activity query progress).

### 3.2 Code mẫu — `MyBoundService.kt`

```kotlin
class MyBoundService : Service() {

    // Binder given to clients
    private val binder = LocalBinder()

    // Coroutine scope for background work
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    inner class LocalBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyBoundService", "onCreate() called")
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d("MyBoundService", "onBind() called")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("MyBoundService", "onUnbind() called")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyBoundService", "onDestroy() called — cleaning up work")
        serviceScope.cancel() // cancel all coroutines
    }

    // Example function exposed to clients
    fun startRepeatingTask() {
        serviceScope.launch {
            repeat(times = 5) { step ->
                delay(timeMillis = 2000)
                Log.d("MyBoundService", "Working... step ${step + 1}")
            }
            Log.d("MyBoundService", "Work finished in BoundService")
        }
    }

    fun getTimestamp(): String = System.currentTimeMillis().toString()
}
```

### 3.3 Thiết lập kết nối (trong Activity)

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
            Log.d("BoundServiceDemo", "Service connected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            _boundService.value = null
            isBound = false
            Log.d("BoundServiceDemo", "Service disconnected")
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

### 3.4 Composable UI

```kotlin
@Composable
fun BoundServiceDemo(boundService: State<MyBoundService?>) {
    Column(Modifier.fillMaxSize().padding(all = 16.dp)) {
        Button(
            onClick = { boundService.value?.startRepeatingTask() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start Work in BoundService (check Logcat)")
        }

        Spacer(Modifier.height(height = 16.dp))

        Button(
            onClick = {
                boundService.value?.let {
                    val time = it.getTimestamp()
                    Log.d("BoundServiceDemo", "Timestamp from service: $time")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Get Timestamp from BoundService")
        }
    }
}
```

---

## 4. Foreground Service

### 4.1 Đặc điểm
- Chạy kèm **persistent notification**.
- Hệ thống cho nó **higher priority** (ít bị kill hơn).
- Bắt buộc phải **hiển thị notification** cho người dùng.

### 4.2 Code mẫu — `MyForegroundService.kt`

```kotlin
class MyForegroundService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    companion object {
        private const val CHANNEL_ID = "fgs_channel"
        private const val NOTIF_ID = 1001
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        Log.d("MyForegroundService", "onCreate() called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyForegroundService", "onStartCommand() called")

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
                Log.d("MyForegroundService", "Work step ${i + 1}")
            }
            Log.d("MyForegroundService", "Work finished -> stopSelf()")
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        Log.d("MyForegroundService", "onDestroy() called — Service stopped")
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
}
```

### 4.3 Composable UI

```kotlin
@Composable
fun ForegroundServiceDemo() {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        Log.d("Permission", "POST_NOTIFICATIONS granted = $granted")
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
                context.stopService(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Stop Foreground Service")
        }
    }
}
```

---

## 5. WorkManager vs Foreground Service

| | WorkManager | Foreground Service |
|---|---|---|
| Dùng cho | Deferrable, scheduled background tasks | Immediate, user-visible tasks |
| Ví dụ | Syncing data, uploading logs | Music playback, tracking GPS |
| Yêu cầu | — | Bắt buộc show notification |

- **Together:** WorkManager có thể chạy foreground work qua `setForegroundAsync()`.

---

## 6. Lifecycle-Aware Components

### 6.1 Tổng quan
- Là các thư viện thuộc **Android Jetpack**.
- Component tự động **align** với lifecycle của Activity/Fragment.
- Các class phổ biến:
  - **ViewModel** — sống sót qua config changes
  - **LiveData / StateFlow** — observable, lifecycle-aware
  - **LifecycleOwner** — bất kỳ thứ gì có lifecycle (VD: Activity)
  - **LifecycleObserver** — lắng nghe các sự kiện lifecycle

### 6.2 Vì sao cần Lifecycle-Aware Components?
- Tránh memory leak (không còn observer "treo").
- UI chỉ update khi đang visible.
- ViewModel giảm boilerplate khi xử lý config changes.
- Phù hợp với kiến trúc MVVM (Model-View-ViewModel).

### 6.3 LifecycleOwner và LifecycleObserver

```kotlin
class MyObserver : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        Log.d("Lifecycle", "Activity started")
    }
    override fun onStop(owner: LifecycleOwner) {
        Log.d("Lifecycle", "Activity stopped")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(observer = MyObserver())
    }
}
```

### 6.4 ViewModel & StateFlow

- **ViewModel:**
  - Giữ UI data sống sót qua configuration changes.
  - Scoped tới Activity/Navigation destination.
  - Expose state cho UI (Compose) theo cách lifecycle-safe.
- **StateFlow (hoặc LiveData):**
  - Data holder có khả năng observable, lifecycle-aware.
  - UI có thể `collectAsState()` trong Compose để tự động update.
  - Ưu tiên dùng trong dự án Kotlin-first (LiveData vẫn phổ biến trong app legacy).

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

---

## Tóm tắt các điểm chính
1. Service dùng cho long-running work không cần UI, có 3 loại: Started, Bound, Foreground.
2. Started Service dễ bị hệ điều hành kill trên Android 8.0+ → nên chuyển sang Foreground Service hoặc WorkManager.
3. Bound Service dùng khi cần **tương tác 2 chiều** giữa Activity và Service (VD: music player).
4. Foreground Service bắt buộc phải có notification, ưu tiên cao hơn, phù hợp cho tác vụ người dùng cần thấy đang chạy (VD: phát nhạc, GPS tracking).
5. WorkManager phù hợp cho tác vụ có thể trì hoãn (deferrable), không cần chạy ngay lập tức.
6. Lifecycle-aware components (ViewModel, StateFlow/LiveData, LifecycleObserver) giúp tránh memory leak và giữ UI đồng bộ với lifecycle, là nền tảng của kiến trúc MVVM.
