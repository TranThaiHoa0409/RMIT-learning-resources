# Week 2: Android UI Foundations

**Course:** COSC2657 / COSC2543 / COSC2729 — Android Development
**Lecturer:** Minh T. Vu

## Agenda
- View component in Android
- Traditional View Layouts in Android
- Jetpack Compose Layouts
- Introduction to the Android Activity component
- Android Activity life cycle
- Interactions between different Activities

---

## 1. Traditional View & Layouts

### View Component in Android
- **View** is the base class for UI controls such as Button, TextView, EditText, etc.
- **ViewGroup** is a subclass of View that holds many views together.
- **Layout** is a subclass of ViewGroup (grandchild of View) — represents the visual structure for an activity or widgets.
- The `widget` package contains (mostly visual) UI elements for your application screen.
- The **view group** is the base class for layouts and view containers.

### Defining a Layout
Layouts can be defined in **two ways**:
1. **Static — Declare in XML:** Android provides an XML vocabulary corresponding to View classes/subclasses (widgets and layouts).
2. **Dynamic — Instantiate at runtime:** create `View`/`ViewGroup` objects programmatically and manipulate their properties in code.

**Static layout creation:**
- Layout XML files live in `res/layout/activity_name.xml`.
- Bind a layout XML to an activity using `setContentView(R.layout.layout_name)` inside `Activity.onCreate()`.

### Common Traditional Layouts
| Layout | Description |
|---|---|
| `FrameLayout` | Contains a single child view |
| `LinearLayout` | Lays out children horizontally or vertically |
| `RelativeLayout` | Positions children relative to the container or other views |
| `GridView` | A scrollable grid |
| `ListView` | A vertical, scrollable list of items |

**Demo:** Using `LinearLayout` (see slide for walkthrough).

### Dynamic Layouts
Each XML element (e.g. `LinearLayout`, `TextView`) is really an instance of a Java/Kotlin class — components can be added to the screen **dynamically using code**, not just declared in XML.

---

## 2. Jetpack Compose Layouts

### Why Jetpack Compose?
- Declarative UI toolkit for Android
- Replaces XML-based layouts
- Built entirely in Kotlin
- More concise, readable, and powerful
- Integrates seamlessly with Android Studio

### Layout in Compose
- A layout is a container that decides how child **composables** are arranged on screen (size + position, based on rules/constraints).
- Written entirely in Kotlin code.

**Key concepts:**
- **Composable function:** every UI element is a function that "composes" UI content.
- **Modifier:** defines how a layout behaves/looks — size, margin, alignment, background color, etc.

### Basic Layout Containers
| Container | Behavior |
|---|---|
| `Column` | Arranges children **vertically**, one below the other; takes as much vertical space as needed. |
| `Row` | Arranges children **horizontally**, left to right; takes as much horizontal space as needed. |
| `Box` | Layers children **on top of each other** (overlay/stacking); by default each child is positioned top-left unless specified. |

### Modifiers
Fundamental to Compose — define composable behavior/appearance:
- `padding()` — space around the composable
- `size()` — sets composable size
- `fillMaxSize()` — expands to fill available space in both dimensions
- `background()` — sets background color/drawable
- `clickable()` — makes the composable clickable

### Alignment and Arrangement
- **Horizontal/Vertical Arrangement:** distributes space between children.
- **Alignment:** aligns children within the parent layout.

### Self-Learning Resources
- [Compose layouts documentation](https://developer.android.com/develop/ui/compose/layouts)
- [Jetpack Compose learning pathway](https://developer.android.com/courses/pathways/compose)
- Book: *Kickstart Modern Android Development With Jetpack and Kotlin* — Canvas → Reading List (VN)

---

## 3. Android Activity

- An **Activity** is a single, focused thing a user can do.
- Most activities interact with the user; an activity presents a single screen containing a UI.
- Analogous to a Window/Frame — or a web page.
- No restriction on the number of Activities per app.

### Activity Life Cycle
The Activity lifecycle diagram covers the standard callback methods (`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`, `onRestart`) governing an activity's transitions through active, visible, and background states.

### Tasks and Backstack
- Activities are grouped into **tasks**. Typically, all activities of a single app share the same task.
- If an activity launches a second activity, the first goes to the **back of the stack (backstack)**, and the second becomes visible and sits on top of the stack.

### Activity States
An Activity has **4 states**:
| State | Description |
|---|---|
| **Active / Resumed** | Top of the stack, visible, and interactive |
| **Paused** | Can be visible, but without focus |
| **Stopped** | Not visible |
| **Inactive** | Completely removed from the activity stack |

- Paused and Stopped activities can be **killed by the system at any time**.
- States are triggered by the user (e.g. switching apps) or by the system.

---

## 4. Interactions Between Activities

### What is an Intent?
An **Intent** is a messaging object that facilitates communication between components.

### Starting an Activity
Use an `Intent` to start an activity.

### Passing Data Between Activities
- Caller side: use `putExtra()` to send data.
- Callee side: use `getStringExtra()` (or similar) to retrieve the data.

### Modern Approach — ActivityResultLauncher (Jetpack)
Activities often need to send/receive results; Jetpack provides a modern, lifecycle-aware way to handle this.

- Part of the **Jetpack Activity** library.
- Replaces the deprecated `startActivityForResult()` / `onActivityResult()`.
- **Benefits:** lifecycle-aware, type-safe, cleaner code.

**Step 1 — Set up the launcher**
- `registerForActivityResult()` can be called in an Activity or Fragment.
- The lambda passed handles the result (callback).

**Step 2 — Launch the second Activity**
- Pass data with `Intent.putExtra()`.
- Launch with `launcher.launch(intent)`.

**Step 3 — Return a result (from the second Activity)**
- Send data back to the calling activity via `Intent`.
- `finish()` terminates the current activity:
  - Removed from the activity stack.
  - Destroyed — triggers `onPause()`, `onStop()`, `onDestroy()`.
  - User is returned to the previous activity; if started for a result, the result is delivered to the caller.

### Compose Integration
- Use `rememberLauncherForActivityResult()` in Jetpack Compose.
- Works seamlessly with Compose UI, keeping logic and UI reactive and clean.

---

*End of Week 2 — Android UI Foundations*
