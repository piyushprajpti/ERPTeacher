package com.piyushprajpti.feature.home

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.home.notifications.NotificationScreen
import com.piyushprajpti.feature.home.root.HomeScreen

fun homeEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.Home -> NavEntry(key) {
            HomeScreen(
                onNotificationClick = { backStack.add(NavRoutes.Notifications) },
                onQuickActionClick = { quickAction -> backStack.add(quickAction.route) }
            )
        }

        is NavRoutes.Notifications -> NavEntry(key) {
            NotificationScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}