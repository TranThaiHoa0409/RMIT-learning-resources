# MAD Week 11 — Testing Android Applications

**Môn học:** COSC2657/COSC2543/COSC2729 — Android Development
**Giảng viên:** Minh T. Vu

## Agenda
- Unit & Integration Testing in Android
- UI Testing with Compose Test
- Following MVVM + Clean Architecture

---

## 1. Why Testing Matters?

- Đảm bảo **reliability** → bắt bug trước khi release.
- Cải thiện **maintainability** → dễ refactor code hơn.
- Hỗ trợ **scalability** → linh hoạt & an toàn hơn khi thêm feature mới.
- Cho phép **automation & CI/CD** → mỗi commit đều có thể được verify dễ dàng.
- Giảm công sức QA thủ công.
- Tránh **technical debt**:
  - Không có test, developer bỏ qua validation → bug tích lũy dần.
  - Tăng chi phí cho các thay đổi trong tương lai.
  - Test đóng vai trò như **living documentation** cho hành vi hệ thống.

---

## 2. Types of Tests in Android

| Loại | Đặc điểm |
|---|---|
| **Unit Tests** | Chạy local (JVM); validate từng đoạn code nhỏ (VD: UseCase logic, ViewModel state); chạy nhanh |
| **Integration Tests** | Validate tương tác giữa các module (VD: ViewModel + Repository); có thể dùng fake hoặc mock dependency |
| **UI Tests** | Validate end-to-end user flow; chạy trên device/emulator; công cụ: **Espresso** (XML UI), **Compose Testing** (Compose UI) |

### The Testing Pyramid
```
        /\
       /  \   Top: UI Tests -> chỉ test các key user flow
      /----\
     /      \  Middle: Integration Tests
    /--------\
   /          \ Base: Unit Tests -> phần lớn test nằm ở đây
  /------------\
```
→ **Focus effort where tests are fastest & most valuable.**

---

## 3. Local Testing in Android (Unit & Integration)

### 3.1 Nguyên tắc
- **Testing on the Local JVM:** chạy local, không cần emulator/device thật → tốc độ tối đa.
- **Unit Testing (In Isolation):** tập trung test 1 class riêng lẻ (VD: ViewModel hoặc UseCase độc lập); dùng **Mocks** (qua Mockito) để stub dependency trực tiếp.
- **Local Integration Testing (Across Layers):** test tương tác giữa nhiều layer (VD: ViewModel + UseCase + Repository); dùng **Fakes** (VD: `FakeUserRepository`) để verify luồng data end-to-end.
- **Key Practices:**
  - Dùng `StandardTestDispatcher` và `runTest` để xử lý coroutines.
  - Advance time bằng `advanceUntilIdle()` để đảm bảo background job hoàn tất trước khi assert.

### 3.2 Testing Dependencies (build.gradle)

```kotlin
// Kotlin + Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

// JUnit for unit testing
testImplementation("junit:junit:4.13.2")

// Coroutines test library
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

// AndroidX Core testing (optional, for LiveData, InstantTaskExecutorRule)
testImplementation("androidx.arch.core:core-testing:2.2.0")

// Mocking framework (optional)
testImplementation("org.mockito:mockito-core:5.5.0")
testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")

// AndroidX Test (for instrumented/Compose tests)
androidTestImplementation("androidx.test.ext:junit:1.1.5")
androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

// Jetpack Compose testing
androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.0")
debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.0")
testImplementation(kotlin("test"))
```

### 3.3 Unit Testing Example

```kotlin
@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelUnitTest {

    private val testDispatcher = StandardTestDispatcher()

    // Mock the direct dependency
    private val mockLoginUseCase: LoginUseCase = mock()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        // Instantiate ViewModel with ONLY the mock
        viewModel = LoginViewModel(mockLoginUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun login_success_updatesStateToSuccess() = runTest {
        // Arrange: Define mock behavior in isolation
        whenever(methodCall = mockLoginUseCase.invoke(email = "user@email.com", password = "1234")).thenReturn(value = true)

        // Act: Trigger ViewModel action
        viewModel.login(email = "user@email.com", password = "1234")
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert: Verify ViewModel updated state correctly
        assertEquals(expected = LoginUiState.Success, actual = viewModel.uiState.value)
    }

    @Test
    fun login_failure_updatesStateToError() = runTest {
        // Arrange: Define mock failure behavior
        whenever(methodCall = mockLoginUseCase.invoke(email = "wrong@email.com", password = "wrongpass")).thenReturn(value = false)

        // Act
        viewModel.login(email = "wrong@email.com", password = "wrongpass")
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(expected = LoginUiState.Error, actual = viewModel.uiState.value)
    }
}
```

### 3.4 Local Integration Testing Example

```kotlin
@OptIn(ExperimentalCoroutinesApi::class)
class LoginIntegrationTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        // Integrate real UseCase with Fake Repository
        val fakeRepo = FakeUserRepository()
        val loginUseCase = LoginUseCase(userRepository = fakeRepo)

        // Pass real UseCase into ViewModel
        viewModel = LoginViewModel(loginUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun login_flowSuccess_updatesStateToSuccess() = runTest {
        // Act: Trigger login through ViewModel -> UseCase -> FakeRepo
        viewModel.login(email = "user@email.com", password = "1234")
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert: Verify end-to-end data flow across layers
        assertEquals(expected = LoginUiState.Success, actual = viewModel.uiState.value)
    }

    @Test
    fun login_flowFailure_updatesStateToError() = runTest {
        // Act
        viewModel.login(email = "wrong@email.com", password = "wrongpass")
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(expected = LoginUiState.Error, actual = viewModel.uiState.value)
    }
}
```

> **Điểm khác biệt chính:** Unit test dùng **mock** cho `LoginUseCase` (chỉ test riêng ViewModel), còn Integration test dùng **fake repository** thật kèm `LoginUseCase` thật (test cả luồng ViewModel → UseCase → Repository).

---

## 4. UI Testing in Jetpack Compose

- Compose có **framework test riêng**.
- API mang tính **declarative** (giống Compose).
- Key concepts:
  - `composeTestRule.setContent { }`
  - **Selectors:** `onNodeWithText()`, `onNodeWithTag()`
  - **Actions:** `.performClick()`, `.performTextInput()`
  - **Assertions:** `.assertIsDisplayed()`, `.assertTextEquals()`

### 4.1 UI Testing Example

```kotlin
@OptIn(ExperimentalCoroutinesApi::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(context = testDispatcher)

    @Test
    fun testLoginSuccessMessage() = testScope.runTest {
        val viewModel = LoginViewModel(
            LoginUseCase(userRepository = FakeUserRepository()),
            dispatcher = testDispatcher
        )

        composeTestRule.setContent {
            LoginScreen(viewModel)
        }

        // Enter valid credentials
        composeTestRule.onNodeWithTag(testTag = "emailField")
            .performTextInput(text = "user@email.com")

        composeTestRule.onNodeWithTag(testTag = "passwordField")
            .performTextInput(text = "1234")

        // Click login
        composeTestRule.onNodeWithTag(testTag = "loginButton").performClick()

        // Let coroutines finish
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify success
        composeTestRule.onNodeWithTag(testTag = "successMessage").assertIsDisplayed()
    }

    @Test
    fun testLoginFailureMessage() = testScope.runTest {
        val viewModel = LoginViewModel(
            LoginUseCase(userRepository = FakeUserRepository()),
            dispatcher = testDispatcher
        )

        composeTestRule.setContent {
            LoginScreen(viewModel)
        }

        // Enter invalid credentials
        composeTestRule.onNodeWithTag(testTag = "emailField")
            .performTextInput(text = "wrong@email.com")

        composeTestRule.onNodeWithTag(testTag = "passwordField")
            .performTextInput(text = "wrongpass")

        // Click login
        composeTestRule.onNodeWithTag(testTag = "loginButton").performClick()

        // Let coroutines finish
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify error
        composeTestRule.onNodeWithTag(testTag = "errorMessage").assertIsDisplayed()
    }
}
```

---

## 5. Testing in MVVM + Clean Architecture

| Layer | Loại test |
|---|---|
| **Entity / UseCase** | Unit tests |
| **Repository** | Unit hoặc integration tests (fakes) |
| **ViewModel** | State verification |
| **UI Layer** | Compose Tests |

**Rule:** Test **từng layer riêng lẻ** trước, sau đó mới test **integration**.

### Best Practices
- Ưu tiên **fakes over mocks** khi có thể.
- Giữ cấu trúc folder test **song song** với main code.
- Chạy test trong **CI/CD pipelines**.
- Viết test **sớm** → tránh technical debt.

---

## Tóm tắt các điểm chính
1. Testing giúp đảm bảo reliability, maintainability, scalability và giảm technical debt — hoạt động như living documentation.
2. Có 3 loại test chính: **Unit** (nhanh, cô lập), **Integration** (test tương tác giữa các layer), **UI** (test end-to-end user flow).
3. **Testing Pyramid**: tập trung nhiều nhất vào Unit tests, ít nhất ở UI tests (chỉ test key flows).
4. Unit test dùng **Mocks**, Integration test dùng **Fakes** để verify luồng data thật giữa các layer.
5. `StandardTestDispatcher` + `runTest` + `advanceUntilIdle()` là bộ công cụ chuẩn để test coroutines.
6. Compose có framework test riêng (`composeTestRule`) với API declarative: selectors, actions, assertions.
7. Trong kiến trúc MVVM + Clean Architecture: test từng layer riêng lẻ (Entity/UseCase → Unit, Repository → Unit/Integration, ViewModel → state verification, UI → Compose Tests), sau đó mới test tích hợp toàn bộ luồng.
