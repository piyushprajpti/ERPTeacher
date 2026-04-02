package com.piyushprajpti.feature.leave.leave_apply

sealed interface MyLeaveApplyEvent {
    data class OnLeaveApplyTypeChange(val leaveType: String) : MyLeaveApplyEvent
    data class OnFromDateChange(val date: Long?) : MyLeaveApplyEvent
    data class OnToDateChange(val date: Long?) : MyLeaveApplyEvent
    data class OnReasonChange(val reason: String) : MyLeaveApplyEvent
    data object OnSubmit : MyLeaveApplyEvent
    data object OnBackClick : MyLeaveApplyEvent
}