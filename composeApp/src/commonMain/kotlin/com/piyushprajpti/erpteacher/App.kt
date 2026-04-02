package com.piyushprajpti.erpteacher

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.piyushprajpti.college_erp_system.theme.AppTheme
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import org.koin.compose.viewmodel.koinViewModel
import com.piyushprajpti.feature.settings.root.AppTheme as Theme

@Composable
fun App(
    setSystemBarsColor: (Boolean) -> Unit = {}
) {
    val viewModel: AppViewModel = koinViewModel()
    val authState by viewModel.authState.collectAsState()
    val theme by viewModel.theme.collectAsState()

    val darkTheme = when (theme) {
        Theme.LIGHT -> false
        Theme.DARK -> true
        Theme.DEVICE_DEFAULT -> isSystemInDarkTheme()
    }

    AppTheme(
        darkTheme = darkTheme,
        setSystemBarsColor = setSystemBarsColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomTheme.colors.primaryBackground),
            contentAlignment = Alignment.Center
        ) {
            Navigation(authState)
        }
    }
}
