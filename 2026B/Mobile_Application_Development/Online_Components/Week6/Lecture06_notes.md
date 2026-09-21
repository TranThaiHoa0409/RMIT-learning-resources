# Lecture06 — Project Notes

Ứng dụng Task list đơn giản minh hoạ 3 thành phần Jetpack tách biệt hoạt động cùng nhau: **Room** (lưu trữ local), **Hilt** (dependency injection), **DataStore Preferences** (lưu setting nhỏ — dark mode), cùng Jetpack Compose cho UI. Project KHÔNG theo kiến trúc nhiều lớp data/domain/presentation như các bài trước — mọi class nằm chung ở package gốc `com.example.lecture06`.

## 1. Tổng quan kiến trúc

```
MyApp (@HiltAndroidApp)
   │
   ▼
MainActivity (@AndroidEntryPoint)
   │  setContent { MaterialTheme(...) { TaskListScreen(...) } }
   ▼
TaskListScreen (Composable UI)
   │  viewModel()
   ▼
TaskViewModel (@HiltViewModel, inject TaskDao)
   │
   ▼
TaskDao ──▶ AppDatabase (Room) ──▶ Task (Entity)

DatabaseModule (@Module) cung cấp AppDatabase + TaskDao cho Hilt graph.

DarkModePreferenceScreen ──▶ PreferencesManager ──▶ DataStore (Context.dataStore)
```

Hilt tự dựng dependency graph tại compile-time (qua kapt annotation processing): `MyApp` bật Hilt cho toàn app, `MainActivity` là entry point Android được Hilt inject, `TaskViewModel` được Hilt cung cấp `TaskDao` (lấy từ `DatabaseModule`) mà không cần tự khởi tạo thủ công như factory pattern ở Tutorial05.

## 2. Room layer — lưu trữ local

| File | Vai trò |
|---|---|
| `Task.kt` | Entity Room (`@Entity(tableName = "tasks")`), 3 field: `id` (PK auto-generate), `title`, `completed`. |
| `TaskDao.kt` | Interface `@Dao` khai báo 5 query: lấy tất cả task, lấy task theo id, insert (conflict REPLACE), update, delete theo id — toàn bộ đều `suspend fun`. |
| `AppDatabase.kt` | `RoomDatabase` abstract class, khai báo entity `Task`, expose `taskDao()`. Có singleton pattern thủ công (`companion object` + `@Volatile INSTANCE`) qua `getDatabase(context)` — tuy nhiên cách này song song tồn tại với `DatabaseModule` (Hilt cũng tự tạo instance riêng), nên trong thực tế `getDatabase()` không được dùng, Hilt cung cấp instance qua `DatabaseModule` là nguồn thật sự được inject. |
| `DatabaseModule.kt` | Hilt `@Module @InstallIn(SingletonComponent::class)` — 2 hàm `@Provides @Singleton`: tạo `AppDatabase` (Room builder) và lấy `TaskDao` từ đó. Đây là nơi Hilt biết cách cung cấp 2 dependency này cho `TaskViewModel`. |

## 3. DataStore layer — lưu setting

| File | Vai trò |
|---|---|
| `PreferencesManager.kt` | Wrapper cho Jetpack DataStore Preferences: khai báo `Context.dataStore` (delegate `preferencesDataStore("settings")`), 1 key boolean `dark_mode`, và 2 hàm `saveDarkMode()` / `getDarkMode()` (trả `Flow<Boolean>`). |

## 4. Presentation — Compose UI + ViewModel

| File | Vai trò |
|---|---|
| `TaskViewModel.kt` | `@HiltViewModel`, constructor inject `TaskDao`. Giữ state `tasks` (`mutableStateListOf`). Có `refreshTasks()`, `addTask()`, `updateTask()`, `deleteTask()` — tất cả chạy trong `viewModelScope.launch` rồi gọi lại `refreshTasks()` để đồng bộ UI với DB. |
| `TaskListScreen.kt` | Composable chính: lấy `TaskViewModel` qua `viewModel()`, hiển thị `DarkModePreferenceScreen`, nút "Add Task", và `LazyColumn` liệt kê task kèm nút xoá. **Lưu ý:** file này không có dòng `package` ở đầu — nằm ở default package, khác với toàn bộ file còn lại (đều khai báo `package com.example.lecture06`); `MainActivity.kt` phải `import TaskListScreen` (không có prefix package) để dùng được. |
| `DarkModePreferenceScreen.kt` | Composable đọc `PreferencesManager.getDarkMode()` qua `collectAsState`, hiển thị `Switch` để bật/tắt, ghi lại qua `PreferencesManager.saveDarkMode()` trong `coroutineScope.launch`. |
| `MainActivity.kt` | Đánh dấu `@AndroidEntryPoint` để Hilt inject. Trong `setContent`, tự đọc `isDarkMode` từ `PreferencesManager` rồi dựng `MaterialTheme(colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme())` trực tiếp — **không** gọi composable `Lecture06Theme` có sẵn trong `ui/theme/Theme.kt` (theme mặc định bị bỏ qua, dùng `darkColorScheme()`/`lightColorScheme()` mặc định của Material3 thay vì bộ màu Purple/Pink đã khai trong `Color.kt`). |
| `MyApp.kt` | `Application` class, đánh dấu `@HiltAndroidApp` — bắt buộc phải có để khởi tạo Hilt component graph cho toàn app. |

## 5. Gradle / plugin setup — phần thêm cho Hilt

**Root `build.gradle.kts`** — thêm dòng khai báo plugin Hilt (`apply false` ở cấp root):

```kotlin
id("com.google.dagger.hilt.android") version "2.50" apply false
```

**`app/build.gradle.kts`** — thêm 2 plugin trong khối `plugins { }`:

```kotlin
id("kotlin-kapt")
id("com.google.dagger.hilt.android")
```

## 6. AndroidManifest — phần custom thêm

So với manifest mặc định, project thêm attribute `android:name=".MyApp"` trên thẻ `<application>` — trỏ tới `Application` class custom để Hilt có thể khởi tạo component graph khi app start. Không có `<uses-permission>` nào được thêm (app không gọi network, chỉ dùng Room local + DataStore local).

## 7. Dependency thêm ngoài mặc định

Giống Tutorial05, template Compose mặc định chỉ có: `core-ktx`, `lifecycle-runtime-ktx`, `activity-compose`, `compose-bom`, `ui`, `ui-graphics`, `ui-tooling(-preview)`, `material3`, `junit`, `androidx-junit`, `espresso-core`, `ui-test-junit4`, `ui-test-manifest`.

**Khác với Tutorial05:** lần này KHÔNG có dependency nào được thêm vào `gradle/libs.versions.toml` — toàn bộ khai trực tiếp dạng string literal trong `app/build.gradle.kts`:

```kotlin
implementation("androidx.room:room-runtime:2.6.1")

// Room Kotlin Extensions and Coroutines support
implementation("androidx.room:room-ktx:2.6.1")

// Room Compiler (for annotation processing)
kapt("androidx.room:room-compiler:2.6.1")

// DataStore Preferences
implementation("androidx.datastore:datastore-preferences:1.1.0")

// Hilt
implementation("com.google.dagger:hilt-android:2.50")
kapt("com.google.dagger:hilt-compiler:2.50")

// Jetpack Hilt Navigation for Compose
implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
```

Ghi chú: `hilt-navigation-compose` được thêm vào nhưng trong code hiện tại `TaskListScreen.kt` lấy ViewModel bằng `viewModel()` (từ `androidx.lifecycle.viewmodel.compose`) chứ không dùng `hiltViewModel()` (hàm mà thư viện này cung cấp) — dependency có mặt nhưng chưa thực sự được dùng tới trong code.
