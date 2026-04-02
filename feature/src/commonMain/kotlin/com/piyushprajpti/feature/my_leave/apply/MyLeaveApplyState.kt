package com.piyushprajpti.feature.leave.leave_apply

data class MyLeaveApplyState(
    val isLoading: Boolean = false,
    val leaveTypes: List<String> = listOf("Sick", "Casual", "Emergency", "Medical", "Academic"),
    val selectedLeaveType: String = "Sick",
    val fromDate: Long? = null,
    val toDate: Long? = null,
    val totalDays: Int = 0,
    val reason: String = "",
    val attachmentName: String? = null
)