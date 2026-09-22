# Tutorial09 — Music Player App (Notes)

## 1. Tổng quan

App demo một music player đơn giản kết hợp download nhạc qua `WorkManager` và điều khiển playback qua notification (foreground `Service`). Kiến trúc hiện tại **chưa theo mô hình MVVM/data-domain-presentation đầy đủ**: gần như là single-`Activity` (Compose UI) + `Service` (phát nhạc) + `Worker` (tải file), giao tiếp qua `Intent`/action string thay vì qua ViewModel hay use case layer.

Package: `com.example.tutorial09`. `minSdk 24`, `targetSdk/compileSdk 36`.

## 2. `MainActivity.kt`

Activity duy nhất của app, chứa toàn bộ Compose UI và logic bind Service.

- **Bind/start Service**: `onStart()` gọi `ContextCompat.startForegroundService()` rồi `bindService()` tới `MusicPlayerService` qua một `ServiceConnection` ẩn danh (lưu instance service vào `boundService` — state Compose). `onStop()` unbind nếu đang bound.
- **Xin quyền thông báo**: composable `RequestNotificationPermission()` — kiểm tra quyền `POST_NOTIFICATIONS` (chỉ cần từ SDK 33+), dùng `rememberLauncherForActivityResult` để request runtime nếu chưa có.
- **`enqueueDownload(context, url)`**: build `OneTimeWorkRequest` cho `DownloadWorker` với input data là URL, tag `"song_download"`, enqueue qua `WorkManager`.
- **`SongListScreen`**: liệt kê danh sách URL nhạc mẫu (hardcode 2 link SoundHelix), mỗi dòng có nút Download (gọi `enqueueDownload`) và nút Play (gửi `Intent` action `ACTION_PLAY` tới `MusicPlayerService` kèm `PATH`).
- **`getLocalSongs(context)`**: đọc danh sách file trong `filesDir/music` (tạo folder nếu chưa có).
- **`PlayerScreen`**: hiển thị danh sách bài đã tải về local; observe `WorkManager.getWorkInfosByTagLiveData("song_download")` qua `observeAsState` — khi có work nào `isFinished` thì refresh lại danh sách local songs. Nút Play mỗi bài gửi `Intent` action `ACTION_PLAY` kèm `PATH` + `PLAYLIST` (toàn bộ danh sách local, dùng cho next/prev).

## 3. `MusicPlayerService.kt`

Foreground `Service`, chịu trách nhiệm phát nhạc và hiển thị notification điều khiển.

- Dùng `MediaPlayer` để phát; state nội bộ: `isPlaying`, `currentPath`, `lastPosition`, `playlist`, `currentIndex`.
- `onStartCommand` xử lý theo `action` nhận từ Intent: `ACTION_PLAY` (phát mới hoặc resume nếu cùng path), `ACTION_PAUSE`, `ACTION_STOP` (dừng + trả `START_NOT_STICKY`), `ACTION_NEXT`, `ACTION_PREV` (điều hướng playlist theo `currentIndex`, có wrap-around).
- `updateNotification()`: build `NotificationCompat` với các `PendingIntent` gọi ngược lại chính Service (Play/Pause/Stop/Next/Prev), icon dùng resource hệ thống có sẵn (`android.R.drawable.ic_media_*`), nội dung hiển thị tên file đang phát.
- `createChannel()`: tạo `NotificationChannel` (`IMPORTANCE_LOW`) cho Android O+.
- Có `LocalBinder`/`onBind` để `MainActivity` bind trực tiếp lấy instance Service (song hiện tại `MainActivity` chỉ dùng `boundService` để lưu state, chưa gọi method nào của service qua binder — mọi điều khiển đều qua `Intent`).

## 4. `DownloadWorker.kt`

`Worker` (WorkManager), input là URL cần tải.

- Lấy `URL` từ `inputData`, tải file bằng `URL(url).openStream()` copy trực tiếp vào `filesDir/music/<tên file>` (đặt tên theo phần cuối URL).
- Bắn `NotificationCompat` báo "Download complete"/"Download failed" (tạo riêng `NotificationChannel` "download_channel", tách biệt với channel của `MusicPlayerService`).
- Trả `Result.success()`/`Result.failure()` — MainActivity theo dõi qua `WorkInfo` (tag `"song_download"`) để biết khi nào refresh danh sách local songs.

## 5. `PlayerViewModel.kt`

`ViewModel` rất đơn giản, chỉ giữ `MutableStateFlow<Boolean>` cho `isPlaying` và hàm `updateState()`.

**Lưu ý:** class này **không được reference ở bất kỳ đâu khác trong code** (`MainActivity` không tạo hay dùng `PlayerViewModel`). Có vẻ là phần scaffold/leftover chưa được tích hợp vào luồng thực tế — nên kiểm tra lại với đề bài tutorial xem có yêu cầu dùng ViewModel này không, hay đây là dấu vết còn sót từ bước làm trước.

## 6. `AndroidManifest.xml` (đã chỉnh so với mặc định)

- Thêm 3 permission: `POST_NOTIFICATIONS`, `FOREGROUND_SERVICE_MEDIA_PLAYBACK`, `INTERNET`.
- Thêm khai báo `<service android:name=".MusicPlayerService" android:exported="false" android:foregroundServiceType="mediaPlayback" />`.
- Phần còn lại (theme, icon, activity launcher) là mặc định AS sinh ra.

## 7. Dependency thêm ngoài mặc định

So với template Compose "Empty Activity" chuẩn của Android Studio, project có thêm các dependency sau (cần thêm lại nếu tạo project mới từ đầu):

**`app/build.gradle.kts`:**

```kotlin
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
implementation(libs.androidx.work.runtime.ktx)      // WorkManager — dùng cho DownloadWorker
implementation(libs.androidx.foundation)             // verticalScroll, Arrangement, ...
implementation(libs.androidx.runtime.livedata)       // observeAsState cho LiveData của WorkManager
implementation("androidx.core:core-ktx:1.12.0")      // trùng mục đích với libs.androidx.core.ktx (1.17.0) — có thể dư thừa, nên kiểm tra lại
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("androidx.media:media:1.6.0")         // cho MediaStyle notification
```

**`gradle/libs.versions.toml`** — version + library entries thêm ngoài mặc định:

```toml
[versions]
workRuntimeKtx = "2.10.3"
foundation = "1.9.0"
runtimeLivedata = "1.9.0"

[libraries]
androidx-work-runtime-ktx = { group = "androidx.work", name = "work-runtime-ktx", version.ref = "workRuntimeKtx" }
androidx-foundation = { group = "androidx.compose.foundation", name = "foundation", version.ref = "foundation" }
androidx-runtime-livedata = { group = "androidx.compose.runtime", name = "runtime-livedata", version.ref = "runtimeLivedata" }
```
