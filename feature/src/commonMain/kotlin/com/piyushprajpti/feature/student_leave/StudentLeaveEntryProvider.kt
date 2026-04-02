package com.piyushprajpti.feature.student_leave

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.student_leave.dashboard.StudentLeaveDashboardScreen

fun studentLeaveEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.StudentLeaveDashboard -> NavEntry(key) {
            StudentLeaveDashboardScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}