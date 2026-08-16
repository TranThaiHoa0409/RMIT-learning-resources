# Week 4: Networking and Web Services

**Course:** COSC2657 / COSC2543 / COSC2729 — Android Development
**Lecturer:** Minh T. Vu

## Agenda
- Kotlin Coroutines and Flow
- RESTful Web Services
- Using RESTful Web Services in Android apps with Retrofit
- JSON Parsing Alternatives

---

## 1. Kotlin Coroutines and Flow

### What Are Coroutines?
Lightweight threads that let you write **asynchronous, non-blocking code** in a sequential, readable style.

**Key concepts:**
- `suspend` function — a function that can be paused and resumed.
- `CoroutineScope` — the scope in which coroutines run.
- `launch` — starts a coroutine that doesn't return a result.
- `async` — starts a coroutine that returns a `Deferred` result.

**Example concepts (from slide):**
- `runBlocking {}` connects the non-coroutine world (e.g. `main`) with coroutines.
- `launch` starts a coroutine without blocking the thread.
- `delay` is a **non-blocking** delay (like `sleep`, but only suspends the current coroutine).

### What is Flow?
A **cold, asynchronous, reactive stream of data** emitted sequentially — part of Kotlin's coroutines library, used for handling streams of values (similar to RxJava, but more lightweight).

**Key concepts:**
- `flow {}` — builds a flow.
- `emit()` — sends a value into the flow.
- `.collect()` — receives values from the flow.
- `.catch {}` — handles exceptions occurring upstream (inside `flow {}`).
- **Cold stream:** nothing happens until it's collected.

**Example (slide):** a `flow {}` block emits several values with a `delay()` between each, and a `.collect {}` call on the consumer side prints each value as it arrives — demonstrating the "nothing happens until collected" cold-stream behavior.

---

## 2. RESTful Web Services

### What is a Web Service?
A **Consumer-Machine-to-Provider-Machine** collaboration schema operating over a computer network. Data exchange happens independently of the OS, browser, platform, and programming languages of provider and consumers.

- A provider may expose multiple **endpoints** (sets of services), each offering related functions.
- Web services rely on standard web protocols: **XML, HTTP, HTTPS, FTP, SMTP**.
- Examples: weather information, currency exchange rates, world news, stock quotes — modeled as a remote data-services provider surrounded by many consumers.

### How to Consume a Web Service?
- This course uses the **REST** architecture.
- REST clients refer to remote services through a conventional **URL** that typically includes: the (stateless) server location, the service name, the function to execute, and any parameters.
  - Example: a URL calling a Google Search service for the subject "RMIT Vietnam".
- Data is transported over **HTTP/HTTPS**, based on the **client-server model**.

### REST (Representational State Transfer) Architecture
- REST operations map to the common HTTP methods: **GET, POST, PUT, DELETE**.
- **REST server** provides access to resources; **REST client** accesses/presents resources.
- **REST resource:**
  - Identified using URIs/Global IDs.
  - Represented using Text, JSON, or XML.

### JSON
**JSON (JavaScript Object Notation)** is a standard, text-based data-interchange format that enables applications to exchange data over a computer network.

### REST Verbs
| Verb | Typical Use |
|---|---|
| `GET` | Retrieve a resource |
| `POST` | Create a new resource |
| `PUT` | Update/replace a resource |
| `DELETE` | Remove a resource |

### HTTP Request Structure
When calling an API, a request has these components:
- **Method:** GET, POST, etc.
- **URL:** path to the resource (e.g. `/users`)
- **Headers:** metadata such as content-type, authentication token
- **Body:** JSON content (used in POST/PUT)

### Postman
**Postman** is a comprehensive API platform for building, testing, and managing APIs.
- Lets you define API requests: HTTP methods (GET, POST, PUT, DELETE), headers, and bodies.
- Used to run a local JSON server and test it (see Tutorial 4 — Preparation).

---

## 3. Using RESTful Web Services in Android (Retrofit)

### Introducing Retrofit
**Retrofit** is a type-safe HTTP client for Android, created by Square, for making REST API calls simple and clean.

**Why use Retrofit:**
- Automatically converts JSON ↔ Kotlin objects.
- Supports coroutines and Flow.
- Reduces boilerplate code.

**Key features:**
- Supports different converters: **Gson, Moshi, Kotlinx.serialization**.
- Integrates with Coroutines, LiveData, RxJava.
- Simplifies API structure with annotations.

### Setting Up Retrofit in a Compose Project
1. Add plugins/libraries in `libs.versions.toml` (Version Catalog).
2. Add dependencies and plugins in `build.gradle.kts`.
3. Configure `AndroidManifest.xml` (e.g. internet permission).

### Building the REST Layer
1. **Define a data class for JSON objects**
   - `@Serializable` — required for Kotlinx.serialization.
   - Class field names should match JSON keys (or use `@SerialName` for mismatches).

2. **Retrofit interface (service layer)**
   - `@GET("users")` maps to `https://baseurl/users`.
   - `suspend` marks the function for coroutine use.
   - The return type is automatically deserialized (e.g. into `List<User>`).

3. **Retrofit builder**
   - `baseUrl` must end with `/`.
   - `Json.asConverterFactory(...)` — uses Kotlinx.serialization to convert JSON.

4. **Using coroutines in the repository**
   - Why use Flows: supports real-time streaming; works naturally with Compose/`StateFlow`; easy error handling with `.catch {}`.

5. **ViewModel integration**
   - `viewModelScope.launch` ties the coroutine to the ViewModel's lifecycle.

6. **Jetpack Compose UI**
   - `collectAsState()` — collects a `StateFlow` into a Composable.
   - `LazyColumn` — optimized scrolling list.
   - Compose is fully reactive — the UI auto-updates on data change.

---

## 4. JSON Parsing Alternatives

| Library | Notes |
|---|---|
| **Kotlinx.serialization** | Native for Kotlin, lightweight, multiplatform |
| **Moshi** (Square) | Default JSON converter for Retrofit 2, more flexible, great for custom parsing, supports annotations like `@Json` |
| **Gson** | Older, common but outdated for modern Kotlin projects |

---

*End of Week 4 — Networking and Web Services*
