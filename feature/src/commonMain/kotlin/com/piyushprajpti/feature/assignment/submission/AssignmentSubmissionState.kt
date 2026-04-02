package com.piyushprajpti.feature.assignment.submission

data class AssignmentSubmissionState(
    val subject: String = "",
    val unit: String = "",
    val title: String = "",
    val dueDate: String = "",
    val totalStudents: Int = 0,
    val submittedCount: Int = 0,
    val pendingCount: Int = 0,
    val lateCount: Int = 0,
    val selectedFilter: SubmissionFilter = SubmissionFilter.All,
    val submissions: List<Submission> = emptyList(),
    val filteredSubmissions: List<Submission> = emptyList()
)

data class Submission(
    val id: String,
    val studentName: String,
    val rollNo: String,
    val submittedAt: String? = null,
    val score: Int? = null,
    val totalScore: Int = 100,
    val isLate: Boolean = false,
    val hasAttachment: Boolean = false,
    val attachmentType: AttachmentType = AttachmentType.Document
)

enum class SubmissionFilter {
    All, Submitted, Graded, Late
}

enum class AttachmentType {
    Document, Image
}
