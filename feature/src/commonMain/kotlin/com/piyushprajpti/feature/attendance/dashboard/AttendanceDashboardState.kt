package com.piyushprajpti.feature.attendance.dashboard

data class AttendanceDashboardState(
    val isLoading: Boolean = false,
    val activeSession: ClassItem? = null,
    val todayClasses: List<ClassItem> = emptyList(),
    val currentDate: String = ""
)

data class ClassItem(
    val id: String,
    val subject: String,
    val year: String,
    val section: String,
    val startTime: String,
    val endTime: String,
    val room: String,
    val status: ClassStatus,
    val studentsEnrolled: Int? = null
)

enum class ClassStatus {
    COMPLETED, ONGOING, UPCOMING
}