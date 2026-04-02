package com.piyushprajpti.feature.attendance.dashboard

sealed interface AttendanceDashboardEvent {
    data class OnClassClick(val classItem: ClassItem) : AttendanceDashboardEvent
    data object OnBackClick : AttendanceDashboardEvent
}