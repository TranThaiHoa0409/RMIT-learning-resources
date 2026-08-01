# Tutorial 3: State Management and Theming

**Objectives:** Get familiar with state management, navigation between composables, and architectural best practices in Jetpack Compose using **MVVM** and **Clean Architecture**. Build on understanding of reactivity, modularization, and navigation by building a multi-screen task manager app. Good preparation for upcoming assignments. Finish the exercise by the end of the week.

---

## Exercise

Build a **single-Activity** Android app with **three composable screens** that demonstrate:
- State hoisting
- Screen-to-screen navigation
- Clean separation of logic using **MVVM** and **Clean Architecture** principles

Ensure the app works correctly across different configurations (e.g. screen rotation).

### App Overview
Create an app with the following screens:
- **`HomeScreen`** — displays a list of tasks and a button to add a new one.
- **`AddTaskScreen`** — a form screen to enter a task title and description.
- **`TaskDetailScreen`** — displays task details and allows deletion.

---

### `HomeScreen` Requirements

**Contains:**
- A list of existing tasks displayed using a **`LazyColumn`**.
- The number of existing tasks, computed via **`derivedStateOf`**.
- A **`FloatingActionButton`** labeled **"Add Task"**.

**Behavior:**
- Clicking a task → navigates to `TaskDetailScreen`, passing the task ID.
- Clicking **"Add Task"** → navigates to `AddTaskScreen`.

---

### `AddTaskScreen` Requirements

**Displays a form with:**
- **Title** — required
- **Due Date** — required
- **Description** — optional
- A **"Save"** button

**Behavior:**
- The **Save** button is **disabled** until both Title and Due Date are filled in.
- On **Save**: the task is added via the ViewModel, and the app navigates back to `HomeScreen` (task count updates automatically).
- A **Snackbar** is shown: `"Task '<Task_Title>' added"`.

---

### `TaskDetailScreen` Requirements

**Displays:**
- Task title, due date, and description.
- A **"Delete"** button.

**Behavior:**
- On **Delete**: the task is removed, and the app navigates back to `HomeScreen`.
- A **Snackbar** is shown: `"Task '<Task_Title>' deleted"`.

---

### Key Skills Exercised
- State hoisting and reactive UI updates.
- `derivedStateOf` for computed values.
- `LazyColumn` for lists.
- Conditional UI logic (disabling a button based on form validity).
- `Snackbar` feedback messages.
- Jetpack Navigation between composable screens with argument passing (task ID).
- MVVM separation (ViewModel handling add/delete logic) and Clean Architecture layering (per Week 3 lecture material).

---

*End of Tutorial 3 — State Management and Theming*
