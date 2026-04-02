package com.piyushprajpti.college_erp_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    setSystemBarsColor: (Boolean) -> Unit = {},
    content: @Composable () -> Unit
) {
    val customColorScheme = if (darkTheme) CustomDarkColorScheme else CustomLightColorScheme

    SideEffect {
        setSystemBarsColor(darkTheme)
    }

    CompositionLocalProvider(LocalCustomColorScheme provides customColorScheme) {
        MaterialTheme(
            typography = getTypography(),
            content = content
        )
    }
}

object CustomTheme {
    val colors: CustomColorScheme
        @Composable
        get() = LocalCustomColorScheme.current
}
