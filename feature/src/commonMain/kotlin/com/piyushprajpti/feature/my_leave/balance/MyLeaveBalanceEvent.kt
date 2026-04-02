package com.piyushprajpti.feature.my_leave.balance

sealed interface MyLeaveBalanceEvent {
    data object OnApplyLeaveClick : MyLeaveBalanceEvent
    data object OnViewAllUpcomingClick : MyLeaveBalanceEvent
    data object OnViewFullPolicyClick : MyLeaveBalanceEvent
}
