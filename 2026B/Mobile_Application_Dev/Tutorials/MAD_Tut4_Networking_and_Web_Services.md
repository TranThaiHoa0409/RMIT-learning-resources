# Tutorial 4: Networking and Web Services

**Objectives:** Create an app that interacts with a pre-built RESTful web service. Instructions are given for POST/GET requests; you implement DELETE/PUT yourself. Provides essential knowledge/skills for apps consuming RESTful web services.

---

## Preparation: Run the Local JSON Server

A JSON server is pre-installed in the lab computers.

1. Use the provided **`db.json`** file — download it into a folder (e.g. `Documents`).
2. Run the JSON server start command in a terminal.
3. Test the server:
   - `http://localhost:3004/students`
   - `http://localhost:3004/comments`
   - If you see the JSON file's content, the server is working.
4. To install your own JSON server (requires Node.js): see the [DigitalOcean json-server tutorial](https://www.digitalocean.com/community/tutorials/jsonserver).

## Preparation: Use Postman to Test the REST Web Service

1. Open **Postman** (download from [getpostman.com](https://www.getpostman.com/) if needed).
2. A test RESTful web service is available at:
   `https://my-jsonserver.typicode.com/minhthanhvu/lecture04/users`
   — or use your own local service: `http://localhost:3004/students`
3. Test a **GET** request against this endpoint.
4. Try **POST**, **PUT**, and **DELETE** requests too:
   - For POST/PUT — put data in the **Body**.
   - For DELETE — put the `id` in the request URL.

> **Note:** `my-jsonserver.typicode.com` is a fake API server — changes are **not permanent** between requests, but it still returns correct responses.

---

## Using REST API in Android — GET/POST Requests

**Goal:** display the names of all students fetched from the RESTful students API (`http://localhost:3004/students`, or the hosted fallback URL). Set up dependencies/classes as covered in the Week 4 lecture.

### 1. Define the Student Data Model
- Use **Kotlinx Serialization** to convert between JSON and Kotlin objects.
- The model matches the structure of `db.json`.
- With `json-server`, if a POST body omits an `id`, one is auto-assigned incrementally.

### 2. Create the Retrofit API Interface
Define suspend functions annotated with HTTP verbs (`@GET`, `@POST`, etc.) representing each REST call. The `suspend` keyword allows calling them asynchronously via coroutines.

### 3. Create the Retrofit Instance
- Use **`10.0.2.2`** instead of `localhost` when accessing a local server from the Android **emulator**.
- Configure Retrofit to use **Kotlinx Serialization** as the converter.

**Troubleshooting `10.0.2.2` connection issues:**
1. Toggle the emulator's Airplane mode off/on.
2. Android Studio → **Settings → Appearance & Behavior → System Settings → HTTP Proxy** — ensure "No proxy" is selected, or manually configure the proxy to `10.0.2.2` with the correct port.
3. Use **ngrok**: install (`brew install ngrok` on Mac, or download for Windows), then run `ngrok http http://localhost:<portnumber>` and use the resulting public URL as your "localhost" in the emulator.

### 4. Display the List of Students
Adapt the lecture's code (which displayed *users*) to display *students* instead. Include a **`FloatingActionButton` (FAB)** to add a new student to the server.

### 5. Create `AddStudentScreen`
Collects user input and sends a **POST** request to the server. After a successful add, it navigates back to the list screen.

### 6. Implement `addStudent` in the ViewModel
```kotlin
fun addStudent(name: String, onComplete: () -> Unit = {})
```

### 7. Define the App Navigation Graph
Reuse the navigation approach from Week 3 to wire up the screens.

Run the app in the emulator. After sending the POST request, check the return status from the REST service — if successful, the server database should reflect the update.

---

## Exercise

Implement **per-student delete and update (PUT)** operations in the list.

---

*End of Tutorial 4 — Networking and Web Services*
