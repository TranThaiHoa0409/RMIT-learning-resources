# Tutorial 6: Persistent Storage

## Objectives

In this tutorial, you will build an Android app that manages a list of currencies using Room Database, Preferences DataStore, and Hilt. You will:

- Create a local SQLite Room database with Entity, DAO, and Database classes
- Build a ViewModel that interacts with the DAO using Kotlin coroutines
- Display a dynamic list of currencies using LazyColumn
- Add functionality to insert, delete, and update currencies via the UI
- Persist user preference for sorting order using Preferences DataStore
- Implement a searching by country mechanism

---

## Preparation: Room Database

- Follow the lecture slides to understand how to use Room with Compose
- Add the required dependencies for the Room library (Slide 8)
- Create the Room `Entity`, `Dao`, and `AppDatabase` classes as shown in the slides (Slides 9–11)
- Build a `CurrencyViewModel` class to interact with the database using coroutines
- Create a Compose UI that:
  - Displays a list of currencies using state observed from the ViewModel
  - Allows adding a new currency using a form (e.g., country name and currency name)
  - Provides delete functionality for each currency

## Preparation: Preferences DataStore and Hilt

- Review Slide 14 for setting up Preferences DataStore
- Add the required dependency for DataStore in `build.gradle(:app)`
- Create a `PreferencesManager` class to store and read UI preferences (dark mode, font size) (Slide 15)
- Use `collectAsState()` to observe preference changes in your UI
- Review Slides 20–23 for integrating Hilt into your project

---

## Exercise

In this exercise, you will extend the current app to provide a complete currency management application.

### 1. Implement the following features

- Each currency should be shown in a **Card** with its country and currency
- **Update Currency:** Allow tapping a card to edit the currency name via a dialog and update the database
- **Search Functionality:** Add a TextField to filter currencies by country name
- **Sort Order Preference:** Add a toggle or dropdown to sort currencies alphabetically (A–Z or Z–A). Store the selected sort order using Preferences DataStore

### 2. (Extra) Firebase Integration

- Configure Firebase and sync the currency list to the cloud using Firebase Realtime Database or Firestore
- When a new currency is added, also post it to Firebase
- When the app is first loaded, pull the data from Firebase to the local Room database
