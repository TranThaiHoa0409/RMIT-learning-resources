# MAD Week 8 – Pre-Class: Background Processing and System Communication

## 1. Background Processing and System Communication

Apps often need to work behind the scenes: syncing data, sending alerts, and responding to system events.

### Why Background Processing Is Needed

- Long-running tasks: file downloads, data sync, image processing.
- Smooth user experience: avoiding blocking of the main thread, to keep the UI responsive.
- Reacting to system events: battery low, connectivity changes, and new data arrival.

Modern Android versions (8 and 10 onwards) have strict limits on background activity to save battery and improve performance.

### Examples of Apps That Use Background Processing

| App | Background Processing Use |
|---|---|
| Google Maps | Continuous GPS tracking, live traffic updates, and rerouting. |
| Grab | Tracks the driver's location, updates the ride status, and processes payments in the background. |
| Messenger | Listens for incoming messages, syncs chats, and sends delivery/read receipts. |
| Spotify | Streams audio, downloads songs, and syncs playlists in real-time. |
| Dropbox | Auto-syncs files, backups, and photos. |

### Components for System Communication

There are four components:

| Component | Description | Example |
|---|---|---|
| BroadcastReceiver | Listens for system or app broadcasts (e.g., battery low, Wi-Fi connected). | A banking app listens for a "transaction completed" broadcast to update the balance and send a notification. |
| Scoped Storage | Secure, permission-based file access — no direct access without explicit user consent. | A photo editing app requests access to the gallery. |
| WorkManager | For deferrable, guaranteed background work — runs even if the app is killed or the device restarts. | The Grab app continues searching for a driver in the background, even if the passenger force-closes the app. |
| Notifications | Alert users outside of the main UI and keep them engaged. | A news app sends a breaking news alert directly to the user's lock screen. |
