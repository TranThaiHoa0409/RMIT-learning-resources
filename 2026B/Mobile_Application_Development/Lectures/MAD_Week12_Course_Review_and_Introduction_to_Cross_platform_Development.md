# Course Review and Introduction to Cross-platform Development

## Week Agenda

- Final Wrap-up
- What is React Native?
- React Native vs Native App Development
- React Native – How to create a project
- React Native – Android app

## Final Wrap-up

### Course Journey

- **Week 1:** Mobile Ecosystem & Android Basics
- **Week 2:** UI Foundations (Views, Layouts, Compose, Activities)
- **Week 3:** State Management & Architecture (MVVM, Navigation, Clean Architecture)
- **Week 4:** Networking (Coroutines, Flow, Retrofit, REST APIs)
- **Week 5:** Location & Maps Integration
- **Week 6:** Data Persistence & Dependency Injection (Room, DataStore, Hilt, Firebase)
- **Week 8:** Background Processing & System Communication
- **Week 9:** Services & Lifecycle-aware Components
- **Week 10:** Hardware Integration & ML (Sensors, CameraX, ML Kit)
- **Week 11:** Testing (Unit, UI, MVVM Testing)

### Core Development Stack

- **Language**: Kotlin (concise, null-safety, coroutines)
- **UI Toolkit**: Jetpack Compose (modern declarative UI)
- **Architecture**: MVVM + Clean Architecture
- **Jetpack Libraries**: Navigation, Lifecycle, WorkManager, Hilt, DataStore, Accompanist
- **Tools**: Android Studio, Emulator, Gradle

### Designing Android UIs

- Compose replaces XML → Composable functions
- Layouts: Column, Row, Box
- Modifiers → styling, padding, size, colors
- Activity & Navigation → Intent, NavGraph, NavController
- Consistency with **Material Design & Theming**

### State & Architecture

- **State management**: remember, rememberSaveable, derivedStateOf
- **State hoisting**: makes Composables reusable & testable
- **MVVM**: Separation of concerns
  - Model → Data (Room, APIs)
  - ViewModel → Business Logic (Flows, Coroutines)
  - View → Compose UI

### Networking & Data

- Coroutines & Flow → async, reactive
- Retrofit + JSON parsing (Moshi, Kotlinx, Gson)
- Room DB + DataStore → persistence
- Hilt → Dependency Injection
- Firebase (extra) → sync, real-time DB, authentication

### Location & Background Tasks

- Maps SDK → markers, polylines, camera control
- Location Services → current location, interval updates
- Background tasks:
  - WorkManager → reliable scheduling and one-shot short task
  - Broadcast Receivers → system & custom events
  - Services (foreground, bound) → long-running work

### Hardware & Machine Learning

- Sensors → accelerometer, gyroscope, proximity, etc.
- CameraX → modern, lifecycle-aware, easy integration
- ML Kit → on-device ML (barcode scanning, text recognition, translation, etc.)

### Testing & Quality Assurance

- **Why testing?** reliability, maintainability, CI/CD
- Unit tests → UseCases, ViewModels
- UI tests → Compose Testing API
- Testing pyramid → focus on unit & integration tests

## React Native

### What is React Native?

- A framework for writing real and natively rendering mobile apps using only Javascript
- It is based on React, Facebook's JavaScript library for building user interfaces, and it targets the mobile platforms
- With React Native, we build a real mobile app that is indistinguishable from an app built using Objective-C or Java
- React Native uses the same fundamental UI blocks as regular iOS and Android apps. We just put those building blocks together using JavaScript and React
- React Native applications are written using a mixture of JavaScript and XML-esque markup
- Then React Native invokes the native rendering APIs in Objective-C (for iOS) or Java (for Android). Thus, the application will render real mobile UI components, not webviews
- Also expose Javascript interfaces for platform APIs, so React Native apps can access platform features
- Some React Native examples: Facebook, Skype, Instagram, Tesla, AirBnb, SoundCloud,…

### React Native vs Native App Development

| React Native | Native Development |
|---|---|
| Cross-platform development (Write one, use everywhere) | Platform-specific (Write twice, Android and iOS) |
| Simple performance | Complex performance |
| Low development cost | High development cost |
| Better scalability | Better UX/UI Experience |
| Large community and reusable components | Plenty of APIs and third-party libraries |
| Less secure | More native module support |

### When to Choose Native App Development

- Developing a complex application, especially a Messenger app
- Planning to launch a regular update
- Focused more on the native user experience
- The app relies on native device features (A Utility app)
- IoT-based mobile app
- Apps for an individual platform

### When to Choose React Native

- Developing a simple and uniform application
- Launching an app on cross-platforms
- Low-budget for app development
- Social media app
- Planning to embed Facebook ads into your app
- E-commerce mobile app
- If you company is a start-up

## React Native – How to Create a Project

### How to Create a React Native Project

- Software requirement: NodeJS and NPM (https://nodejs.org/en/download/), jdk8, expo, yarn (optional)
- Install Yarn: `npm install -g yarn`
- Install Expo: `npm install --global expo-cli`
- Install React Native CLI: `npm install -g react-native-cli`
- Create project: `npx create-expo-app@latest ProjectName` or `react-native init ProjectName` (A project template based on Expo or a plain React Native app)
- `cd ProjectName` and run the project

## React Native – Android App

### React Native – Run as an Android App (Windows only)

- cd to **android** folder, create a file named **local.properties** with the content:
- `sdk.dir=C:\\Users\\Username\\AppData\\Local\\Android\\sdk`
- Run the program at the project folder: `npm start`
- Run an Android emulator, use the commands to run start the app
- Open the project in an IDE and build the application starting from the **App.js** file
