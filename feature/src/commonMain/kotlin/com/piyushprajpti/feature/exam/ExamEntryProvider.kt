package com.piyushprajpti.feature.exam

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes

fun examEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.ExamSchedule -> NavEntry(key) {
            ExamScheduleScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}