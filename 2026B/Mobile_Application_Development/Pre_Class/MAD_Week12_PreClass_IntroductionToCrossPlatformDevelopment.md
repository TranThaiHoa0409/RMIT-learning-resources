# MAD Week 12 – Pre-Class: Introduction to Cross-platform Development

## 1. Introduction to Cross-platform Development

Companies want apps to run on different platforms to maximise profit, which is why developers often use cross-platform development.

### Native vs. Cross-Platform Development

| Approach | Description | Pros | Cons |
|---|---|---|---|
| Native Development | Apps are built specifically for one platform (Android, iOS), using the platform's official language and SDK (Android: Kotlin/Java; iOS: Swift/Objective-C). | Best performance, full access to hardware APIs, consistent user experience. | Separate codebases for each platform, requiring more time and resources. |
| Cross-Platform Development | Apps are built with a shared codebase that runs on multiple platforms; frameworks handle rendering UI and bridging to native APIs. | Faster development, single team, reduced costs. | May not perfectly match native look-and-feel; sometimes less optimised for performance. |

### Popular Cross-Platform Frameworks

| Framework | Language | Approach | Strengths | Limitations |
|---|---|---|---|---|
| Flutter (Google) | Dart | Renders its own widgets for consistent UI | Fast dev (hot reload), strong ecosystem | UI may feel less "native" |
| React Native (Meta) | JavaScript | Bridges JS to native components | Large community, reuses web dev skills | Performance drops on complex UIs |
| Kotlin Multiplatform (JetBrains) | Kotlin | Share business logic, keep native UI | Good for Android teams, Kotlin synergy | Still evolving, less UI abstraction |
| .NET MAUI (Microsoft) | C# | Unified framework for Android/iOS/Windows/macOS | Strong enterprise fit, MS ecosystem | Smaller mobile dev community |
