# ERP Student Project Developer Guidelines

## Architecture & Structure
- **Screen Pattern:** Every screen must consist of four files: `[Name]Screen.kt`, `[Name]ViewModel.kt`, `[Name]State.kt`, and `[Name]Event.kt`.
- **Clean Code:** Keep the main `Screen.kt` file clean. Move UI components into a `components` sub-folder within the feature module (e.g., `feature/home/components/`).
- **Reusable Components & No Duplication:** Try to create reusable components. If a component is used in 2 screens, then create it in the `components` folder under `piyushprajpti` and use it. You will be creating screens one by one and you will create the screen-specific components in that screen folder, but if in the future that is used in some other screen also, then move that in the top-level `components` folder and use it. Keep in mind that in the whole project, everything must have only one copy; duplicate files and functions must not be created.
- **ViewModel Data:** Always populate ViewModels with extensive dummy data that covers all possible states and edge cases for testing. Create more dummy data additional to the present in image covering all features and cases of the screen.
- **Navigation Back Handling:** Do not handle `onBackClick` within the ViewModel. Pass an `onBackClick: () -> Unit` lambda directly into the Screen Composable to handle navigation at the caller level.

## UI & Styling Standards
- **Theme:** Always use `CustomTheme.colors` from `com.piyushprajpti.college_erp_system.theme`.
- **Colors:** 
    - **NEVER use hardcoded `Color(0xFF...)` values.**
    - If a new color is required, define it in `CustomColorScheme.kt` and then use it via `CustomTheme.colors`.
    - Every new color added to the theme MUST have a suitable value for both light and dark themes.
    - **Use image for design reference only. Use colors defined in the `CustomColorScheme` and use `CustomTheme` for coloring.**
- **TopBar:** Always use the project's custom `TopBar` component.
- **No Scaffold:** Do not use the material `Scaffold` component. Use custom layouts (Column/Box) with proper system bar padding.
- **Full Screen Effect:** 
    - Always add `statusBarsPadding()` to the root screen element.
    - **Navigation Bar Padding:** Always use `navigationBarsPadding()` modifier. **DO NOT use `LocalBottomBarPadding.current`.**
    - Add navigation bar padding to the `contentPadding` of scrollable containers (LazyColumn/verticalScroll) or to the root layout to ensure content is not hidden behind system bars.
- **Scrollability:** Every page must be scrollable (usually via `LazyColumn` or `Modifier.verticalScroll`).
- **Padding:** Use a default corner/screen padding of `15.dp`.
- **Typography:** 
    - Keep font sizes professional and normal. Avoid excessively large text. 
    - **NEVER use `MaterialTheme.typography` or `CustomTheme.typography` styles anywhere.**
    - There is no typography defined in the theme. Always use direct properties like `fontSize`, `fontWeight`, and `color` within the `Text` composable.
- **Rounding:** Use consistent corner rounding (e.g., `RoundedCornerShape(15.dp)` or as defined in the theme).
- **Currency:** Always use the Rupee symbol (₹) for currency values.

## Functional Design & Logic
- **Functional Implementation:** Do not just replicate the reference image literally. Use common sense to create a functional screen.
- **Dynamic Filtering:** If a screen has filters, the data displayed must dynamically match the selected filter based on logic in the ViewModel.
- **Real-world Logic:** Reference images are for design inspiration; the actual implementation should behave like a real, logical application (e.g., empty states, proper sorting, active interactions).

## State Management
- **Lifecycle:** Use `collectAsStateWithLifecycle()` to collect state in Composables.
- **Template Helpers:** Always include the standard project helpers. **Place them at the top of the Composable, after all variable declarations but BEFORE the start of the UI layout (e.g., before the main Column or Row).**
    - `LoadingWidget(viewModel.loadingHelper.state.value)`
    - `AlertDialog(viewModel.messageHelper)`
    - `ToastWidget(viewModel.messageHelper)`
- **Loading State:** When populating dummy data or during initial load in ViewModels, **DO NOT add dummy loading delays** (e.g., `delay(2000)`). The data should be available immediately unless a real network call is being simulated/made.

## Dummy Data Assets
- **Profile Image:** `https://cdn-icons-png.flaticon.com/512/15297/15297661.png`
- **QR Code:** `https://img.freepik.com/premium-vector/modern-square-sample-black-qr-code-icon-white-background_131186-1524.jpg`
- **Student ID Photo:** `https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png`
- **Book Covers:**
    - `https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg` (Clean Code)
    - `https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg` (The Pragmatic Programmer)
    - `https://m.media-amazon.com/images/I/41T5H8u7fUL._SX331_BO1,204,203,200_.jpg` (Refactoring)

## Best Practices
- Use `sealed interface` for Events.
- Use `data class` for State with default values.
- Use `Koin` for ViewModel injection.
- **Code Quality:** All code must follow industry standards, be scalable, bug-proof, and error-proof. Optimization is mandatory to ensure app performance is not compromised. Code must be highly readable and easily understandable.
