# MAD Week 6 – Pre-Class: Data Persistence and Dependency Injection

## 1. Data Persistence in Android

Apps need to remember things such as user preferences, offline data, and login status. This section examines how Android handles data storage.

### Storing Data With SQLite and Room Database

Every Android device comes with SQLite built in, a lightweight database that does not require a server or an internet connection. It is used for storing structured data locally on the user's device.

Working directly with SQLite means writing SQL queries manually, for example:

```sql
CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, email TEXT);
INSERT INTO users (name, email) VALUES ('John', 'john@email.com');
```

This becomes complex quickly and is prone to errors, requiring the developer to remember SQL syntax, handle type conversions, and write a large amount of boilerplate code. Room Database is used to make this process easier.

#### Room Database

Room is Google's solution that wraps SQLite with a more developer-friendly interface. Instead of writing SQL, developers work with Kotlin data classes and annotations.

| Feature | SQLite | Room |
|---|---|---|
| Query Validation | Errors occur at runtime. | Errors detected at compile-time. |
| Boilerplate Code | Requires writing raw SQL and managing connections. | Abstracts SQL with DAO methods. |
| Integration | No direct integration with LiveData/Coroutines. | Works seamlessly with LiveData and Coroutines. |
| Learning Curve | Requires knowledge of raw SQL. | Simple and easy to learn. |

### Preferences DataStore

Not everything needs a database. DataStore is designed for storing simple key-value pairs, such as user preferences, app settings, or configuration data. Examples of data that can be stored include:

- User preferences (dark mode, notification settings)
- App version and configuration
- Simple user data (username, last login)
- Feature flags and settings

DataStore is the modern replacement for SharedPreferences: [Preferences DataStore codelab](https://developer.android.com/codelabs/android-preferences-datastore?hl=en#0)

The industry may also utilise a NoSQL database, such as Firebase, which is a BaaS (Backend as a Service): [Firebase](https://firebase.google.com/)

## 2. Managing Dependencies

As the number of components such as Room, DataStore, and Firebase grows, an app needs a way to organise these components without creating a tangled mess of dependencies.

Without proper organisation, code becomes tightly coupled, with classes creating their own dependencies, making testing difficult and the code inflexible.

### Dependency Injection

Dependency Injection (DI) is a pattern where objects receive their dependencies from external sources rather than creating them internally.

| Aspect | Without DI | With DI |
|---|---|---|
| Testability | Hard to test (cannot replace the database with a test version) | Easy to test (inject mock dependencies) |
| Coupling | Tightly coupled to specific implementations | Flexible (can swap implementations) |
| Clarity | Difficult to change or configure | Clear about what each class needs |

### Hilt: Automated Dependency Injection

Hilt is Google's dependency injection library that automatically generates the code to provide dependencies where they are needed.

What Hilt does:

- Automatically creates and manages object instances
- Ensures proper lifecycle management (objects live as long as needed)
- Reduces boilerplate dependency setup code
- Integrates seamlessly with Android components
