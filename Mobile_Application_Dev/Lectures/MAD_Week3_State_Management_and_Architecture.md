# Week 3: State Management and Architecture

**Course:** COSC2657 / COSC2543 / COSC2729 — Android Development
**Lecturer:** Minh T. Vu

## Agenda
- State management in Jetpack Compose
- Jetpack Navigation
- Model-View-ViewModel (MVVM) pattern
- Clean Architecture Principles
- Theming in Jetpack Compose

---

## 1. State Management in Jetpack Compose

- **State** refers to any data that can change over time, which the UI should react to.
- Compose supports several state types optimized for different use cases.
- When state changes, the UI **automatically updates** to reflect the new data — the UI is based on the *current state*, not controllers.
- This reactivity is fundamental to Compose's declarative nature.

### Local State
- Owned by a **single composable**, not shared outside it.
- `mutableStateOf()` tells Compose to observe a value for changes and redraw the composable whenever it updates.

### State Hoisting
- A design pattern where state is moved **up** to a higher composable function, managed more centrally.
- Makes state accessible to multiple composables — promotes separation of concerns.
- **Benefits:**
  - **Reusability:** stateless components are more flexible/reusable.
  - **Testability:** stateless components are easier to test (predictable, inputs controlled by the caller).
- Example: a Counter App built using state hoisting.

### `remember` vs. `rememberSaveable`
- **`remember`:** stores a value across recompositions within the same composable instance — keeps state without resetting on every recompose.
- **`rememberSaveable`:** an extension of `remember` that also saves state across **configuration changes** (e.g. screen rotation) using `SavedStateHandle`.

### Derived States
- **`derivedStateOf`** derives a value from other states. It recalculates only when its dependencies change, optimizing recomposition performance.

### Lifecycle-Aware State Management
- **`LaunchedEffect`:** runs side effects (e.g. fetching data) when a composable enters the composition; ties the task to the composable's lifecycle.
- **`DisposableEffect`:** manages setup and cleanup tasks (e.g. registering/unregistering listeners) tied to the composable's lifecycle.

### Common Use Cases
- **`mutableStateListOf`** for dynamic lists — keeps a list's state reactive when items are added/removed.
- **`LaunchedEffect` + network requests** — used for one-time network requests/side effects when a composable enters composition.
- **Forms with multiple states** — managing multiple input fields with individual states enables real-time validation and better control/feedback.
- **Complex data models** — use `mutableStateOf` to manage state objects and selectively update the UI based on changes.

---

## 2. Model-View-ViewModel (MVVM) Pattern

### Overview
- MVVM is the industry-recognized architecture pattern that overcomes the drawbacks of MVP and MVC.
- It separates the data presentation logic (View/UI) from the core business logic.

**Layers:**
| Layer | Responsibility |
|---|---|
| **Model** | Data and business logic |
| **View** | UI, responsible for displaying data |
| **ViewModel** | Middle layer — manages UI-related data/business logic, exposes it to the View |

### Why MVVM over MVC?
1. **Better separation of code** — cleaner separation of business logic from UI, easier to maintain.
2. **Easier testing** — ViewModel testable without needing the UI.
3. **Supports data binding** — automatic UI updates, less boilerplate.
4. **Team collaboration** — Model, View, ViewModel can be worked on independently.
5. **Reusable logic** — a single ViewModel can serve multiple views.

Caveat: not ideal for small projects; overly complex data-binding logic can make debugging harder.

### MVVM in Jetpack Compose
- The **View** = composable functions; the **ViewModel** interacts with the UI; the **Model** provides the data.

**Example walkthrough:**
1. **Project setup** — add dependencies to `build.gradle.kts`.
2. **Model (`User.kt`)** — a simple data class.
3. **ViewModel (`UserViewModel.kt`)** — exposes state to the View.
4. **View (`UserScreen.kt`)** — composable UI observing the ViewModel.
5. **Hook into `MainActivity`** and run the app.

---

## 3. Jetpack Navigation

### What is it?
A component of Android Jetpack that simplifies navigation between destinations (Fragments, Activities, Composables).

**Jetpack Compose Navigation uses:**
- **`NavController`** — manages navigation.
- **`NavHost`** — defines screen destinations.
- **`composable()`** — links routes to UI.
- Data can be passed between screens via **route arguments**.

### Setup Steps
1. **Project setup** — add dependencies to `build.gradle.kts`.
2. **Define navigation routes** — create a sealed class for screen routes.
3. *(Step 3 implied — set up NavController)*
4. **Home Screen UI**
5. **Detail Screen UI**
6. **Navigation Host setup**
7. **Run the app**

---

## 4. Clean Architecture Principles

### What is Clean Architecture?
A software design approach emphasizing **separation of concerns** by organizing code into independent layers. Each layer has a clear responsibility and communicates only with adjacent layers, making the app:
- **Scalable** — easy to grow/maintain
- **Testable** — logic testable in isolation
- **Flexible** — UI, business logic, and data sources evolve independently

### Standard 4-Layer Architecture (Android)
| Layer | Contains |
|---|---|
| **Presentation** | UI and ViewModels |
| **Domain** | Business rules and use cases |
| **Data** | APIs, databases, repositories |
| **Framework** | Retrofit/Ktor, Room, Android SDK |

**Responsibilities:**
- **Presentation:** UI + ViewModels — only talks to Domain.
- **Domain:** pure Kotlin logic (no Android dependency) — contains UseCases and Repository interfaces.
- **Data:** implements domain interfaces (e.g. via Ktor or Room).
- **Framework:** external libraries and platform-specific components.

**Dependency Rule:** outer layers can depend on inner layers, but **never the reverse**.

### Layer Breakdown
- **Domain Layer (Android-free):** Entities, Repository Interface, UseCase.
- **Data Layer:** Repository Implementation, Mapping Extensions.
- **Presentation Layer:** ViewModel, UI (Jetpack Compose or XML + Fragment/Activity).

### Tips for Clean Architecture in Android
- Use `sealed class` or `Result` for state handling (Success, Error, Loading).
- Add mapper packages for clean model conversion.
- Keep the Domain layer Android-free.
- Use `StateFlow` or `LiveData` to expose data from ViewModels.
- Structure by feature (modularization) as the app grows.

---

## 5. Android Theming

Self-study topic for the week:
- Dive into `MaterialTheme` and how it controls colors, typography, and shapes.
- Try switching between light and dark themes.
- Customize a theme and apply it to a simple screen.

**Resources:**
- [Material Design 3](https://m3.material.io/)
- Theming in Jetpack Compose with Material 3 (Codelab)
- Book: *Kickstart Modern Android Development With Jetpack and Kotlin* — Canvas → Reading List (VN)

---

*End of Week 3 — State Management and Architecture*
