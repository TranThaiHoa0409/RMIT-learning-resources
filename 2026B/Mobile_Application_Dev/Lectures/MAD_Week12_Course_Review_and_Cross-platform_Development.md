# MAD Week 12 — Course Review & Cross-platform Development

**Môn học:** COSC2657/COSC2543/COSC2729 — Android Development
**Giảng viên:** Minh T. Vu

## Agenda
- Final Wrap-up
- What is React Native?
- React Native vs Native App Development
- React Native — How to create a project
- React Native — Android app

---

## Phần 1: Final Wrap-up (Course Review)

### 1.1 Course Journey — tổng hợp toàn bộ 11 tuần

| Tuần | Chủ đề |
|---|---|
| Week 1 | Mobile Ecosystem & Android Basics |
| Week 2 | UI Foundations (Views, Layouts, Compose, Activities) |
| Week 3 | State Management & Architecture (MVVM, Navigation, Clean Architecture) |
| Week 4 | Networking (Coroutines, Flow, Retrofit, REST APIs) |
| Week 5 | Location & Maps Integration |
| Week 6 | Data Persistence & Dependency Injection (Room, DataStore, Hilt, Firebase) |
| Week 8 | Background Processing & System Communication |
| Week 9 | Services & Lifecycle-aware Components |
| Week 10 | Hardware Integration & ML (Sensors, CameraX, ML Kit) |
| Week 11 | Testing (Unit, UI, MVVM Testing) |

### 1.2 Core Development Stack
- **Language:** Kotlin (concise, null-safety, coroutines)
- **UI Toolkit:** Jetpack Compose (modern declarative UI)
- **Architecture:** MVVM + Clean Architecture
- **Jetpack Libraries:** Navigation, Lifecycle, WorkManager, Hilt, DataStore, Accompanist
- **Tools:** Android Studio, Emulator, Gradle

### 1.3 Designing Android UIs
- Compose thay thế XML → dùng **Composable functions**.
- Layouts: `Column`, `Row`, `Box`.
- Modifiers → styling, padding, size, colors.
- Activity & Navigation → `Intent`, `NavGraph`, `NavController`.
- Nhất quán với **Material Design & Theming**.

### 1.4 State & Architecture
- **State management:** `remember`, `rememberSaveable`, `derivedStateOf`.
- **State hoisting:** giúp Composable tái sử dụng & dễ test.
- **MVVM** — tách biệt trách nhiệm (Separation of concerns):
  - **Model** → Data (Room, APIs)
  - **ViewModel** → Business Logic (Flows, Coroutines)
  - **View** → Compose UI

### 1.5 Networking & Data
- **Coroutines & Flow** → xử lý bất đồng bộ (async), reactive.
- **Retrofit** + JSON parsing (Moshi, Kotlinx, Gson).
- **Room DB + DataStore** → persistence.
- **Hilt** → Dependency Injection.
- **Firebase** (mở rộng) → sync, real-time DB, authentication.

### 1.6 Location & Background Tasks
- **Maps SDK** → markers, polylines, camera control.
- **Location Services** → lấy vị trí hiện tại, cập nhật theo interval.
- **Background tasks:**
  - `WorkManager` → scheduling đáng tin cậy, tác vụ one-shot ngắn.
  - `Broadcast Receivers` → sự kiện hệ thống & custom event.
  - `Services` (foreground, bound) → công việc chạy lâu.

### 1.7 Hardware & Machine Learning
- **Sensors** → accelerometer, gyroscope, proximity, ...
- **CameraX** → hiện đại, lifecycle-aware, dễ tích hợp.
- **ML Kit** → on-device ML (barcode scanning, text recognition, translation, ...).

### 1.8 Testing & Quality Assurance
- **Vì sao cần testing?** reliability, maintainability, CI/CD.
- **Unit tests** → UseCases, ViewModels.
- **UI tests** → Compose Testing API.
- **Testing pyramid** → tập trung vào unit & integration tests.

---

## Phần 2: React Native (Cross-platform Development)

### 2.1 React Native là gì?
- Framework để viết ứng dụng mobile **render tự nhiên (natively rendering)** chỉ dùng **JavaScript**.
- Dựa trên **React** — thư viện JavaScript của Facebook để xây UI — nhắm tới nền tảng mobile.
- Với React Native, app được xây dựng thật, **không thể phân biệt** với app viết bằng Objective-C hoặc Java.
- React Native dùng **cùng những UI block cơ bản** như app iOS/Android thông thường — chỉ là ghép các block đó lại bằng JavaScript và React.

### 2.2 Cách React Native hoạt động
- App React Native được viết bằng **hỗn hợp JavaScript và markup kiểu XML**.
- React Native gọi tới **native rendering APIs** trong Objective-C (cho iOS) hoặc Java (cho Android) → app render ra **UI component mobile thật**, không phải webview.
- Cũng expose **JavaScript interfaces** cho platform APIs → app React Native có thể truy cập tính năng của platform.
- Các app nổi bật dùng React Native: Facebook, Skype, Instagram, Tesla, AirBnb, SoundCloud, ...

### 2.3 So sánh: React Native vs Native App Development

| | React Native | Native Development |
|---|---|---|
| Phát triển | Cross-platform (viết 1 lần, dùng khắp nơi) | Platform-specific (viết 2 lần cho Android và iOS) |
| Performance | Đơn giản | Phức tạp |
| Chi phí phát triển | Thấp | Cao |
| Scalability | Tốt hơn | — |
| Cộng đồng | Lớn, nhiều component tái sử dụng | — |
| API/thư viện bên thứ 3 | — | Nhiều |
| Hỗ trợ native module | — | Nhiều hơn |
| Bảo mật | Kém an toàn hơn | UX/UI tốt hơn |

### 2.4 Khi nào nên chọn Native App Development?
- Phát triển ứng dụng phức tạp, đặc biệt là app Messenger.
- Có kế hoạch phát hành cập nhật thường xuyên.
- Tập trung nhiều vào trải nghiệm người dùng bản địa (native UX).
- App phụ thuộc vào tính năng phần cứng của thiết bị (VD: Utility app).
- App dựa trên IoT.
- App chỉ dành cho một nền tảng cụ thể.

### 2.5 Khi nào nên chọn React Native?
- Phát triển ứng dụng đơn giản và đồng nhất (uniform).
- Muốn ra mắt app trên nhiều nền tảng (cross-platform).
- Ngân sách phát triển thấp.
- App mạng xã hội (social media app).
- Có kế hoạch nhúng Facebook ads vào app.
- App thương mại điện tử (e-commerce).
- Công ty là start-up.

### 2.6 Cách tạo project React Native

**Yêu cầu phần mềm:** NodeJS và NPM (https://nodejs.org/en/download/), JDK 8, Expo, Yarn (tùy chọn).

```bash
# Install Yarn
npm install -g yarn

# Install Expo
npm install --global expo-cli

# Install React Native CLI
npm install -g react-native-cli

# Create project (chọn 1 trong 2)
npx create-expo-app@latest ProjectName        # template dựa trên Expo
react-native init ProjectName                 # plain React Native app

# Di chuyển vào project và chạy
cd ProjectName
```

### 2.7 Chạy dự án như app Android (chỉ trên Windows)

1. `cd` vào thư mục **android**, tạo file `local.properties` với nội dung:
   ```
   sdk.dir=C:\\Users\\Username\\AppData\\Local\\Android\\sdk
   ```
2. Chạy chương trình tại thư mục project: `npm start`
3. Mở Android emulator, dùng lệnh để start app.
4. Mở project trong IDE và build ứng dụng, bắt đầu từ file **App.js**.

---

## Tóm tắt các điểm chính
1. Week 12 tổng kết toàn bộ hành trình môn học: từ UI Foundations (Compose), State & Architecture (MVVM), Networking, Location, Data Persistence, Background Processing, Services, Hardware/ML, đến Testing.
2. Core stack xuyên suốt môn học: **Kotlin + Jetpack Compose + MVVM/Clean Architecture + Jetpack Libraries**.
3. React Native là framework cross-platform dùng JavaScript + React, render ra UI native thật (không phải webview).
4. Chọn **Native** khi cần hiệu năng cao, UX bản địa tốt, hoặc tích hợp phần cứng/IoT sâu.
5. Chọn **React Native** khi cần phát triển nhanh, chi phí thấp, đa nền tảng, phù hợp cho start-up hoặc app đơn giản/đồng nhất.
6. Quy trình tạo project React Native: cài Node/NPM → Yarn/Expo/React Native CLI → `create-expo-app` hoặc `react-native init` → cấu hình `local.properties` (Windows) → chạy bằng `npm start` + emulator.
