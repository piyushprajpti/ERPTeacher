package com.piyushprajpti.feature.assignment

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.assignment.create.AssignmentCreateScreen
import com.piyushprajpti.feature.assignment.list.AssignmentListScreen
import com.piyushprajpti.feature.assignment.submission.AssignmentSubmissionScreen

fun assignmentEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.AssignmentList -> NavEntry(key) {
            AssignmentListScreen(
                onBackClick = { backStack.removeLast() },
                onSubmissionClick = { backStack.add(NavRoutes.AssignmentSubmission) },
                onCreateClick = { backStack.add(NavRoutes.AssignmentCreate) }
            )
        }

        is NavRoutes.AssignmentSubmission -> NavEntry(key) {
            AssignmentSubmissionScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        is NavRoutes.AssignmentCreate -> NavEntry(key) {
            AssignmentCreateScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}