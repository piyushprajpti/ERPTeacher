package com.piyushprajpti.feature.attendance

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.attendance.dashboard.AttendanceDashboardScreen
import com.piyushprajpti.feature.attendance.mark.AttendanceMarkScreen

fun attendanceEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.AttendanceDashboard -> NavEntry(key) {
            AttendanceDashboardScreen(
                onBackClick = { backStack.removeLast() },
                onCardClick = {backStack.add(NavRoutes.AttendanceMark) }
            )
        }

        is NavRoutes.AttendanceMark -> NavEntry(key) {
            AttendanceMarkScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}