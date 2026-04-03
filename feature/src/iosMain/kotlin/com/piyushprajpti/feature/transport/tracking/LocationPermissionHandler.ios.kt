package com.piyushprajpti.feature.transport.tracking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
actual fun LocationPermissionHandler(onResult: (Boolean) -> Unit) {
    LaunchedEffect(Unit) {
        // iOS implementation would use CoreLocation
        // For now, we'll just return true to not block the UI if not implemented
        onResult(true)
    }
}
