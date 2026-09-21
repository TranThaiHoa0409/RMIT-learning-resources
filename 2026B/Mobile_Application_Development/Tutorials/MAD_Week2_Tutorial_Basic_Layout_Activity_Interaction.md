# Tutorial 2: Basic Layout and Activity Interaction

**Objectives:** This tutorial will get you familiar with Android Activity & Jetpack components. You will learn through experience some of the modern Android development elements which will prepare you for the Assignment 1. It is also a good chance to improve your Kotlin coding skills. Make sure you finish the exercise by the end of the week.

## Exercises

Build an Android app with three activities that demonstrate two-way communication and dynamic UI using Jetpack Compose.

### App Overview

Create an app with the following screens:

| Screen | Description |
|---|---|
| `MainActivity` | Entry screen with a name input and two navigation buttons. |
| `StudentFormActivity` | A form screen that receives and displays the student's name. |
| `StudentServicesActivity` | A screen with multiple service buttons. |

### MainActivity Requirements

**Contains:**

- A `TextField` for entering the student's name.
- Two buttons: "Student Form" and "Student Services".

**Behavior:**

- When "Student Form" is clicked: The entered name is passed to `StudentFormActivity`.
- When "Student Services" is clicked: Navigates to `StudentServicesActivity`.

### StudentFormActivity Requirements

Displays a form UI (you can design freely).

**Must include:**

- A `TextField` filled with the student's name received from `MainActivity`.
- A **Submit** button.
- A **Go to Services** button.

**Behavior:**

- On **Submit**, return to `MainActivity` and display a message: "Thank you \<Student_Name\> for submitting your form."
- On **Go to Services**, navigate to `StudentServicesActivity`.

### StudentServicesActivity Requirements

Displays a list of service buttons (e.g., Library, IT Support, Counseling).

**Must include:**

- A list of buttons for the services.

**Behavior:**

- When a service button is clicked, return to `MainActivity` and display a message: "Thank you for selecting \<Service_Name\> service."
