package com.piyushprajpti.feature.hostel.complaints.models

data class Complaint(
    val id: String,
    val title: String,
    val category: String,
    val date: String,
    val status: ComplaintStatus,
    val statusText: String,
    val rating: Int? = null,
    val progress: Float = 0f // 0 to 1
)

enum class ComplaintStatus {
    SUBMITTED, ASSIGNED, IN_PROGRESS, RESOLVED
}
