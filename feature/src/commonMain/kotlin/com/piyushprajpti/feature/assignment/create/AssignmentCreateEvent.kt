package com.piyushprajpti.feature.assignment.create

sealed interface AssignmentCreateEvent {
    data class OnTitleChange(val title: String) : AssignmentCreateEvent
    data class OnDescriptionChange(val description: String) : AssignmentCreateEvent
    data class OnDueDateChange(val date: String) : AssignmentCreateEvent
    data class OnDueTimeChange(val time: String) : AssignmentCreateEvent
    data class OnMaxMarksChange(val marks: String) : AssignmentCreateEvent
    data class OnSubmissionTypeToggle(val type: SubmissionType) : AssignmentCreateEvent
    object OnAddReferenceMaterial : AssignmentCreateEvent
    data class OnPublishVisibilityChange(val visibility: PublishVisibility) : AssignmentCreateEvent
    object OnCreateAssignment : AssignmentCreateEvent
}