# MAD Tutorial 9 — Services and Lifecycle Components

**Môn học:** COSC2657/COSC2543/COSC2729 — Android Development

## Objectives
Trong tutorial này, xây dựng một app **simulated music player** thể hiện:
- Phát nhạc ở background dùng **Foreground Service**
- Điều khiển playback (Play/Pause/Stop) bằng **Bound Service**
- Quản lý UI state với **ViewModel + StateFlow**
- Dùng **media-style notification** có nút điều khiển playback
- Download một bài hát từ URL dùng **WorkManager**

---

## Part 1 — Setup Requirements

### Permissions cần thêm trong `AndroidManifest.xml`:
- `POST_NOTIFICATIONS`
- `FOREGROUND_SERVICE_MEDIA_PLAYBACK`
- `INTERNET`

### Khai báo service:
- `MusicPlayerService` với `foregroundServiceType="mediaPlayback"`

---

## Part 2 — MusicPlayerService Requirements

Tạo service `MusicPlayerService` có thể:
- Start như một **Foreground Service** với notification thường trực (persistent).
- Hoạt động như một **Bound Service** để các Activity/UI có thể gọi method.
- Duy trì trạng thái boolean `isPlaying`.

### Các method cần thêm:
| Method | Chức năng |
|---|---|
| `play()` | Bắt đầu vòng lặp playback giả lập |
| `pause()` | Tạm dừng playback |
| `stop()` | Dừng playback và kết thúc service |

### Yêu cầu khác:
- Service phải cập nhật nội dung notification (Playing vs Paused).
- Thêm **MediaStyle notification** với action play/pause.

---

## Part 3 — PlayerViewModel Requirements

Tạo `PlayerViewModel`:
- Giữ state cho biết nhạc đang phát hay không.
- Dùng `MutableStateFlow` và expose ra `StateFlow`.
- Có method `updateState(isPlaying: Boolean)`.

```kotlin
class PlayerViewModel : ViewModel() {
    private val _isPlaying = MutableStateFlow(value = false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    fun updateState(isPlaying: Boolean) {
        _isPlaying.value = isPlaying
    }
}
```

---

## Part 4 — MainActivity & UI Requirements

- **Bind** tới `MusicPlayerService` bằng `ServiceConnection`.
- **Start** service trong `onStart()` với `ContextCompat.startForegroundService()`.
- **Unbind** service trong `onStop()`.

### Yêu cầu UI:
- Hiển thị trạng thái playback (**Now Playing** / **Paused**).
- Các nút: **Play**, **Pause**, **Stop**.
- Mỗi nút:
  - Gọi method tương ứng của service (`play()`, `pause()`, `stop()`)
  - Cập nhật state của ViewModel

---

## Part 5 — Exercises (Bài tập)

1. Mở rộng service với method `nextTrack()` và `previousTrack()`, thêm nút UI tương ứng.
2. Nâng cấp notification với **MediaStyle actions** cho play/pause/stop.
3. Đồng bộ hành động của nút notification với UI state thông qua ViewModel.
4. Thêm tên bài hát (track name) vào ViewModel và hiển thị nó trên UI lẫn notification.
5. Thêm chức năng download bài hát từ URL dùng **WorkManager**, hiển thị progress trên notification.

---

## Gợi ý kiến trúc tổng thể

```
MainActivity
 ├── bind/unbind ── MusicPlayerService (Foreground + Bound)
 │                     ├── play() / pause() / stop()
 │                     ├── isPlaying: Boolean
 │                     └── cập nhật MediaStyle notification
 └── observe ──────── PlayerViewModel
                          └── StateFlow<Boolean> isPlaying
```

**Luồng dữ liệu:**
1. Người dùng bấm nút Play trên UI → gọi `service.play()` → đồng thời gọi `viewModel.updateState(true)`.
2. Service cập nhật notification (icon pause, text "Now Playing").
3. UI observe `viewModel.isPlaying` (qua `collectAsState()`) → tự động re-compose hiển thị đúng trạng thái.
4. Nếu người dùng bấm nút Pause/Stop trực tiếp trên notification (MediaStyle actions) → cần đồng bộ ngược lại vào ViewModel (Exercise 3).

## Liên hệ với lý thuyết Week 9
- Đây là ví dụ thực tế kết hợp **Bound Service** (điều khiển playback) và **Foreground Service** (chạy nền có notification) trong cùng một service.
- `PlayerViewModel` + `StateFlow` minh họa **Lifecycle-Aware Components** đã học ở Week 9.
- Phần download qua `WorkManager` (Exercise 5) minh họa use case "deferrable, scheduled background task" khác với việc phát nhạc (task cần chạy ngay và hiển thị cho người dùng).
