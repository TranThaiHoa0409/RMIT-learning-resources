# Tutorial 3 — Clean Architecture + Jetpack Compose Navigation (Task App)

## 1. Overall Architecture

The project is split into three layers, following Clean Architecture:

```
presentation  →  domain  ←  data
```

- **domain**: pure business logic. No dependency on Android, Compose, or any specific data source.
- **data**: implements the domain's repository interface using an actual (or fake) data source.
- **presentation**: UI (Compose screens), state management (ViewModel), and navigation.

Key rule to remember: both `data` and `presentation` depend on `domain`, but `domain` never depends on them. This is what keeps the business logic (use cases) testable and independent of UI/data-source changes.

## 2. Domain Layer

| File | Role |
|---|---|
| `domain/model/Task.kt` | Data class representing a Task at the business-logic level (separate from any data-layer entity). |
| `domain/repository/TaskRepository.kt` | Interface only — declares what operations are available (get/add/delete tasks) without saying where the data comes from. |
| `domain/usecase/GetTasksUseCase.kt` | Wraps "get all tasks" as a single-purpose use case. |
| `domain/usecase/AddTaskUseCase.kt` | Wraps "add a task" as a single-purpose use case. |
| `domain/usecase/DeleteTaskUseCase.kt` | Wraps "delete a task" as a single-purpose use case. |

Point to remember: the ViewModel calls **use cases**, not the repository directly — each use case represents one discrete business action.

## 3. Data Layer

| File | Role |
|---|---|
| `data/datasource/FakeTaskDataSource.kt` | Fake/in-memory data source, used before a real DB or API exists — lets the app run end-to-end without a backend. |
| `data/repository/TaskRepositoryImpl.kt` | Implements the `TaskRepository` interface from the domain layer, wiring it to the fake data source. |

Point to remember: this is the layer to swap out later (e.g. replace `FakeTaskDataSource` with Room or a real API) without touching domain or presentation code.

## 4. Presentation Layer

| File | Role |
|---|---|
| `presentation/task/viewmodel/TaskViewModel.kt` | Holds UI state (task list, selected task, etc.), calls the use cases, and exposes state to Compose (State/StateFlow). |
| `presentation/task/navigation/TaskNavRoutes.kt` | Declares the route names/constants used for navigation. |
| `presentation/task/navigation/NavGraph.kt` | Sets up `NavHost` and maps each route to its Composable screen — this is the new technique introduced in this tutorial (Navigation Compose). |
| `presentation/task/ui/HomeScreen.kt` | Shows the task list, reads state from `TaskViewModel`. |
| `presentation/task/ui/AddTaskScreen.kt` | Form/UI for creating a new task. |
| `presentation/task/ui/TaskDetailScreen.kt` | Shows details of a selected task. |
| `MainActivity.kt` | Entry point — sets up `NavGraph` inside `setContent`. |

Point to remember: each screen receives the `ViewModel` and/or `NavController` to read state and trigger navigation — this is the standard MVVM + Navigation Compose wiring pattern.

## 5. Setup Not Included by Default (must redo manually in a new project)

A fresh "No Activity" project does **not** include these — add them back manually:

**`gradle/libs.versions.toml`** — add to `[versions]`:
```toml
lifecycleViewmodelCompose = "2.9.2"
navigationCompose = "2.9.2"
```
and to `[libraries]`:
```toml
androidx-lifecycle-viewmodel-compose = { module = "androidx.lifecycle:lifecycle-viewmodel-compose", version.ref = "lifecycleViewmodelCompose" }
androidx-navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigationCompose" }
```

**`app/build.gradle.kts`** — add to `dependencies { }`:
```kotlin
implementation(libs.androidx.navigation.compose)
implementation(libs.androidx.lifecycle.viewmodel.compose)
```

Without these two dependencies, Navigation Compose (`NavGraph.kt`) and the ViewModel-Compose integration (`TaskViewModel.kt` usage in screens) won't compile.
