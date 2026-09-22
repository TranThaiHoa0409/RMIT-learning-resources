# MAD Week 11 – Pre-Class: Testing Android Applications

## 1. Testing Android Applications

Building features is only half the job; making sure they work correctly is the other half. Testing helps catch bugs early, improve app quality, and save time in the long run.

### Why Testing Is Necessary

- Prevents bugs before they reach users
- Ensures business logic works as expected
- Verifies that the user interface behaves correctly
- Increases confidence when adding new features or refactoring code

### Types of Testing in Android

| Testing Type | Description | Tools | Example |
|---|---|---|---|
| Unit Testing | Tests business logic in isolation (the most modular level: functions, classes). | JUnit – for writing assertions (check if expected = actual); Mockito – for mocking dependencies (simulate behaviour without real objects) | Testing a `calculateDiscount()` function without needing the whole checkout UI. |
| UI Testing | Tests how the user interface responds (buttons, text, navigation). | Espresso – for View-based UI | Simulate tapping through a shopping app, verifying that "Add to Cart" and "Checkout" buttons work. |
| Testing with Jetpack Compose | Compose apps require a slightly different approach. | Compose Testing APIs – for verifying UI elements in Compose-based apps | Check if a Compose button with text "Login" actually appears and triggers navigation when clicked. |
