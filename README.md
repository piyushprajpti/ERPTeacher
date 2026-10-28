# 👨‍🏫 ERPTeacher - Modern College ERP Faculty Mobile Application

> A modern, enterprise-grade, cross-platform mobile ERP client engineered for college faculty and university professors. Built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform (CMP)** using **Clean Architecture** and **MVI (Model-View-Intent)** pattern, targeting both **Android** and **iOS** from a single shared codebase.

---

## 🚀 Live App Download & Demo Credentials

Recruiters and reviewers can test the application directly using the pre-built APK:

![Download APK](https://drive.google.com/file/d/1zw5ykuEcBl-BI_-KiFgpGjkKWDX0cylw/view?usp=sharing)

### 🔑 Demo Login Credentials

| Field | Value |
| :--- | :--- |
| **Institution / Tenant** | Select **`Raffles University`** from the institution dropdown |
| **Faculty Email** | `user@example.com` |
| **Password** | `pass@123` |

> 💡 *Note: The companion student app is also available at [ERPStudent](https://github.com/piyushprajpti/ERPStudent).*

---

## 📱 Key Highlights & Capabilities

### 🏢 1. Multi-Tenant Authentication & Session Management
- **Institution Selection:** Multi-tenant architecture allowing faculty to select their respective college/university tenant before logging in.
- **Secure Authentication:** JWT-based login lifecycle with automated token refresh (`refresh-token`), session persistence via DataStore Preferences, and self-service password recovery / OTP verification.

### 📋 2. Student Attendance Management
- **Attendance Dashboard:** Overview of allocated classes, course codes, and historical lecture logs.
- **Mark Attendance:** Intuitive student roster interface supporting quick one-tap toggle for Present, Absent, and Late states with batch submission.

### 📝 3. Assignment Lifecycle & Grading
- **Create Assignments:** Publish new assignments with course selection, title, instructions, maximum marks, deadline picker, and attachment uploads.
- **Submission Review:** Inspect student submissions, download student work, and award grades with personalized feedback.

### 🗓️ 4. Teaching Timetable & Schedule
- **Faculty Schedule:** Dynamic calendar interface displaying daily lectures, classroom locations, batch details, and free periods.
- **Scrollable Date Selector:** Rapid day-by-day navigation for planning upcoming lecture sessions.

### ✈️ 5. Leave Management (Faculty & Student Approval)
- **Student Leave Approvals:** Centralized dashboard for reviewing, approving, or rejecting student leave requests with remarks.
- **My Leave (Faculty):** Track personal leave quotas (Casual, Medical, Earned), apply for leave with date range selectors, and check approval status.

### 📖 6. Syllabus Planner & Coverage Tracking
- **Curriculum Planner:** Semester-wise syllabus structure organized into units and topics.
- **Coverage Tracker:** Real-time percentage tracking of completed vs. pending syllabus for each assigned subject.

### 📢 7. Campus Notice Broadcasting
- **Notice Creation:** Draft and broadcast department and institution announcements directly to students with priority flags and tags.

### 📊 8. Examination & Results Publishing
- **Invigilation Duties:** View upcoming exam schedules, hall assignments, and exam duty timetables.
- **Marks & Result Entry:** Digital entry portal for internal assessments and semester examination grades.

### 🚌 9. Campus Transport & Live GPS Bus Tracking
- **Bus Route Details:** Route maps, driver contacts, and designated campus stops.
- **Live GPS Map Tracking:** Platform-native interactive maps using **Google Maps Compose** (Android) and platform-specific view bridging (`expect`/`actual`) for live bus location tracking and ETA calculations.

### 📚 10. Digital Library Access
- **Catalog Search:** Real-time book search by title, author, ISBN, and availability.
- **Borrowed Books & Dues:** Track issued faculty copies, renewal dates, and department reference material.

### 🏡 11. Hostel & Residential Administration
- **Hostel Overview:** Stay details, student room allotments, and warden contact directories.
- **Gate Pass Review:** Approve or inspect digital student gate passes with QR-code verification.
- **Mess & Complaints:** Weekly mess menu inspection, food rating feedback, and hostel maintenance ticket tracking.

### 🎨 12. Custom Dual-Theme System (Light / Dark Mode)
- Full support for Light, Dark, and System Default themes.
- Zero hardcoded colors; strict use of centralized theme tokens (`CustomTheme.colors`).
- Edge-to-edge system bar handling with `statusBarsPadding` and `navigationBarsPadding`.

---

## 🛠️ Technical Architecture & Engineering Decisions

ERPTeacher is engineered following Clean Architecture and MVI principles:

```
┌────────────────────────────────────────────────────────┐
│                      :composeApp                       │
│     (Android & iOS Entry Points, Dependency Root)      │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│                       :feature                         │
│  UI Screens, MVI ViewModels, Compose Multiplatform     │
└───────────┬────────────────────────────────┬───────────┘
            │                                │
┌───────────▼───────────┐        ┌───────────▼───────────┐
│        :domain        │        │         :core         │
│ UseCases, Domain Model│        │ Design System, Routes │
└───────────▲───────────┘        │ HTTP Utils, Theme     │
            │                    └───────────▲───────────┘
┌───────────┴───────────┐                    │
│         :data         ├────────────────────┘
│ Room KMP, Ktor Client │
│ Repositories, DAOs    │
└───────────────────────┘
```

### 1. Unidirectional Data Flow (MVI)
Every feature screen adheres to a strict four-file separation:
- **`[Feature]Screen.kt`**: Clean declarative UI composables observing immutable state.
- **`[Feature]ViewModel.kt`**: Business logic handler and state holder with `LoadingHelper` and `MessageHelper`.
- **`[Feature]State.kt`**: Immutable data class capturing the entire UI state.
- **`[Feature]Event.kt`**: Sealed interface defining all user actions/intents.

### 2. Multi-Module Separation
- **`:composeApp`**: Application orchestrator, Android `Application` class, iOS `UIViewController` factory, and root dependency graph.
- **`:feature`**: Modular feature packages containing Compose Multiplatform UI components, ViewModels, and navigation entry providers.
- **`:domain`**: Pure Kotlin business layer containing Models, Repository Interfaces, and Use Cases with zero Android/UI dependencies.
- **`:data`**: Data layer containing Ktor API clients, Room Database, DAOs, Entity mappers, and Repository implementations.
- **`:core`**: Common utilities, HTTP error handling (`Result<T>`), Design System (`CustomColorScheme`), DataStore factory, and type-safe navigation definitions.

### 3. JetBrains Navigation 3
Utilizes JetBrains Navigation 3 (`androidx.navigation3`) with type-safe route serializability (`@Serializable`), backstack management, and isolated entry providers per feature module.

### 4. Robust Offline Caching & Networking
- **AndroidX Room (KMP):** SQLite bundled driver providing local database persistence across Kotlin Multiplatform targets.
- **Ktor 3.x Client:** Fully configured with JSON serialization, HTTP timeout controls, logging, and automated JWT token refresh interceptors.
- **DataStore Preferences:** Multiplatform key-value persistence for authentication tokens and user settings.

---

## 🧰 Technology Stack & Libraries

| Category | Technology |
| :--- | :--- |
| **Language** | [Kotlin 2.3.20](https://kotlinlang.org/) |
| **UI Framework** | [Compose Multiplatform 1.10.3](https://www.jetbrains.com/lp/compose-multiplatform/) (Material 3) |
| **Targets** | Android (minSdk 26, targetSdk 36) & iOS (Arm64, Simulator) |
| **Architecture** | Clean Architecture + MVI (Model-View-Intent) |
| **Dependency Injection** | [Koin 4.2.1](https://insert-koin.io/) (`koin-compose-viewmodel`, `koin-android`) |
| **Networking** | [Ktor 3.5.0](https://ktor.io/) (OkHttp on Android, Darwin on iOS, ContentNegotiation) |
| **Serialization** | `kotlinx.serialization` 1.11.0 |
| **Local Database** | [AndroidX Room 2.8.4](https://developer.android.com/kotlin/multiplatform/room) with SQLite Bundled Driver |
| **Local Key-Value** | AndroidX DataStore Preferences 1.2.1 |
| **Asynchronous Engine** | Kotlin Coroutines 1.11.0 & Kotlin Flow |
| **Image Loading** | [Coil 3.5.0](https://coil-kt.github.io/coil/) (Multiplatform + Ktor 3 engine) |
| **Navigation** | [JetBrains Navigation 3](https://github.com/JetBrains/compose-multiplatform) |
| **Maps & Location** | Google Maps Compose 8.3.0 with multiplatform `expect`/`actual` |
| **Logging** | [Kermit 2.1.0](https://kermit.touchlab.co/) by Touchlab |
| **Date & Time** | `kotlinx-datetime` 0.7.1 |

---

## 📂 Project Directory Structure

```
ERPTeacher/
├── composeApp/                     # App entry point, Application class & iOS MainViewController
│   ├── src/androidMain/            # Android manifest, Activity, Room factory, App initialization
│   ├── src/commonMain/             # App composable, Navigation router, Theme wrapper
│   └── src/iosMain/                # iOS ViewController and platform initializers
├── core/                           # Core foundation module
│   └── src/commonMain/kotlin/
│       ├── constants/              # API endpoints, constants, and storage keys
│       ├── datastore/              # DataStore preference factory
│       ├── http/                   # Result wrapper, HTTP response helper, error codes
│       ├── navigation/             # Type-safe Serializable NavRoutes
│       └── theme/                  # Design system, CustomColorScheme, light/dark themes
├── data/                           # Data layer module
│   └── src/commonMain/kotlin/
│       ├── local/                  # Room Database, DAOs, Entities, Type Converters
│       ├── remote/                 # Ktor API services, Request & Response DTOs
│       └── repository/             # Repository implementations (Auth, User, Common)
├── domain/                         # Domain layer module (Pure Kotlin)
│   └── src/commonMain/kotlin/
│       ├── model/                  # Domain business entities (User, Tenant)
│       ├── repository/             # Abstract Repository interfaces
│       └── usecase/                # Single-purpose business use cases
├── feature/                        # Feature presentation module
│   └── src/commonMain/kotlin/
│       ├── assignment/             # Assignment creation, listing, and submission review
│       ├── attendance/             # Attendance dashboard & student marking roster
│       ├── auth/                   # Tenant selection & faculty login
│       ├── common/                 # Password recovery, OTP verification, support
│       ├── documents/              # Faculty documents & certificate requests
│       ├── events/                 # College events & extracurricular details
│       ├── exam/                   # Examination schedule & invigilation duties
│       ├── home/                   # Faculty dashboard, daily schedule, quick actions
│       ├── hostel/                 # Stay details, gate pass review, mess, complaints
│       ├── my_leave/               # Faculty leave balance & leave application
│       ├── notice/                 # Notice creation & broadcasting portal
│       ├── result/                 # Student marks entry & grading
│       ├── schedule/               # Teaching timetable & lecture schedule
│       ├── settings/               # Profile management & dual-theme switcher
│       ├── student_leave/          # Student leave approval dashboard
│       ├── syllabus/               # Syllabus planner & coverage tracking
│       └── transport/              # Route details & live GPS bus tracking
└── iosApp/                         # iOS Xcode project entry point (SwiftUI wrapper)
```

---

## 💻 Building and Running the Project

### Prerequisites
- **JDK:** OpenJDK 17 or 21
- **Android Studio:** Android Studio Ladybug (or newer) with Compose Multiplatform plugin
- **Xcode:** Xcode 15+ (for iOS compilation, macOS only)
- **CocoaPods / Swift Package Manager:** Configured for iOS dependencies

### Android Build
Build the debug APK directly via Gradle:

```bash
# On macOS / Linux
./gradlew :composeApp:assembleDebug

# On Windows (PowerShell)
.\gradlew.bat :composeApp:assembleDebug
```

The output APK will be generated at:
`composeApp/build/outputs/apk/debug/erp_teacher.apk`

### iOS Build
1. Open the `/iosApp` directory in Xcode.
2. Select an iOS Simulator (e.g., iPhone 15 / 16) or a connected physical iOS device.
3. Build and run using `Product > Run` (`Cmd + R`).

---

## 👨‍💻 Developer & Contact

**Piyush Prajapati**  
- **GitHub:** [@piyushprajpti](https://github.com/piyushprajpti)  
- **Email:** [eng.piyushp@gmail.com](mailto:eng.piyushp@gmail.com)  
- **Repository:** [ERPTeacher on GitHub](https://github.com/piyushprajpti/ERPTeacher)  
- **Companion Project:** [ERPStudent on GitHub](https://github.com/piyushprajpti/ERPStudent)  

---

<p align="center">
  <i>Developed with ❤️ using Kotlin Multiplatform & Compose Multiplatform</i>
</p>