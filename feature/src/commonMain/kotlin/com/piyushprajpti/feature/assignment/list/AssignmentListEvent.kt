package com.piyushprajpti.feature.assignment.list

sealed interface AssignmentListEvent {
    data object OnAddAssignment : AssignmentListEvent
    data class OnShareAssignment(val assignmentId: String) : AssignmentListEvent
    data class OnSubmissionsClick(val assignmentId: String) : AssignmentListEvent
}
