package com.piyushprajpti.feature.transport

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.transport.dashboard.TransportDashboardScreen
import com.piyushprajpti.feature.transport.tracking.TransportTrackingScreen

fun transportEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.TransportDashboard -> NavEntry(key) {
            TransportDashboardScreen(
                onBackClick = { backStack.removeLast() },
                onTrackingClick = { backStack.add(NavRoutes.TransportTracking) }
            )
        }

        is NavRoutes.TransportTracking -> NavEntry(key) {
            TransportTrackingScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}