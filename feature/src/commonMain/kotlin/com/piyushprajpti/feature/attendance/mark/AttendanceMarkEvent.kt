package com.piyushprajpti.feature.attendance.mark

sealed interface AttendanceMarkEvent {
    data class OnStatusChange(val studentId: String, val status: AttendanceStatus) : AttendanceMarkEvent
    data object OnSubmitClick : AttendanceMarkEvent
    data object OnSaveDraftClick : AttendanceMarkEvent
    data object OnFilterClick : AttendanceMarkEvent
    data object OnBackClick : AttendanceMarkEvent
}