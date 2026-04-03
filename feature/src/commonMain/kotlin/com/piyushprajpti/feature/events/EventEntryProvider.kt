package com.piyushprajpti.feature.events

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.events.dashboard.EventDashboardScreen
import com.piyushprajpti.feature.events.detail.EventDetailScreen

fun eventEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.EventDashboard -> NavEntry(key) {
            EventDashboardScreen(
                onBackClick = { backStack.removeLast() },
                onEventClick = { backStack.add(NavRoutes.EventDetail) }
            )
        }

        is NavRoutes.EventDetail -> NavEntry(key) {
            EventDetailScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}