# Project Architecture & Coding Standards - ERP Teacher (KMP)

This document serves as the definitive guide for the project architecture, coding standards, and best practices. Follow these rules strictly when adding new features or modifying existing ones.

## 1. Project Architecture (Clean Architecture + MVI)
The project follows a modular Clean Architecture approach with a focus on Kotlin Multiplatform (KMP).

- **`:core`**: Contains shared utilities, theme definitions, base components, networking logic (Ktor), and navigation routes.
- **`:domain`**: Contains business logic, entities (models), and repository interfaces. It must not depend on any other module except `:core`.
- **`:data`**: Contains repository implementations, data sources (Remote/Local), and DTOs.
- **`:feature`**: Contains UI logic. Each feature is a separate module or sub-folder.
- **`:composeApp`**: The main entry point that assembles all modules and handles top-level navigation.

### Screen Pattern
Every new screen must be implemented using four distinct files:
1. `[Name]Screen.kt`: The UI Composable. Keep it clean; move complex logic to the ViewModel.
2. `[Name]ViewModel.kt`: Handles state management, events, and business logic.
3. `[Name]State.kt`: A `data class` representing the immutable state of the screen.
4. `[Name]Event.kt`: A `sealed interface` representing user actions or intents.

## 2. Coding Standards & UI Rules

### UI & Layout
- **No Scaffold:** Do not use `androidx.compose.material3.Scaffold`. Use `Column` or `Box` as the root layout.
- **System Bars:** 
    - Use `statusBarsPadding()` on the root element.
    - Use `navigationBarsPadding()` for system navigation bar spacing.
    - **Floating Bottom Bar:** If a screen is a top-level destination with a bottom bar, use `LocalBottomBarPadding.current` within the `contentPadding` of scrollable containers (like `LazyColumn`) to avoid content being hidden behind the floating bar.
- **Scrollability:** Every screen must be scrollable. Use `LazyColumn` for lists or `Modifier.verticalScroll` for static layouts.
- **Padding:** Default screen/container padding is `15.dp`.
- **Corners:** Use consistent rounding, typically `RoundedCornerShape(15.dp)`.
- **Typography:** 
    - **DO NOT** use `MaterialTheme.typography` or `CustomTheme.typography`.
    - Define `fontSize`, `fontWeight`, and `color` directly in the `Text` composable.
- **Currency:** Always use the Rupee symbol (₹).

### Styling & Theme
- **Colors:** Use `CustomTheme.colors`. **NEVER** use hardcoded hex values (e.g., `Color(0xFF...)`) in UI code.
- **Theme Definition:** Any new color must be added to `CustomColorScheme.kt` and defined for both `LightColorScheme` and `DarkColorScheme`.
- **Components:** Reuse components from the `components` folder. Screen-specific components should live in a `components` sub-folder within the feature directory.

## 3. State & Logic Management

### ViewModel Best Practices
- **Helpers:** Every ViewModel must include `LoadingHelper` and `MessageHelper`.
- **State Collection:** Use `collectAsStateWithLifecycle()` in the Composable.
- **Navigation:** Do not handle navigation (e.g., `onBackClick`) inside the ViewModel. Pass lambdas to the Screen Composable.
- **Dummy Data:** Populate ViewModels with exhaustive dummy data for all possible states (loading, error, empty, success) during development. **Do not add artificial delays** (e.g., `delay(2000)`) for dummy data.

### Standard UI Helpers
Include these at the top of every main Screen Composable (after declarations, before layout):
```kotlin
LoadingWidget(viewModel.loadingHelper.state.value)
AlertDialog(viewModel.messageHelper)
ToastWidget(viewModel.messageHelper)
```

## 4. Dependencies & Frameworks
- **DI:** Koin for dependency injection.
- **Navigation:** Navigation3 (KMP-ready).
- **Networking:** Ktor.
- **Serialization:** Kotlinx Serialization for `NavRoutes` and API models.
- **Image Loading:** Coil (or the KMP equivalent used in the project).

## 5. Directory Structure Guidelines
- **Feature Modules:** `feature/[feature_name]/[sub_feature]/`
    - `root/`: Contains Screen, ViewModel, State, Event.
    - `components/`: Feature-specific UI components.
- **Core Components:** `core/src/commonMain/kotlin/com/piyushprajpti/components/`

## 6. Identified Mistakes to Avoid
- **Hardcoded Strings:** Use resources (if configured) or constants; avoid hardcoding labels in Composables.
- **Inconsistent Padding:** Ensure `navigationBarsPadding()` and `LocalBottomBarPadding` are used correctly to prevent UI overlaps.
- **State Over-exposure:** Only expose `StateFlow` from ViewModels, never `MutableStateFlow`.
- **Duplicate Logic:** If a component or logic is used in more than one place, move it to a shared location immediately.
