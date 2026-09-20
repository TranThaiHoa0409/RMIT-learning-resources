# Hidden Gems — Full Project Handoff (Master, All Sessions Combined)

**Course:** COSC2657/COSC2543/COSC2729 — Assignment 1, Android App Development
**Deadline:** Friday, Aug 7, 2026, 23:59
**Student approach:** Solo project, strictly sequential/incremental workflow, communicates in Vietnamese, applies code changes directly rather than having Claude edit files.

This is the single, complete reference for the whole project — architecture, every feature, every bug fixed, every decision made, and what's left to do. Supersedes all earlier partial handoff docs.

---

## 1. What the app is

**Hidden Gems** — a tourism/culture discovery app for Ho Chi Minh City. Users browse, add, and share "hidden spots" (rooftop cafes, alleys, street murals) on a map-integrated feed. Satisfies all Assignment 1 requirements: ≥3 screens (has 6+), a meaningful model class, REST API integration, Maps/Location, and a local database.

---

## 2. Final technical configuration

- **Package:** `com.example.my_assignment_1`
- **App display name:** "Hidden Gems" (`strings.xml → app_name`; originally defaulted to "My_Assignment_1")
- **App icon:** custom (replaced the default Android Studio template icon via Image Asset Studio)
- **AGP:** 9.2.1, built-in Kotlin (no separate `kotlin-android` plugin); Kotlin 2.2.10; KSP 2.2.10-2.0.2
- **Compose BOM:** 2026.03.01, via `org.jetbrains.kotlin.plugin.compose` + `buildFeatures.compose = true`
- **SDK:** compileSdk/targetSdk 37 (bumped from 36 for androidx.core-ktx 1.19.0), minSdk 24
- **Backend:** MockAPI.io — base URL `https://6a6f84f955c0ce38c325a3a9.mockapi.io/api/v1/` — two resources: `spots` (title, category, description, address, latitude, longitude, imageUrl, ownerId, createdAt) and `users` (email, password, displayName, createdAt)
- **Network timeout:** explicit `OkHttpClient` in `RetrofitInstance.kt`, 30s connect/read/write (previously unset — could hang indefinitely on a bad connection)
- **Auth:** custom REST-based (not Firebase). Passwords are **SHA-256 hashed client-side** before being sent to/stored on the server — see §7 breaking-change note.
- **Navigation:** Top app bar (not bottom nav); Home is the start destination (browsing needs no login); Login/SignUp are guarded routes triggered only by tapping "Add spot" or "Profile"; after login, the app returns to the originally intended destination.
- **Release signing:** keystore `hiddengems` (stored outside the project directory, path known to the user), wired into `app/build.gradle.kts` via a manually-added `signingConfigs` block (the Android Studio "Generate Signed APK" wizard does **not** persist this on its own). Release SHA-1:
  ```
  B3:1F:4E:21:79:D4:D8:2A:75:07:5E:69:03:82:3F:F3:15:BE:FF:E5
  ```
  Must be registered on the Google Maps API key (Google Cloud Console → Credentials → Application restrictions → Android apps) alongside the pre-existing debug SHA-1, or the release APK's map renders blank.

---

## 3. Full source file structure

```
com/example/my_assignment_1/
├── MainActivity.kt
├── data/
│   ├── local/
│   │   ├── AppDatabase.kt              — Room singleton
│   │   ├── SessionManager.kt           — SharedPreferences: persists logged-in User across restarts, works fully offline
│   │   ├── SpotDao.kt                  — getAll, insertAll, replaceAll (transaction), update, deleteById
│   │   ├── SpotEntity.kt                — Room entity, mapped to/from Spot
│   │   └── ThemePreferenceManager.kt    — persists dark/light choice
│   ├── remote/
│   │   ├── ApiService.kt               — GET/POST/PUT/DELETE spots, GET/POST users
│   │   └── RetrofitInstance.kt         — Retrofit + kotlinx-serialization converter, 30s OkHttpClient timeout
│   └── repository/
│       ├── AuthRepository.kt           — login/signUp/getUserById; password hashing; friendly error messages
│       └── SpotRepository.kt           — cache-aside pattern (FromNetwork/FromCache/Empty), createSpot, updateSpot, deleteSpot, getSpotById (with cache fallback)
├── model/
│   ├── Spot.kt                          — meaningful model class #1
│   └── User.kt                          — meaningful model class #2
├── navigation/
│   ├── NavGraph.kt                      — all routes wired, guarded navigation for Add/Profile/Edit
│   └── Screen.kt                        — Home, Detail, Add, EditSpot, Profile, Login, SignUp routes
├── ui/
│   ├── add/
│   │   ├── AddSpotScreen.kt             — form UI; doubles as Edit form via editingSpotId param; category autocomplete combobox
│   │   └── AddSpotViewModel.kt          — geocoding, GPS location, category suggestions, edit-mode load/submit via Factory
│   ├── auth/
│   │   ├── AuthViewModel.kt             — login/signUp/restoreSession/logout state
│   │   ├── LoginScreen.kt               — Back button, email/password validation
│   │   └── SignUpScreen.kt              — email/password/confirm-password validation
│   ├── detail/
│   │   ├── DetailScreen.kt              — spot detail + Google Map + "Open in Maps app"; Edit/Delete icons for owner; delete confirm dialog
│   │   └── DetailViewModel.kt           — loads spot (network + cache fallback), deleteSpot()
│   ├── home/
│   │   ├── HomeScreen.kt                — feed, expandable search, category/distance filter chips, offline banner, FAB, CenterAlignedTopAppBar
│   │   └── HomeViewModel.kt             — DistanceFilter buckets, Vietnamese-normalized search, category list, distance-based sort
│   ├── profile/
│   │   ├── ProfileScreen.kt             — compact header row (avatar/name/email/theme toggle/logout) + Created Spots list (card-styled rows, inline edit/delete)
│   │   └── ProfileViewModel.kt          — loads/filters "my spots" by ownerId, delete with Room-backed offline cache
│   └── theme/
│       ├── Theme.kt                     — full wine-red/gold Material3 ColorScheme, hiddenGemsTopAppBarColors() helper
│       └── ThemeViewModel.kt            — exposes dark/light state to the nav graph
└── util/
    ├── VietnameseUtils.kt               — normalizeVietnamese(): NFD strip + đ/Đ handling, for diacritic-insensitive search
    ├── ErrorMessages.kt                 — friendlyErrorMessage(): never leak raw exceptions to the UI
    ├── PasswordHasher.kt                — hashPassword(): SHA-256
    └── Validation.kt                    — isValidEmail(), isValidPasswordLength()
```

Resources:
- `res/values/colors.xml` — `hidden_gems_primary_dark` (#5C1A2B), `hidden_gems_primary_light` (#B5804A)
- `res/values/themes.xml`, `res/values-night/themes.xml` — splash background + legacy theme primary colors match the brand palette
- `res/values/strings.xml` — `app_name = "Hidden Gems"`
- `res/mipmap*/ic_launcher*` — custom icon
- `AndroidManifest.xml` — `INTERNET`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION` permissions; Maps API key meta-data

---

## 4. Complete feature list

### Browsing (no login required)
- Home feed (`LazyColumn`, card-based: image/placeholder, title, category, address, description)
- Search by name/address, **Vietnamese diacritic-insensitive** ("pho co" matches "phố cổ") via `normalizeVietnamese()`
- Category filter — dropdown wraps to content width, not full screen
- Distance filter — `All` plus 5km buckets up to 45–50km; works both online and offline
  - Offline: locked to `<5km` only, other buckets disabled in the UI; with no known location, nothing is shown (rather than unverified cached spots)
  - Whenever location is known, the whole list sorts by distance ascending
- Search bar expands to full width on tap, hiding (not clearing) the category/distance chips
- Offline banner with an "enable location" shortcut

### Auth
- Sign up / log in, email + password
- **SHA-256 password hashing**, client-side, before any network call
- Email format + minimum-6-char password validation on both Login and SignUp
- Session persisted locally (`SessionManager`), works offline; background sync only logs out on a confirmed 404 (deleted account), never on a network error
- Login screen has a Back button
- Guarded navigation to Add/Profile, redirecting to Login → back to original destination after success

### Adding / editing spots
- Fields: title, category (autocomplete), description, address, optional image URL, location
- Category autocomplete: live-filtered suggestions from existing categories; on submit, case-insensitive match reuses the existing category's exact casing (prevents "Cafe"/"cafe" duplicates)
- Location: geocode typed address, or use current GPS (Fused Location Provider)
- Edit mode: same screen, pre-filled, PUT instead of POST, preserves original `createdAt`

### Ownership — Edit/Delete
- `Spot.ownerId == currentUser.id` gates visibility of Edit/Delete
- Detail screen: Edit + Delete icons on TopAppBar for the owner only; Delete requires a confirmation dialog
- Profile → Created Spots: numbered list of the user's own spots, card-styled rows (`surfaceVariant` bg + `outline` border), inline Edit/Delete icon buttons (32dp target / 16dp icon, same tint as title text — not red)
- Edit/Delete require network; offline attempts fail with a friendly message, never a crash

### Detail + Maps
- Full detail view, Google Map with a marker at the spot's coordinates (`MarkerState` wrapped in `remember(position)`)
- "Open in Maps app" deep-link button

### Profile
- Compact single-row header (avatar-letter, name, email, icon-only theme toggle, logout) to leave room for the spot list
- Created Spots section (see Ownership)

### Theming
- Full custom Material3 palette — dark mode wine red (#5C1A2B primary), light mode warm gold (#B5804A primary), derived card tint/border colors, brand-colored TopAppBars (white text/icons) via `hiddenGemsTopAppBarColors()`
- Dark/light toggle persisted
- Splash screen colors match the brand palette
- Standard Material3 red kept for `error`/`errorContainer` so the offline warning banner stays visually distinct from the brand

### Error handling
- `friendlyErrorMessage()` maps raw exceptions to short, non-technical strings; raw detail still `Log.e`'d
- Applied across `SpotRepository` and `AuthRepository`'s network-facing methods (deliberately *not* applied to `AuthRepository.getUserById()`, which needs the raw exception type to distinguish "network failure" from "account deleted" during session restore)
- `ProfileViewModel` now passes `spotDao` into its repository so "Created Spots" correctly falls back to cache offline

---

## 5. ⚠️ Breaking change: password hashing

Passwords are now SHA-256-hashed client-side. **Test accounts created before this change (stored as plain text on MockAPI) can no longer log in** — old test users were deleted from MockAPI; all accounts must be re-created via Sign Up going forward. Documented limitation: unsalted SHA-256, not a production-grade slow hash (bcrypt/scrypt/Argon2) — acceptable for assignment scope.

---

## 6. Complete bug log (both sessions)

| Bug | Cause | Fix |
|---|---|---|
| Kotlin sealed class NPE at startup | `by lazy` needed on companion `buckets` property — see below, same underlying class-init-order issue recurred twice | `by lazy` |
| Room KSP2 incompatibility | Room 2.6.1 too old for KSP2 | Upgraded to Room 2.8.4 |
| Offline network failure incorrectly cleared user session | Didn't distinguish `UserNotFoundException` from generic network errors | Catch/distinguish explicitly; cache full `User` JSON locally |
| Offline Detail screen failures | No cache fallback in `getSpotById()` | Added Room cache fallback |
| `JAVA_HOME` not set on Windows | Env var not configured | Set via PowerShell `[System.Environment]::SetEnvironmentVariable`; required a fresh terminal |
| Emulator "Waiting for all target devices..." | Stale ADB server state | `adb kill-server` / `adb start-server` via full platform-tools path, then Wipe Data + Cold Boot |
| App crashed instantly opening the distance filter dropdown | Class-initialization-order NPE: companion `val buckets` eagerly referenced a nested `data object All` before it was guaranteed initialized | Changed `buckets` to `by lazy { }` |
| Keyboard kept closing while typing category autocomplete | `DropdownMenu`/`ExposedDropdownMenu` opens a new focusable Popup on every re-render, stealing IME focus | `properties = PopupProperties(focusable = false)` |
| `ExposedDropdownMenu` / `MenuAnchorType` unresolved reference | This Compose BOM's Material3 version had renamed/altered that experimental API vs. public docs | Dropped `ExposedDropdownMenuBox` entirely; used plain `Box` + `OutlinedTextField` + `DropdownMenu` instead |
| Two category fields showing at once | Old `OutlinedTextField` wasn't deleted when the new combobox was added | Removed the duplicate |
| Maps `MarkerState` "without using remember" lint warning | Constructed inline every recomposition | Wrapped in `remember(position) { MarkerState(...) }` |
| Profile "Created spots" showed raw `Unable to resolve host "...": No address associated with hostname` | `ProfileViewModel`'s repository was built without `spotDao`, so there was no offline cache, and the fallback message was the raw exception text | Passed `spotDao` in; introduced `friendlyErrorMessage()` sitewide |
| `No parameter with name 'editingSpotId' found` | Nav-graph call to pass `editingSpotId` was applied before `AddSpotScreen`'s signature was actually updated to accept it (steps done out of order) | Confirmed current file state before re-applying the signature change |
| `Result.failure(Exception(friendlyErrorMessage(e)))` — "unresolved reference: e" | Pasted into the `try` block's `else` branch (no `e` in scope) instead of the `catch` block | Moved to the correct `catch` block |
| `gradlew signingReport` showed `Variant: release / Store: null` after a successful signed-APK build | The Studio "Generate Signed APK" wizard signs a one-off build but doesn't persist a `signingConfig` into Gradle | Manually added `signingConfigs { create("release") {...} }` + wired into `buildTypes.release.signingConfig` |

---

## 7. Deliberately not implemented / declined

- **Home feed cards getting inline Edit/Delete icons** and losing their image/description (an earlier draft of the color-system doc specified this) — explicitly reversed by the student ("giữ ownership như hiện tại, phần này là lỗi của tui khi prompt"). Home feed stays view-only for everyone; ownership-gated Edit/Delete remains exclusive to Detail (owner only) and Profile → Created Spots. The FAB + centered TopAppBar title parts of that same draft *were* implemented.
- **Offline write queue** for Edit/Delete — out of scope; documented as a known limitation.
- **Google/OAuth login** — custom email/password only; documented as a known limitation.
- **SignUpScreen Back button** — Login got one; SignUp was left open, not yet actioned.
- **Keystore password security** (`storePassword`/`keyPassword` currently plain text in `build.gradle.kts`) — acceptable for a one-off assignment submission; flagged only as a concern if the repo is ever made public later (fix: `keystore.properties` + `.gitignore`).

---

## 8. Remaining tasks before submission

1. Confirm the release SHA-1 has propagated on Google Cloud Console and the Map actually renders on the signed release APK on a real device.
2. Record the demo video (see companion file `Demo_Video_Script.md` — ~7 min, shot-by-shot, real device, ≤8 min, YouTube unlisted link).
3. Finish `Readme.txt` — Student ID and Student Name are still placeholders; everything else (features, tech stack, known limitations) is filled in; paste the video link once uploaded.
4. Full regression pass was completed and passed on the **debug** build (A–H checklist: auth, Home/search/filter, add spot, detail/maps, ownership edit/delete, profile, offline, dark/light) — not yet independently re-confirmed on the **signed release** build specifically (low risk here since `optimization { enable = false }` is set for release).
5. Package: release `.apk` + completed `Readme.txt` + full project source → one `.zip` (not `.rar`) → submit via Canvas before Aug 7, 23:59.

---

## 9. Key learnings (both sessions)

- AGP 9.2.1 bundles Kotlin natively — no `kotlin-android` plugin declaration needed; `android.disallowKotlinSourceSets=false` in `gradle.properties` resolves a KSP conflict.
- Retrofit's kotlinx-serialization converter import must be `com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory`, not the `retrofit2.converter...` path.
- Room 2.8.4+ is required for KSP2 compatibility.
- Distinguish network errors from auth errors in repository catch blocks to avoid incorrectly invalidating a cached session.
- Android Studio may auto-generate `values/themes.xml` / `values-night/themes.xml` with a project-specific theme name — edit the existing files rather than creating new ones, or Manifest references break.
- Splash theme must be applied to the `<activity>` tag specifically, not `<application>`.
- Environment variable changes don't propagate to already-open terminal sessions — always open a fresh terminal.
- Haversine/`Location.distanceBetween` gives bird's-eye distance, not driving distance — acknowledged, acceptable limitation for this use case.
- Mobile apps get Compose's built-in ripple for free on `clickable()`/`IconButton()` — no need to implement hover states.
- Compose `DropdownMenu`/`ExposedDropdownMenu` popups are focusable by default and will steal keyboard focus every time the popup's item count changes while a paired text field is being typed into — always pass `PopupProperties(focusable = false)` in that pairing.
- Sealed classes with nested `data object` subtypes referenced eagerly from the sealed class's own `companion object val` initializers risk a real, reproducible JVM class-initialization-order NPE — wrap such companion-level collections in `by lazy { }`.
- Never let a raw `Throwable.message` reach the UI — translate through a `friendlyErrorMessage()`-style boundary at the repository layer; log the raw detail via `Log.e` instead.
- The Android Studio "Generate Signed APK/Bundle" wizard signs a one-off build but does **not** persist signing config into the Gradle project — `./gradlew assembleRelease`/`signingReport` need `signingConfigs`/`buildTypes.release.signingConfig` added to `build.gradle.kts` by hand.
- Debug and release builds have different SHA-1 fingerprints — any SHA-1-restricted API key (Google Maps, etc.) needs the release fingerprint registered explicitly, or it silently fails only in release builds.
- A Windows keystore file created without an explicit extension is completely valid — Gradle only cares about the path, not the extension.

---

## 10. Tools & resources used throughout

- **IDE:** Android Studio on Windows; physical Samsung A53 (One UI, Vietnamese) as primary test device; emulator as secondary
- **Backend:** MockAPI.io (free, no self-hosting)
- **Key libraries:** Retrofit + Jake Wharton kotlinx-serialization converter, Room 2.8.4, Coil, Google Maps Compose, Accompanist Permissions, OkHttp, Kotlinx Serialization
- **Build tooling:** Gradle with `libs.versions.toml` version catalog; ADB via full platform-tools path
- **Course materials referenced:** `Assignment_1_Android_App.md`, MAD Week/Tutorial markdown files (Weeks 3–5 most relevant: state management, networking, location/maps)
