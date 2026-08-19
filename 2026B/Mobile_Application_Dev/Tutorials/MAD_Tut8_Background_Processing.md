# Tutorial 8: Background Processing

## Objectives

In this tutorial, you will build an Android app where user actions and system events are logged to scoped storage, summarized periodically via WorkManager, and delivered to the user via Notifications. The Compose UI will show the log and lets users trigger custom broadcasts.

---

## Preparation: Project Setup

- Add the required dependencies for the WorkManager library
- Add `POST_NOTIFICATIONS` permission to `AndroidManifest.xml`
- Declare a `<receiver>` for `SystemEventReceiver` with the `BATTERY_LOW` action inside `<application>`

> **Note:** Runtime receivers will be registered programmatically.

---

## Data Layer – Event Log with Scoped Storage

To persist the log of our events, we will use scoped storage (private app-specific external directory).

1. Create an `Event` data class with: `timestamp`, `source` (custom/system/worker), `type`, and `message`
2. Implement `EventLogRepository` with methods to:
   - Append events to `event_log.csv`
   - Read and parse all events for display
   - Count recent events for summaries
3. Place file in `context.getExternalFilesDir(null)` (scoped storage → no extra permission)

---

## Notifications Helper

We will use notifications to connect background events with the user. We build a helper to create a channel and show notifications.

- Create `Notify` singleton object with a `CHANNEL_ID`
- Add `ensureChannel(context)` to register the channel (API 26+)
- Add `show(context, title, text)` to build and post notifications with a `PendingIntent` to open `MainActivity`
- In `MainActivity.onCreate`, call `Notify.ensureChannel(this)`
- Request notification permission for API 33+

---

## Broadcast Receivers — Custom & System Events

We will use broadcast receivers to listen to both custom and system events.

### Custom Broadcasts

1. Create `CustomEventReceiver` extending `BroadcastReceiver`
2. Define actions: `ACTION_HAPPY` and `ACTION_SAD`
3. In `onReceive`, log to repository and call `Notify.show()`
4. Register/unregister receiver dynamically in `MainActivity`

### System Broadcasts

1. Create `SystemEventReceiver` for the `BATTERY_LOW` action
2. In `onReceive`, log event and show notification
3. Register in manifest so it fires even when the app isn't running

---

## Background Work — WorkManager Summary

We will use WorkManager to run periodic jobs, even if the app is closed.

- Create `SummaryWorker` class extending `Worker`
- In `doWork()`, read the log and count events from the last hour
- If events exist, send a summary notification
- Schedule periodic work every 15 minutes using `PeriodicWorkRequestBuilder` with constraints (not low battery)
- Enqueue it in `MainActivity`

---

## UI - Compose Screen

The UI provides controls for custom broadcasts and a live view of the log of events.

- Set up a Composable with a `Scaffold` and a top bar
- Add three buttons: "I'm Happy", "I'm Sad", and "Refresh"
- On button click, send the appropriate broadcast and refresh log state
- Use `LazyColumn` to display all events with timestamp, type, source, and message
- Ensure UI reloads log entries after broadcasts

---

## Application Flow

1. User presses a button → custom broadcast → logged to file → notification shown
2. Emulator simulates low battery → system broadcast → logged → notification shown
3. WorkManager wakes → counts recent events → summary notification shown
4. UI reads log file → displays all past events
