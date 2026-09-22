# Tutorial 9: Services and Lifecycle Components

## Objectives

In this tutorial, you will build a **simulated music player app** that demonstrates:

- Playing music in the background using a **Foreground Service**
- Controlling playback (Play/Pause/Stop) with a **Bound Service**
- Managing UI state with **ViewModel + StateFlow**
- Using a **media-style notification** with playback controls
- Download a song from URL using **WorkManager**

## Part 1 - Setup Requirements

- Add these permissions in `AndroidManifest.xml`:
  - `POST_NOTIFICATIONS`
  - `FOREGROUND_SERVICE_MEDIA_PLAYBACK`
  - `INTERNET`
- Declare the service:
  - `MusicPlayerService` with `foregroundServiceType="mediaPlayback"`

## Part 2 - MusicPlayerService Requirements

- Create a service `MusicPlayerService` that can:
  - Start as a **Foreground Service** with a persistent notification.
  - Act as a **Bound Service** so Activities/UIs can call methods.
  - Maintain a boolean state `isPlaying`.
- Add methods:
  - `play()` -> starts simulated playback loop
  - `pause()` -> pauses playback
  - `stop()` -> stops playback and terminates service
- Ensure the service updates the notification content (Playing vs Paused)
- Add **MediaStyle notification** with play/pause actions

## Part 3 - PlayerViewModel Requirements

- Create a `PlayerViewModel` that:
  - Holds the state of whether music is playing
  - Uses `MutableStateFlow` and exposes `StateFlow`
  - Has a method `updateState(isPlaying: Boolean)`

## Part 4 - MainActivity & UI Requirements

- Bind to `MusicPlayerService` using `ServiceConnection`
- Start service in `onStart()` with `ContextCompat.startForegroundService()`
- Unbind service in `onStop()`
- UI:
  - Show playback state (Now Playing/ Paused)
  - Buttons: **Play**, **Pause**, **Stop**
  - Each button:
    - Calls service method (`play()`, `pause()`, `stop()`)
    - Updates ViewModel state

## Part 5 – Exercises

1. Extend the service with `nextTrack()` and `previousTrack()` methods, and add UI buttons
2. Enhance the notification with **MediaStyle actions** for play/pause/stop
3. Sync notification button actions with UI state using ViewModel
4. Add track name to ViewModel and display it in the UI and notification
5. Add an option to **download a song from a URL** using WorkManager, and show progress in the notification
