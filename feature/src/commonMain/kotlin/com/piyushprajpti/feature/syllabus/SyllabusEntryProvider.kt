package com.piyushprajpti.feature.syllabus

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.syllabus.coverage.SyllabusCoverageScreen
import com.piyushprajpti.feature.syllabus.planner.SyllabusPlannerScreen

fun syllabusEntryProvider (
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.SyllabusCoverage -> NavEntry(key) {
            SyllabusCoverageScreen(
                onBackClick = { backStack.removeLast() },
                onPlannerClick = { backStack.add(NavRoutes.SyllabusPlanner) }
            )
        }

        is NavRoutes.SyllabusPlanner -> NavEntry(key) {
            SyllabusPlannerScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}