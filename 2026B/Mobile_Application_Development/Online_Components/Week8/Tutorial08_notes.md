# Tutorial08 — Project Notes

Ứng dụng nhỏ minh hoạ 3 cơ chế background xử lý sự kiện trong Android: **Broadcast Receiver** (2 loại — runtime-registered và manifest-declared), **WorkManager** (background job định kỳ), và **Notification**. Mọi event (do người dùng bấm nút hoặc hệ thống phát ra) đều được ghi vào một file log CSV cục bộ và hiển thị lại trên màn hình chính.

## 1. Tổng quan kiến trúc

```
MainActivity (presentation)
   │  onCreate(): ensureChannel() → request permission → registerReceiver() → enqueue WorkManager
   ▼
AppScreen (Composable, cùng file MainActivity.kt)
   │  nút "I'm Happy" / "I'm Sad" → sendBroadcast(ACTION_HAPPY / ACTION_MISS)
   ▼
CustomEventReceiver (runtime-registered)          SystemEventReceiver (manifest-declared)
   │  nhận custom broadcast từ app                    │  nhận broadcast hệ thống BATTERY_LOW
   ▼                                                   ▼
        EventLogRepository.append(Event)  ──▶  event_log.csv (external files dir)
                    │
                    ▼
              Notify.show()  ──▶  Notification hệ thống

SummaryWorker (WorkManager, chạy mỗi 15 phút)
   │  đọc EventLogRepository.recentCount(1 giờ gần nhất)
   ▼
   Notify.show() nếu có event mới
```

`AppScreen` đọc lại toàn bộ log qua `EventLogRepository.readAll()` mỗi khi bấm "Refresh" để hiển thị `LazyColumn`.

## 2. Domain

| File | Vai trò |
|---|---|
| `domain/model/Event.kt` | Data class đơn giản: `timestamp`, `source` (custom/system), `type` (HAPPY/SAD/BATTERY_LOW), `message`. |

## 3. Data

| File | Vai trò |
|---|---|
| `data/repository/EventLogRepository.kt` | Ghi/đọc log dạng CSV (1 dòng/event, phân cách bằng dấu phẩy) vào `context.getExternalFilesDir(null)/event_log.csv` — dùng scoped storage nên không cần xin quyền đọc/ghi. Có `append()`, `readAll()` (parse CSV, sort theo timestamp giảm dần), và `recentCount(sinceMillis)` để đếm event gần đây (dùng bởi `SummaryWorker`). |

## 4. Core — Notification

| File | Vai trò |
|---|---|
| `core/notification/Notify.kt` | Helper tập trung logic notification: `ensureChannel()` tạo `NotificationChannel` (chỉ cần trên Android O+), `show()` build và post 1 `NotificationCompat` với `PendingIntent` mở lại `MainActivity` khi bấm vào. |

## 5. Core — Broadcast Receiver (2 cách đăng ký khác nhau)

| File | Vai trò |
|---|---|
| `core/receiver/CustomEventReceiver.kt` | Đăng ký **runtime** (trong code, tại `MainActivity.onCreate()` qua `ContextCompat.registerReceiver(..., RECEIVER_NOT_EXPORTED)`), chỉ sống trong vòng đời Activity, phải `unregisterReceiver()` ở `onDestroy()`. Nhận 2 custom action tự định nghĩa (`ACTION_HAPPY`, `ACTION_MISS`) do chính app gửi qua `sendBroadcast()`. Khi nhận được: ghi `Event` vào repository + hiển thị notification. |
| `core/receiver/SystemEventReceiver.kt` | Đăng ký **tĩnh trong Manifest** (`<receiver>`), có thể đánh thức app kể cả khi app không chạy. Lắng nghe implicit broadcast hệ thống `Intent.ACTION_BATTERY_LOW` (một trong số ít broadcast hệ thống vẫn được phép khai báo tĩnh trên Android hiện đại). Khi nhận được: ghi `Event` + hiển thị notification, tương tự `CustomEventReceiver`. |

Cả 2 receiver đều dùng chung `EventLogRepository` và `Notify` — đây là điểm minh hoạ chính của bài: so sánh runtime vs manifest-declared receiver trên cùng một pattern xử lý.

## 6. Core — WorkManager

| File | Vai trò |
|---|---|
| `core/worker/SummaryWorker.kt` | `Worker` chạy nền định kỳ (được `MainActivity` enqueue mỗi 15 phút, ràng buộc `setRequiresBatteryNotLow(true)`). Trong `doWork()`: đếm số event trong 1 giờ gần nhất qua `repo.recentCount()`, nếu > 0 thì gửi notification tổng hợp. Minh hoạ deferred work vẫn chạy được khi app ở background. |

## 7. Presentation

| File | Vai trò |
|---|---|
| `presentation/MainActivity.kt` | **Đã di chuyển vào package con `presentation`** (khác vị trí mặc định là package gốc). Trong `onCreate()`: (1) gọi `Notify.ensureChannel()`, (2) xin quyền `POST_NOTIFICATIONS` runtime trên Android 13+, (3) đăng ký `CustomEventReceiver`, (4) lên lịch `SummaryWorker` bằng `PeriodicWorkRequestBuilder` (15 phút, `ExistingPeriodicWorkPolicy.UPDATE` để tránh lặp task khi Activity được tạo lại). `onDestroy()` gọi `unregisterReceiver()`. Cùng file còn có 2 composable: `AppScreen` (3 nút Happy/Sad/Refresh + `LazyColumn` hiển thị log) và `EventRow` (hiển thị 1 dòng event, convert timestamp sang `LocalDateTime`). **Lưu ý:** `setContent { AppScreen() }` gọi thẳng `AppScreen()`, không bọc qua composable `Tutorial08Theme` có sẵn trong `ui/theme/Theme.kt` — theme mặc định (Purple/Pink color scheme) không thực sự được áp dụng, UI chạy theo theme hệ thống mặc định của `MaterialTheme` gốc. |

## 8. AndroidManifest — phần custom thêm

So với manifest mặc định, project đã thêm:

- `<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>`.
- Khối `<receiver android:name=".core.receiver.SystemEventReceiver" android:exported="true">` với `<intent-filter><action android:name="android.intent.action.BATTERY_LOW"/></intent-filter>` — đăng ký tĩnh cho `SystemEventReceiver`. `CustomEventReceiver` **không** xuất hiện trong Manifest vì được đăng ký runtime trong code.
- Activity name đổi từ mặc định `.MainActivity` thành `.presentation.MainActivity`, khớp với việc file bị di chuyển vào subpackage.

## 9. Dependency thêm ngoài mặc định

Template Compose mặc định chỉ có: `core-ktx`, `lifecycle-runtime-ktx`, `activity-compose`, `compose-bom`, `ui`, `ui-graphics`, `ui-tooling(-preview)`, `material3`, `junit`, `androidx-junit`, `espresso-core`, `ui-test-junit4`, `ui-test-manifest`.

Ngoài các dòng đó, project thêm trực tiếp trong `app/build.gradle.kts` (không qua `libs.versions.toml`):

```kotlin
// WorkManager for background tasks
implementation("androidx.work:work-runtime-ktx:2.9.0")

// Core KTX (notifications helper depends on compat libs)
implementation("androidx.core:core-ktx:1.13.1")
implementation("androidx.core:core-splashscreen:1.0.1")
```

Ghi chú:
- `androidx.core:core-ktx:1.13.1` khai lại một dependency đã có sẵn trong version catalog (`libs.androidx.core.ktx`, bản `1.17.0`) — 2 version khác nhau của cùng 1 thư viện cùng tồn tại trong file gradle (Gradle sẽ tự chọn bản cao hơn khi resolve, nhưng đây là khai trùng không cần thiết, không phải lỗi chức năng).
- `androidx.core:core-splashscreen` được thêm nhưng chưa thấy code nào gọi `installSplashScreen()` — dependency có mặt nhưng chưa thực sự được dùng.
