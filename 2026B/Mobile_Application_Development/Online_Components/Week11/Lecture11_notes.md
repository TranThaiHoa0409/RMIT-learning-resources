# Lecture11 — Login MVVM Demo (Notes)

## 1. Tổng quan

Đây là demo về kiến trúc MVVM + Use Case cho một màn hình Login đơn giản, có kèm bộ test khá đầy đủ (unit test, integration test, Compose UI test). Khác với Tutorial09/10 và Lecture10 (gần như single-file), project này **có tách lớp rõ ràng**: `UserRepository` (interface, domain/data boundary) → `LoginUseCase` (domain) → `LoginViewModel` (presentation, giữ `LoginUiState`) → `LoginScreen` (UI Compose).

Package: `com.example.lecture11`. `minSdk 24`, `targetSdk/compileSdk 36`.

**⚠️ Lưu ý quan trọng:** Project **không có file `MainActivity.kt`** trong `app/src/main/java/com/example/lecture11/` (chỉ có 6 file custom liệt kê bên dưới + folder `ui/theme`), trong khi `AndroidManifest.xml` vẫn khai báo `<activity android:name=".MainActivity">` làm launcher activity. Nghĩa là **project hiện tại sẽ không build/chạy được** — có thể do lúc copy/nộp bài bị thiếu file này, cần bạn kiểm tra lại (rất có thể `MainActivity.kt` bị sót khi copy từ máy khác, hoặc đã bị xoá nhầm).

## 2. Các file custom (theo lớp kiến trúc)

- **`UserRepository.kt`** — interface domain/data, khai báo `suspend fun login(email, password): Boolean`. Đây là điểm trừu tượng hoá nguồn dữ liệu login (chưa có implementation thật kết nối backend).

- **`FakeUserRepository.kt`** — implementation giả của `UserRepository`, dùng cho test/demo: chỉ chấp nhận đúng 1 cặp email/password hardcode (`user@email.com` / `1234`).

- **`LoginUseCase.kt`** — use case lớp domain, nhận `UserRepository` qua constructor (dependency injection thủ công), expose qua `operator fun invoke(email, password)` gọi xuống repository.

- **`LoginUiState.kt`** — `sealed class` đại diện trạng thái màn hình Login: `Idle`, `Loading`, `Success`, `Error`.

- **`LoginViewModel.kt`** — `ViewModel` nhận `LoginUseCase` (và `CoroutineDispatcher` optional, mặc định `Dispatchers.Main` — cho phép inject dispatcher test được). Giữ `MutableStateFlow<LoginUiState>`, hàm `login()` chạy trong `viewModelScope`: set `Loading` → gọi use case → set `Success`/`Error` theo kết quả.

- **`LoginScreen.kt`** — Composable nhận `LoginViewModel` làm tham số (không tự khởi tạo — dễ test/preview). Có 2 `OutlinedTextField` (email, password, mỗi ô có `testTag` riêng để UI test) + nút Login (gọi `viewModel.login()`) + hiển thị message theo `uiState` (Success/Error/Loading), mỗi trạng thái cũng có `testTag` riêng.

- **`ui/theme/Color.kt`, `Theme.kt`, `Type.kt`** — mặc định AS Compose template, chưa sửa (giống các project trước).

## 3. Bộ test custom (không phải boilerplate AS)

- **`app/src/test/.../LoginViewModelUnitTest.kt`** *(unit test)*: mock `LoginUseCase` bằng Mockito-Kotlin, test 2 case (login đúng → `Success`, login sai → `Error`), dùng `StandardTestDispatcher` + `runTest` để test coroutine.
- **`app/src/test/.../LoginIntegrationTest.kt`** *(integration test)*: không mock — dùng `FakeUserRepository` thật + `LoginUseCase` thật, chỉ thay dispatcher, kiểm tra luồng end-to-end qua các lớp.
- **`app/src/androidTest/.../LoginScreenTest.kt`** *(Compose UI test)*: dùng `createAndroidComposeRule`, nhập liệu qua `testTag` (`emailField`, `passwordField`, `loginButton`), verify message hiển thị đúng (`successMessage`/`errorMessage`) cho cả 2 case đúng/sai.
- File mặc định AS vẫn còn: `test/.../ExampleUnitTest.kt`, `androidTest/.../ExampleInstrumentedTest.kt` — không liên quan tới bài, không cần note.

## 4. `AndroidManifest.xml`

Không có gì khác thường ngoài phần khai báo `.MainActivity` mặc định (xem lưu ý ở mục 1 — file này không tồn tại trong source). Không có permission nào được thêm.

## 5. Dependency thêm ngoài mặc định

So với template Compose "Empty Activity" chuẩn:

**`app/build.gradle.kts`:**

```kotlin
// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

// Unit test
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
testImplementation("androidx.arch.core:core-testing:2.2.0")
testImplementation("org.mockito:mockito-core:5.5.0")
testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
testImplementation(kotlin("test"))
```

Ghi chú thêm trong file gốc: comment "CRITICAL FIX: Forces modern Espresso version to resolve the InputManager exception" ở dòng `androidTestImplementation(libs.androidx.espresso.core)` — cho thấy version Espresso trong `libs.versions.toml` đã được nâng cấp thủ công để fix lỗi (xem bên dưới).

**`gradle/libs.versions.toml`** — so với mặc định, các version đã được nâng cấp/đổi khác:

```toml
androidxJunit = "1.2.1"        # tên khác + version khác so với mặc định (junitVersion = "1.3.0" ở 2 project trước)
espressoCore = "3.6.1"         # nâng cấp thủ công (comment "CRITICAL FIX" trong build.gradle.kts)
composeBom = "2024.12.01"      # nâng cấp so với mặc định (kèm comment nhắc nâng lên 2025.x hoặc 2024.12+)
```

Không có entry `[libraries]` mới nào thêm (chỉ thay đổi version của các lib đã có sẵn).
