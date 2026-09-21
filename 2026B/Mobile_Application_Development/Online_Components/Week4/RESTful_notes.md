# RESTful — Android Studio Project Notes

Project: `RESTful` (Compose app quản lý Student qua REST API, kiến trúc MVVM với 3 lớp data / presentation).

## 1. Cấu trúc custom cần copy

Toàn bộ nằm trong `app/src/main/java/com/example/restful/`:

```
restful/
├── MainActivity.kt
├── data/
│   ├── model/Student.kt
│   ├── network/StudentApi.kt
│   └── repository/ApiClient.kt
├── presentation/
│   ├── navigation/StudentApp.kt
│   ├── ui/
│   │   ├── StudentListScreen.kt
│   │   ├── AddStudentScreen.kt
│   │   └── EditStudentScreen.kt
│   └── viewmodel/StudentViewModel.kt
└── ui/theme/ (Color.kt, Theme.kt, Type.kt — mặc định AS, chưa chỉnh, copy theo cho đủ nhưng không có gì custom)
```

## 2. Vai trò từng file

| File | Vai trò |
|---|---|
| `MainActivity.kt` | Entry point, gọi `MaterialTheme { StudentApp() }`. Lưu ý: dùng `MaterialTheme` mặc định của material3, không dùng `RESTfulTheme` custom định nghĩa trong `ui/theme/Theme.kt` — theme riêng hiện chưa được áp dụng thực tế. |
| `data/model/Student.kt` | 2 data class: `Student(id, name)` và `NewStudent(name)` — tách riêng model cho POST (không có id). |
| `data/network/StudentApi.kt` | Interface Retrofit định nghĩa 4 endpoint: `GET /students`, `POST /students`, `DELETE /students/{id}`, `PUT /students/{id}`. |
| `data/repository/ApiClient.kt` | Singleton Retrofit (`object ApiClient`), baseUrl `http://10.0.2.2:3004/`, dùng kotlinx.serialization làm converter. |
| `presentation/navigation/StudentApp.kt` | `NavHost` với 3 route: `studentList` → `addStudent` → `editStudent/{id}`, dùng chung 1 `StudentViewModel`. |
| `presentation/viewmodel/StudentViewModel.kt` | `StateFlow<List<Student>>` (danh sách) + `SharedFlow<String>` (message/snackbar); 4 hàm: `getStudents`, `addStudent`, `deleteStudent`, `updateStudent`, đều có try/catch riêng. |
| `presentation/ui/StudentListScreen.kt` | `Scaffold` + `LazyColumn` liệt kê sinh viên, FAB thêm mới, click item để sửa, icon xóa; lắng nghe `viewModel.message` để hiện Snackbar. |
| `presentation/ui/AddStudentScreen.kt` | Form 1 `TextField` (name) + nút Submit gọi `viewModel.addStudent`. |
| `presentation/ui/EditStudentScreen.kt` | Nhận `studentId`, tự tìm student trong list qua `LaunchedEffect`, form sửa tên + nút Update. |

## 3. File mặc định (đã kiểm tra, không cần note gì thêm)

`.gradle/`, `.idea/`, `.kotlin/`, `build/`, `gradlew*`, `local.properties`, `res/mipmap-*`, `res/drawable/ic_launcher_*`, `res/xml/backup_rules.xml`, `res/xml/data_extraction_rules.xml`, `res/values/colors.xml`, `res/values/strings.xml`, `res/values/themes.xml`, `ui/theme/Color.kt`, `ui/theme/Theme.kt`, `ui/theme/Type.kt`, `settings.gradle.kts`, `build.gradle.kts` (gốc), `ExampleInstrumentedTest.kt`, `ExampleUnitTest.kt`.

## 4. Cần thêm lại thủ công ở project mới (không nằm trong folder `restful/`)

### AndroidManifest.xml

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

Và trong thẻ `<application>`:

```xml
android:usesCleartextTraffic="true"
```

Thiếu 2 dòng này thì gọi API sẽ fail ngay (không có quyền internet / bị chặn HTTP không mã hóa).

### `app/build.gradle.kts`

```kotlin
// plugin
alias(libs.plugins.kotlin.serialization)

// dependencies
implementation(libs.retrofit)
implementation(libs.retrofit2.kotlinx.serialization.converter)
implementation(libs.kotlinx.serialization.json)
implementation(libs.kotlinx.coroutines.android)
implementation("androidx.navigation:navigation-compose:2.9.2")
implementation(libs.androidx.lifecycle.viewmodel.compose)
```

### `gradle/libs.versions.toml`

```toml
[versions]
# thêm nếu chưa có version tương ứng ở project mới

[libraries]
kotlinx-coroutines-android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2"
kotlinx-serialization-json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0"
retrofit = "com.squareup.retrofit2:retrofit:3.0.0"
retrofit2-kotlinx-serialization-converter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0"

[plugins]
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version = "2.2.0" }
```

Thiếu phần dependency/plugin này thì project mới sẽ không build được (unresolved reference `retrofit2`, `kotlinx.serialization`, `NavHost`...).

## 5. Lưu ý khác

- **Package name:** namespace gốc là `com.example.restful`. Nếu project đích có package name khác, phải sửa lại dòng `package ...` ở đầu mỗi file (và cấu trúc thư mục tương ứng) cho khớp, nếu không Android Studio sẽ báo lỗi resolve.
- **baseUrl `10.0.2.2:3004`** trong `ApiClient.kt` là địa chỉ đặc biệt trỏ về `localhost` của máy host khi chạy trên Android emulator. Nếu chạy trên thiết bị thật hoặc backend ở địa chỉ khác, phải đổi lại IP/port cho đúng.
