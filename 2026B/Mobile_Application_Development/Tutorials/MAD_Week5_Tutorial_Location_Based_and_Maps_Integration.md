# Tutorial 5: Location-based and Maps Integration

## Objectives

In this tutorial, you will be creating an Android app that integrates Google Maps and Location Services. You will:

- Display an interactive map using the **Google Maps API**.
- Customize the map with markers, zoom controls, and different map types.
- Request and handle **runtime location permissions** in your app.
- Retrieve the device's **current location** using the **Fused Location Provider**.
- Implement **periodic location updates** and dynamically move the map camera.

This tutorial will provide you with essential knowledge and skills to build **location-aware applications** in Android.

## Preparation: Google Maps API

- Follow the lecture slides to enable Google Maps SDK and Get an API Key (Slide 10)
- Add the required dependencies for Google Maps, Location Services, and Accompanist Permissions in `build.gradle(:app)` (Slide 10, 16, 19)
- Display a Google Map and try the Maps key operations in Jetpack Compose (Slide 11 – 14)
  - Add a Marker, Customize Marker Appearance, and Handle Click Events
  - Change Map Type
  - Enable Zoom Controls

## Preparation: Location-Based Services

- Follow the lecture slides to request location permissions (FINE and COARSE)
- Fetch Last Known Location (Slide 20-23)
- Continuous Location Updates (Slide 24-26) – Change the location in the Emulator Settings to see the updates

## Exercise

In this exercise, you will extend the app you built in this tutorial to display nearby restaurants retrieved from a RESTful web service.

1. **Create a new Android project** or extend the current tutorial project
2. **Prepare a RESTful endpoint** that provides a list of nearby restaurants (with latitude and longitude)
   - Use **JSON Server** locally at `http://10.0.2.2:3004/restaurants` or a hosted JSON service
3. **Use Retrofit** with Kotlin Coroutines to retrieve the list of restaurants from the server
4. **Display markers for all restaurants** on the Google Map
   - Each marker should show the restaurant's **name** as the title
5. **Add a FloatingActionButton (FAB)** to refresh the restaurant list and update the markers on the map
6. **Implement `onMapClick`** to allow adding a **new restaurant** to the database:
   - When the user taps on the map, show a dialog to **enter the restaurant name**
   - Add the new restaurant (with latitude & longitude from the click) to the REST server using **POST**
   - Update the marker list after a successful response
