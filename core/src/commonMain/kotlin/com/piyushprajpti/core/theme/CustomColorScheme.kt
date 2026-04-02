package com.piyushprajpti.college_erp_system.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColorScheme(
    val primary: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val tertiaryText: Color,
    val outline: Color,
    val error: Color,
    val success: Color,
    val warning: Color,
    val info: Color,
    val white: Color,
    val black: Color,
    val orange: Color,
    val blue: Color,
    val purple: Color,
)

val LocalCustomColorScheme = staticCompositionLocalOf<CustomColorScheme> {
    error("No custom color provided")
}
val DarkColorScheme = CustomColorScheme(
    primary = Color(0xFFE16349),
    primaryBackground = Color(0xFF000000),
    secondaryBackground = Color(0xFF121212),
    primaryText = Color(0xFFF5F5F5),
    secondaryText = Color(0xFFB0B0B0),
    tertiaryText = Color(0xFF757575),
    outline = Color(0xFF2C2C2C),
    error = Color(0xFFE53935),
    success = Color(0xFF43A047),
    warning = Color(0xFFFB8C00),
    info = Color(0xFF1E88E5),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    orange = Color(0xFFFF9800),
    blue = Color(0xFF2196F3),
    purple = Color(0xFF9C27B0),
)

val LightColorScheme = CustomColorScheme(
    primary = Color(0xFFE16349),
    primaryBackground = Color(0xFFFFFFFF),
    secondaryBackground = Color(0xFFF6F8FA),
    primaryText = Color(0xFF24292F),
    secondaryText = Color(0xFF57606A),
    tertiaryText = Color(0xFF6E7781),
    outline = Color(0xFFD0D7DE),
    error = Color(0xFFE53935),
    success = Color(0xFF43A047),
    warning = Color(0xFFFB8C00),
    info = Color(0xFF1E88E5),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    orange = Color(0xFFFF9800),
    blue = Color(0xFF2196F3),
    purple = Color(0xFF9C27B0),
)

val CustomDarkColorScheme = DarkColorScheme
val CustomLightColorScheme = LightColorScheme
