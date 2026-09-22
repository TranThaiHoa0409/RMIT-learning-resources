# MAD Week 9 – Pre-Class: Services and Lifecycle-Aware Components

## 1. Services and Lifecycle-Aware Components

Android's Services enable background work, and Lifecycle-Aware Components help manage resources by responding to app lifecycle events.

### Types of Services and Examples

Services are one of the four main Android components (along with Activities, BroadcastReceivers, and ContentProviders). Services enable apps to continue running even when the user switches to another app.

| Service Type | Description | Example |
|---|---|---|
| Foreground Service | Performs operations that are noticeable to the user and shows persistent notifications. | Google Maps app providing directions. |
| Background Service | Runs silently in the background (restricted in Android 8+). | Data syncing. |
| Bound Service | Allows components to bind and interact with it. | Fitness tracker updates live step count in UI. |

### WorkManager vs Foreground Service

| Component | Best For |
|---|---|
| WorkManager | Deferrable, guaranteed background work, even after app or device restarts. |
| Foreground Service | Real-time, user-visible work that must run immediately and show a persistent notification. |

In practice, the choice depends on company practices, product requirements, and Android OS restrictions.

### Lifecycle-Aware Components

Part of Android Jetpack, Lifecycle-Aware Components automatically respect lifecycle events (ViewModel, StateFlow, LifecycleObserver, etc.). The component lives as long as the dependee scope exists.

### Summary

- Services: run tasks in the background, even if the UI is gone.
- Foreground Services: best for ongoing user-visible tasks.
- Lifecycle-aware components: make apps safe and efficient.
