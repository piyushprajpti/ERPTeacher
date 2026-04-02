package com.piyushprajpti.feature.assignment.create

data class AssignmentCreateState(
    val title: String = "",
    val description: String = "",
    val dueDate: String = "",
    val dueTime: String = "",
    val maxMarks: String = "100",
    val allowedSubmissions: List<SubmissionType> = listOf(SubmissionType.PDF),
    val referenceMaterials: List<String> = emptyList(),
    val publishVisibility: PublishVisibility = PublishVisibility.NOW,
    val scheduleDate: String = "",
    val scheduleTime: String = ""
)

enum class SubmissionType {
    PDF, TEXT, IMAGE, LINK
}

enum class PublishVisibility {
    NOW, SCHEDULE
}