# Tutorial 5: Location-based and Maps Integration

**Objectives:** Build an Android app that integrates Google Maps and Location Services. You will:
- Display an interactive map using the Google Maps API.
- Customize the map with markers, zoom controls, and different map types.
- Request and handle runtime location permissions.
- Retrieve the device's current location using the Fused Location Provider.
- Implement periodic location updates and dynamically move the map camera.

This provides essential knowledge/skills for building location-aware Android applications.

---

## Preparation: Google Maps API

- Follow the lecture slides to enable the **Google Maps SDK** and get an **API Key** (Week 5 slide 10).
- Add the required dependencies for **Google Maps**, **Location Services**, and **Accompanist Permissions** in `build.gradle(:app)` (Week 5 slides 10, 16, 19).
- Display a Google Map and try the Maps key operations in Jetpack Compose (Week 5 slides 11–14):
  - Add a Marker, customize its appearance, and handle click events.
  - Change the map type.
  - Enable zoom controls.

## Preparation: Location-Based Services

- Follow the lecture slides to request location permissions (**FINE** and **COARSE**).
- Fetch the **last known location** (Week 5 slides 20–23).
- Implement **continuous location updates** (Week 5 slides 24–26) — change the location in the Emulator's extended controls settings to observe updates live.

---

## Exercise

Extend the tutorial app to display **nearby restaurants** retrieved from a RESTful web service.

1. Create a new Android project, or extend the current tutorial project.
2. Prepare a RESTful endpoint providing a list of nearby restaurants (with latitude and longitude).
   - Use JSON Server locally at `http://10.0.2.2:3004/restaurants`, or a hosted JSON service.
3. Use **Retrofit with Kotlin Coroutines** to retrieve the restaurant list from the server.
4. Display **markers** for all restaurants on the Google Map — each marker's title should show the restaurant's name.
5. Add a **`FloatingActionButton` (FAB)** to refresh the restaurant list and update the markers on the map.
6. Implement **`onMapClick`** to allow adding a new restaurant to the database:
   - When the user taps the map, show a dialog to enter the restaurant name.
   - Add the new restaurant (with latitude & longitude from the tap location) to the REST server using **POST**.
   - Update the marker list after a successful response.

---

### Key Skills Exercised
- Google Maps Compose integration (`GoogleMap`, `Marker`, `MarkerState`, `CameraPositionState`).
- Accompanist runtime permissions handling.
- Fused Location Provider — one-off and continuous location retrieval.
- Combining location data with a Retrofit-backed REST API (list + POST new entries).
- Map interaction handling (`onMapClick`) and dynamic marker updates.

---

*End of Tutorial 5 — Location-based and Maps Integration*
