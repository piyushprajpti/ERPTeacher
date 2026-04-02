package com.piyushprajpti.feature.student_leave.dashboard

sealed interface StudentLeaveDashboardEvent {
    data class OnTabSelected(val tab: LeaveStatus) : StudentLeaveDashboardEvent
    data class OnApproveClick(val leaveId: String) : StudentLeaveDashboardEvent
    data class OnRejectClick(val leaveId: String) : StudentLeaveDashboardEvent
    data object OnMyLeavesClick : StudentLeaveDashboardEvent
}