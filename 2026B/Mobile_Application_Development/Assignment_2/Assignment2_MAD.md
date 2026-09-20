# Assignment 2

**Due:** Sep 11 by 23:59
**Points:** 100
**Submitting:** File upload (.zip)
**File Types:** zip
**Available:** Jun 22 at 6:00 – Sep 16 at 23:59

## Assessment Overview

| Field | Detail |
|---|---|
| Assessment name | Assignment 2 |
| Length | Code submission |
| Type | Group Project |
| Feedback mode | Feedback will be available via the LMS |
| Late work | A penalty of 10% (of the marks awarded) per day will apply. If more than 5 days late, a penalty of 100% will apply. Weekend days (Saturday and Sunday) are counted when counting total late days |
| Deadline | Friday Sep 11, 2026 |

## Learning Objectives Assessed

1. Describe and compare different mobile application models/architectures and patterns.
2. Apply mobile application models/architectures and patterns to the development of a mobile software application.
3. Describe the components and structure of a mobile development framework (Google's Android Studio).
4. Apply a mobile development framework to the development of a mobile application.
5. Demonstrate advanced Java programming competency by developing a maintainable and efficient cloud based mobile application.
6. Address the limitations and challenges of working in a mobile environment and thus utilise the opportunities for commercial and/or social benefit.

**Ready for Life and Work:** Prepare students to work in software development projects that use Android.

## Assessment Details

Choose one of the following four project options.

### Option 1: Fleet Vehicle Maintenance & Inspection Tracker

**Context**

Phuong Hai JSC operates a logistics and transport fleet for company operations. Vehicle breakdowns caused by missed regular maintenance or unreported defects cause operational delays. The company requires a mobile-first solution for drivers and fleet managers to track vehicle health, record pre-trip inspections, and manage service logs.

**Project Overview**

Students will build an Android application for fleet maintenance tracking. Drivers can complete digital pre-trip checklists, scan vehicle identification codes, attach photo evidence of vehicle damage, record GPS coordinates on interactive maps, and log fuel intake. The app includes offline storage, background sync, system event receivers, and an admin portal for fleet managers.

Reference document: *Fleet Vehicle Maintenance & Inspection Tracker.docx*

### Option 2: Smart Facility & Equipment Booking App

**Context**

Phuong Hai JSC operates multiple office sites containing shared meeting rooms, testing equipment, and specialized laboratories. Conflicts over room bookings and untracked equipment check-outs frequently occur. The company wants a localized mobile application to manage facility schedules and validate equipment pick-ups.

**Project Overview**

Students will build an Android app for booking facilities and tracking shared equipment. The app utilizes geofencing/GPS validation to verify that an employee is physically present on-site before unlocking a session. It uses foreground services for ongoing check-in timers, QR scanning for room verification, offline caching, and administrative report generation.

Reference document: *Smart Facility & Equipment Booking App.docx*

### Option 3: Asset Delivery & Proof-of-Delivery (PoD) App

**Context**

Phuong Hai JSC delivers specialized engineering equipment and materials directly to client construction sites. Delivery drivers often face challenges such as disputes regarding delivery times, damaged goods upon arrival, or missing signatures. A robust mobile application is required to track deliveries and gather digitally verifiable Proof of Delivery (PoD).

**Project Overview**

Students will build an Android delivery management app. Drivers can view assigned delivery stops on Google Maps, scan package shipping labels, capture recipient signatures, upload photo proof, run active delivery routes via Foreground Services, and support offline delivery sync. A dispatch manager portal provides real-time monitoring.

Reference document: *Asset Delivery & Proof-of-Delivery (PoD) App.docx*

### Option 4: On-Site Safety Audit & Hazard Reporting App

**Context**

Phuong Hai JSC must strictly adhere to occupational health and safety standards across its warehouses and client project sites. Currently, safety audits and hazard reports are recorded on paper, leading to delays in mitigating safety risks and lost documentation. The company wants an Android app to allow immediate hazard reporting and regular site safety audits.

**Project Overview**

Students will build an Android app focused on workplace safety compliance. Inspectors can record safety incidents with photo proof, detect motion impacts using hardware sensors, tag locations on Google Maps, run ongoing audit routines with Foreground Services, visualize hazard analytics, and manage sync states.

Reference document: *On-Site Safety Audit & Hazard Reporting App.docx*

## Architecture & Dependency Injection

- **Architecture:** Must follow MVVM and Clean Architecture principles using Jetpack Compose and Navigation Component.
- **Dependency Injection:** Must use Hilt to manage dependencies across ViewModels, Repositories, Database instances, and Network clients.

## Additional Features (Applies to All Projects)

The core features listed are provided by the company — these are the basic requirements for the app. Teams are also encouraged to come up with useful or creative features on their own to make the app more interesting and practical. Each team must include at least one meaningful creative feature (ML integration preferred if applicable), as this will count toward the creativeness section in the grading rubric.

## Testing Requirements (Applies to All Projects)

To ensure code quality and practical skill development, each team must include basic testing as part of their project deliverables:

- **Unit Testing:** At least 1-2 meaningful unit tests that cover business logic, validation, or data transformations (e.g., form validation, retry rules, or parsing logic).
- **UI Testing:** At least 1 Jetpack Compose UI test or Espresso test for verifying core UI workflows (e.g., form submission, list rendering, navigation flow).

**Test Coverage Expectations**

- You are not required to cover every feature.
- Focus on testing logic that is prone to failure or core to the app's functionality.
- **Test Location:** All tests should be located in proper test directories (`src/test` or `src/androidTest`) and runnable within Android Studio.

This will be assessed as part of the project evaluation rubric. Basic test coverage helps demonstrate good engineering practice, even in small teams.

## GitHub Classroom

https://classroom.github.com/a/LzWup6s-

## Plagiarism

Your code will be checked against plagiarism using a software called JPlag. If plagiarism is detected, both submissions will receive ZERO marks.

## Support Resources

Additional library and learning resources are available to help with the assessment in this course.

- Library services
- Program Tutors
- Learning Advisors

## Submission Instructions

- Zip your project and submit the .zip file to Canvas. Rar and other forms of compression are not allowed.
- The submission must contain an apk file.
- Each submission MUST include a `Readme.txt` file with the following details: Student Id, Student Name, functionality of the app (write down all features that your app has), technology used, and any unimplemented required features.
- A demo using an actual Android device is required for this assignment (12 mins maximum). Please include the link to your video in your submission (YouTube, etc.)

## Rubric — Android Assignment 2

| Criteria | Description | Points |
|---|---|---|
| App usefulness & creativeness | The app should directly address the industry partner use case. Evaluation is based on how well the app solves the problem. Creativity in applying the use case in a practical way or additional features is rewarded. | 40 |
| App completion & functionality | The app should be ready to demo with core features working. Bugs and crashes will reduce marks. | 30 |
| Non-functionality (UI/UX, quality, professionalism) | Focuses on usability, design quality, accessibility, professional UI, and whether the app has been tested and validated. | 20 |
| Demo | Teams must demo their app live. Must highlight your Unique Selling Point (USP) — an additional feature, innovation, or creative idea beyond the industry requirements. | 10 |
| **Total** | | **100** |
