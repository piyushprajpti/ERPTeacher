package com.piyushprajpti.feature.student_leave.dashboard

data class StudentLeaveDashboardState(
    val selectedTab: LeaveStatus = LeaveStatus.PENDING,
    val pendingCount: Int = 0,
    val leaves: List<LeaveRequest> = emptyList()
)

data class LeaveRequest(
    val id: String,
    val studentName: String,
    val studentImage: String,
    val rollNo: String,
    val semester: String,
    val leaveType: String,
    val startDate: String,
    val endDate: String,
    val duration: String,
    val reason: String,
    val attachment: Attachment? = null,
    val isNew: Boolean = false,
    val status: LeaveStatus = LeaveStatus.PENDING
)

data class Attachment(
    val fileName: String,
    val fileSize: String,
    val fileType: String
)

enum class LeaveStatus {
    PENDING, APPROVED, REJECTED
}