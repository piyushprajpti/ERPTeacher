package com.piyushprajpti.feature.assignment.submission

sealed interface AssignmentSubmissionEvent {
    data class OnFilterChange(val filter: SubmissionFilter) : AssignmentSubmissionEvent
    data object OnGradeAllClick : AssignmentSubmissionEvent
    data class OnSubmissionClick(val submissionId: String) : AssignmentSubmissionEvent
}
