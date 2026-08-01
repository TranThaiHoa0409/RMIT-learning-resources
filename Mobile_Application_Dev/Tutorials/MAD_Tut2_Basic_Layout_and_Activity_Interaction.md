# Tutorial 2: Basic Layout and Activity Interaction

**Objectives:** Get familiar with Android Activity and Jetpack components through building a small multi-activity app using Jetpack Compose. This prepares you for Assignment 1 and improves Kotlin coding skills. Finish the exercise by the end of the week.

---

## Exercise

Build an Android app with **three activities** demonstrating two-way communication and dynamic UI using Jetpack Compose.

### App Overview
Create an app with the following screens:
- **`MainActivity`** — entry screen with a name input and two navigation buttons.
- **`StudentFormActivity`** — a form screen that receives and displays the student's name.
- **`StudentServicesActivity`** — a screen with multiple service buttons.

---

### `MainActivity` Requirements

**Contains:**
- A `TextField` for entering the student's name.
- Two buttons: **"Student Form"** and **"Student Services"**.

**Behavior:**
- Clicking **"Student Form"** → the entered name is passed to `StudentFormActivity`.
- Clicking **"Student Services"** → navigates to `StudentServicesActivity`.

---

### `StudentFormActivity` Requirements

Displays a form UI (free design). Must include:
- A `TextField` pre-filled with the student's name received from `MainActivity`.
- A **"Submit"** button.
- A **"Go to Services"** button.

**Behavior:**
- On **Submit** → return to `MainActivity` and display the message:
  > "Thank you `<Student_Name>` for submitting your form."
- On **Go to Services** → navigate to `StudentServicesActivity`.

---

### `StudentServicesActivity` Requirements

Displays a list of service buttons (e.g. Library, IT Support, Counseling). Must include:
- A list of buttons for the services.

**Behavior:**
- When a service button is clicked → return to `MainActivity` and display the message:
  > "Thank you for selecting `<Service_Name>` service."

---

### Key Skills Exercised
- Passing data between activities (`Intent.putExtra()` / retrieving extras).
- Returning results to a calling activity (e.g. via `ActivityResultLauncher` / `rememberLauncherForActivityResult()`, per Week 2 lecture material).
- Building simple forms and button-driven navigation with Jetpack Compose.

---

*End of Tutorial 2 — Basic Layout and Activity Interaction*
