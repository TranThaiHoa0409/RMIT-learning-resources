# Tutorial 1: Intro to Android

**Objectives:** Get familiar with Android Studio, learn basic layout techniques (preparation for Week 2), and practice Kotlin coding skills. Finish all exercises by the end of the week.

---

## 1. Getting Started
- Start Android Studio (on Mac: `Cmd + Space` → type "Android Studio").
- Go to **Projects → New Project** to create a new project.
- Choose a template: **Phone and Tablet → No Activity** (for now).
- Android Studio will prompt for project name, location, etc. — standard for any IDE.
- You'll also be asked for the **Minimum SDK** (Android API version). Choose a moderate option — **API 24 (Nougat, Android 7.0)** is recommended, reaching ~98% of devices. This can be changed later.

## 2. Getting Familiar with the Studio
Like other IDEs, Android Studio has:
- **Explorer window** (left) — navigate/create project files and resources.
- **Code window** (right) — write code.
- Both **Kotlin and Java** are available in the same Android project.
- **`AndroidManifest.xml`** is the project's configuration file, providing essential info to the Android system about your app.

## 3. Designing User Interfaces
Two main approaches to building UI in Android:

1. **XML-based UI** — the traditional approach; layouts defined in `.xml` files (e.g. `res/layout/activity_main.xml`). Use the **Design tab** to drag and drop components (Button, TextView, EditText).
2. **Jetpack Compose (Kotlin-based UI)** — the modern, declarative approach; layouts defined directly in Kotlin using composable functions, no XML needed.

This course introduces both, starting with **XML in early tutorials** and gradually transitioning to **Jetpack Compose** in later weeks.

### Creating an XML-based Activity
- Right-click the code package → **New → Activity → Empty Views Activity**.
- Specify activity properties; choose **Launcher Activity** if it should be the app's entry point. Choose **Kotlin** as the language.
- The activity and its layout are created in the project (`res → layout → activity_main.xml`), and the activity is registered in `AndroidManifest.xml`.

## 4. Running Your App
Before writing more code, learn to run the app on an **Android emulator** (a virtual device simulating a real phone):

1. Open Android Studio with the project loaded.
2. Click the green **Run** button (▶) in the toolbar.
3. If no emulator exists yet:
   - Android Studio prompts you to create a new virtual device.
   - Choose a device model (e.g. Pixel 4) and system image (e.g. Android API 30).
   - Click **Finish** to create the emulator.
4. Once the emulator starts, the app installs and launches automatically.

**Tip:** manage/create virtual devices via **Tools → Device Manager**.

## 5. Layout Practice (Design Panel)
- Open `res/layout/activity_main.xml`, switch to **Design View**.
- Drag and drop:
  - `LinearLayout` (Vertical)
  - `LinearLayout` (Horizontal)
  - `Button`
  - `EditText` (Plain Text)
  - `TextView`
- These appear both on the design canvas and in the **Component Tree**.
- The default layout is **`ConstraintLayout`**. For more on ConstraintLayout and its properties, watch: [youtube.com/watch?v=4N4bCdyGcUc](https://www.youtube.com/watch?v=4N4bCdyGcUc)

## 6. Start Coding — Button Events
Two ways to set an event listener for a button: **declarative** and **programmatic**. This tutorial uses the **programmatic** way — click the `.kt` file to switch to the coding view.

Key APIs:
- `Toast.makeText().show()` — displays a message on screen.
- `findViewById` — returns the reference to a control (a View).

Run the app again on the Emulator after adding the click handler.

## 7. Advanced: Fixing the Layout
With the default `ConstraintLayout`, components may all appear at the same position (e.g. top-left, `(0,0)`) when run on the emulator — this happens because the views are **not constrained**; they only have design-time positions and jump to `(0,0)` at runtime unless constraints are added. Watch the ConstraintLayout video above to learn how to fix this.

---

## 8. Exercises

1. **Marriage Oracle App** — Build an app with Name and Age `EditText`s and a button. On click, generate a random number; if `number mod 5 == 3` **and** age > 18, the person "will get married this year."
   - Convert the Age `EditText` text to an integer using `toIntOrNull()`:
     ```kotlin
     val age = ageEditText.text.toString().toIntOrNull()
     ```
2. **BMI Calculator** — Two text boxes: height (m), weight (kg).
   - `BMI = weight / (height * height)`
   - Add a button to switch units to the American system (inches and pounds/height).
3. **Lottery Game** — One `EditText` for the user's guess (1–10). A button draws the lottery (system generates a random number 1–10). If it matches the guess, display **"Win"**. Include a button to restart the game.
4. **Calculator** — Build a calculator supporting add, subtract, multiply, and divide.

### Extra — Jetpack Compose
Create a new project via **Projects → New Project → Phone and Tablet → Empty Activity** (this template is Compose-based, with Compose libraries/theme pre-configured — *Empty Activity* = Compose, *Empty Views Activity* = XML-based).

Try adapting the XML-based exercise above to the Compose approach. Explore features such as **Live Edit**, **Interactive Preview**, **Material Design Integration**, and **Modifiers for styling/layout**.

---

*End of Tutorial 1 — Intro to Android*
