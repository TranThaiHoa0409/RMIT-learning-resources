# Week 1: Introduction to Android

**Course:** COSC2657 / COSC2543 / COSC2729 — Android Development
**Lecturer:** Minh T. Vu

## Agenda
- Course delivery
- Introduction to Mobile App Development
- Introduction to Android & Android Studio
- Kotlin Basics
- Introduction to Jetpack Compose
- Introduction to MVVM Architecture

---

## 1. Overview of the Mobile App Market

### History of Mobile Platforms
Symbian, Brew, Windows CE/Mobile, Java ME, BlackBerry, iOS, Android, Windows Phone/Windows 10 Mobile.

### Mobile Operating Systems Today
Only two main options remain:
- **Android & iOS** — ~72% global market share combined, ~27% in premium markets.

**Others (mostly declining/niche):**
- Windows Mobile — retired 2017, support ended 2019
- Tizen (Samsung) — mainly smart TVs/wearables now
- BlackBerry — discontinued as a phone OS; focus shifted to enterprise software
- Linux (e.g. Ubuntu Touch) — actively developed by UBports; niche devices (PinePhone, Fairphone)
- WebOS — now open source; used in LG TVs/smart appliances
- Dead: Symbian, Palm, Brew, Java ME

**Cross-platform frameworks:**
- HTML5/JavaScript — hybrid apps and PWAs
- PhoneGap/Apache Cordova — legacy, minimal active development
- Microsoft Xamarin — being merged into .NET MAUI
- Flash — dead for mobile (rebranded Adobe Animate CC, animation only)
- **Flutter** — Google's UI toolkit for natively-compiled apps from one codebase
- **React Native** — Meta's framework using JavaScript/React

### "Share" — Different Meanings
| Type | Notes |
|---|---|
| Press Share | Media coverage |
| Mind Share | Brand awareness |
| Market Share* | Android leads |
| Usage Share* | iOS leads |
| Profit Share* | iOS leads |
| Spend Share | Developers want this for good ROI |

iOS leads Usage, Profit, and ROI; Android leads Market Share. Press/Mind share are hard to measure.

### The Android App Market — Trade-offs
- **Lowest common denominator approach:** aim for maximum *customer* coverage, not maximum *device* coverage. The more non-optional specialized features you add, the smaller your potential market.
- **Paying customer approach:** theory that customers with money/willing to pay tend to have the latest phones.

**App categories:**
- **Games:** dominated by big developers (Angry Birds, Pokémon), but small local devs can succeed (e.g. Crossy Road).
- **Business apps:** free for customers, paid for by the business (part of a larger product/service); Android chosen because most international customers use it.
- **Entrepreneurial apps:** a tough, competitive market.

---

## 2. Introduction to Android

- Mobile operating system developed by Google.
- Runs on smartphones, tablets, watches, cameras, game consoles, TVs, and even PCs.
- Originally developed by Android Inc., acquired by Google in 2005; first release in 2008.

### Current Picture
- Current version: **Android 16**.
- Best-selling OS: ~3 billion active users, ~3.5 million apps on Play Store.
- References: [developer.android.com/about/versions](https://developer.android.com/about/versions), [Android version history (Wikipedia)](https://en.wikipedia.org/wiki/Android_version_history)

### What is Android?
- Android is a Java application that runs on a **modified Linux**.
- **Linux vs. Linux Kernel:** what we usually call "Linux" (Ubuntu, Fedora, RedHat) is really a *Linux distribution*. Android only uses the **Linux kernel** — not other standard libraries such as the GNU shell, GNOME desktop, etc.
- Android originally used the **Dalvik virtual machine**; replaced by **Android Runtime (ART)** since Android 5.0.
- Android is Linux, but:
  - Linux software can run on Android — only in very limited ways.
  - Android apps can run on Linux — only with a virtual machine or simulator.

### Android Structure (layers)
1. Linux Kernel
2. Android API + Android Runtime
3. Application Framework
4. Application — where developers build their apps

### JDK vs. SDK
- **JDK (Java Development Kit):** tools to compile and run Java applications; includes the JVM. Key tools: `javac`, `java`, `jar`, `keytool`.
- **Java SDK:** an extended JDK that also helps compile, run, debug, and monitor Java applications.

### Android SDK
- Android SDK = JDK + Android libraries.
- Android originally used **Apache Harmony** (open source), not Sun/Oracle JDK; since 2015 Android switched to **OpenJDK**.
- Since Android apps are built on Java, a JDK is required.
- The Android SDK is embedded in IDEs such as Android Studio, Eclipse, etc.

### Android Studio
- Official IDE for Android development, released 2013; current version **Narwhal (2025.1.1)**.
- Joint effort between JetBrains and Google, based on **IntelliJ IDEA**.
- Includes an Android Virtual Device (Emulator) for running/debugging.
- Gradle-based build support, modern UI design tools, intelligent code assistance, AI-powered features, integrated tools/services, and version control & collaboration support.

---

## 3. Kotlin Basics

### Kotlin vs. Java
Kotlin is a modern, statically-typed language that **fully interoperates with Java** — Java classes can invoke Kotlin methods/properties and use Kotlin classes, and vice versa.

### Kotlin's Components
Kotlin supports both **OOP and Functional Programming**, and shares the same basic building blocks as Java (with different syntax): variables, primitive types, text, control structures (`if`, `if-else`, `when`, `for`, `while`), math/logical operations, functions, classes, objects, and OOP components.

### Kotlin's Unique Features (not in Java)
Highlighted on the slide (title-only page); commonly cited Kotlin-specific features include null safety (`?`, `!!`, `?:`), data classes, extension functions, smart casts, coroutines, `when` expressions, default/named arguments, and concise lambda syntax.

### Self-Learning Resources
- [GeeksforGeeks Kotlin guide](https://www.geeksforgeeks.org/kotlin/kotlin-programming-language/)
- [Hyperskill Kotlin course](https://hyperskill.org/courses/18)
- [developer.android.com/kotlin](https://developer.android.com/kotlin) (Android + Kotlin)
- [kotlinlang.org](https://kotlinlang.org/docs/home.html) (official docs)
- Book: *Kickstart Modern Android Development With Jetpack and Kotlin* — available via Canvas → Reading List (VN)

---

## 4. Introduction to Jetpack Compose

Jetpack Compose is a **Kotlin-based toolkit for building modern Android UI**. It is Google's official **declarative UI framework**, designed to replace traditional XML layouts.

**Resources:** Android Basics with Jetpack Compose; same textbook as above.

---

## 5. Introduction to MVVM Architecture

**MVVM (Model-View-ViewModel)** is a software architectural pattern that organizes code in a modular, testable, and maintainable way — especially useful in Android with Jetpack Compose. It separates UI logic from business logic/data, making apps easier to scale and debug.

| Layer | Responsibility |
|---|---|
| **Model** | Data layer. Handles business logic and data sources (e.g. Room DB, network APIs). Should be UI-agnostic. |
| **View** | UI layer (Jetpack Compose or XML). Displays data, forwards user interactions to the ViewModel. Should be stateless and reactive. |
| **ViewModel** | Bridge between View and Model. Holds UI-related data, exposes it via `LiveData` or `StateFlow`. Survives configuration changes (e.g. screen rotation). |

### ViewModel (MVVM) vs. Controller (MVC)
Slide contrasts the two: in MVC the Controller directly manipulates the View and Model with tighter coupling, while in MVVM the ViewModel exposes observable state that the View binds/reacts to, without holding a direct reference to the View — improving testability and decoupling.

### Benefits of MVVM in Android
- Clear separation of UI and business logic
- Easier unit testing (ViewModel testable without UI)
- Survives configuration changes (state held in ViewModel)
- Works naturally with Compose's reactive/declarative model and `StateFlow`/`LiveData`

---

*End of Week 1 — Introduction to Android*
