package com.piyushprajpti.feature.schedule

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes

fun scheduleEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.Schedule -> NavEntry(key) {
            ScheduleScreen()
        }

        else -> null
    }
}