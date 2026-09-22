# Tutorial 3: State Management and Theming

**Objectives:** This tutorial will get you familiar with state management, navigation between composables, and applying architectural best practices in Jetpack Compose using MVVM and Clean Architecture. You will build on your understanding of reactivity, modularization, and navigation by building a multi-screen task manager app. This is great preparation for your upcoming assignments. Make sure you finish the exercise by the end of the week.

## Exercises

Build a single-Activity Android app with three composable screens that demonstrate state hoisting, screen-to-screen navigation, and clean separation of logic using the MVVM pattern and Clean Architecture principles. Make sure the app works correctly across different configurations.

Create an app with the following screens:

- **HomeScreen**: Displays a list of tasks and a button to add a new one
- **AddTaskScreen**: A form screen to enter a task title and description
- **TaskDetailScreen**: A screen that displays task details and allows deletion

### HomeScreen Requirements

Contains:

- A list of existing tasks displayed using a `LazyColumn`
- The number of existing tasks using `derivedStateOf`
- A `FloatingActionButton` labeled "Add Task"

Behavior:

- Clicking on a task navigates to `TaskDetailScreen`, passing the task ID
- Clicking "Add Task" navigates to `AddTaskScreen`

### AddTaskScreen Requirements

Displays:

- A form with the following input fields:
  - `Title`: Required
  - `Due Date`: Required
  - `Description`: Optional
- A `Save` button

Behavior:

- The `Save` button is disabled until **both** the Title and Due Date fields are filled in
- On `Save`, the task is added using the ViewModel and the app navigates back to `HomeScreen`. The number of existing tasks will be updated.
- A `Snackbar` is shown with the message: "Task '\<Task_Title\>' added"

### TaskDetailScreen Requirements

Displays:

- Task title, due date, and description
- A `Delete` button

Behavior:

- When the `Delete` button is clicked, the task is removed and the app navigates back to `HomeScreen`
- A `Snackbar` is shown with the message: "Task '\<Task_Title\>' deleted"
