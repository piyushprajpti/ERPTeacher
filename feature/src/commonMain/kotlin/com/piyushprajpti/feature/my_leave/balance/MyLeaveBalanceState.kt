package com.piyushprajpti.feature.my_leave.balance

import androidx.compose.ui.graphics.Color

data class MyLeaveBalanceState(
    val leaveBalances: List<LeaveBalance> = emptyList(),
    val upcomingLeaves: List<UpcomingLeave> = emptyList(),
    val institutionalRules: InstitutionalRule? = null
)

data class LeaveBalance(
    val type: String,
    val consumed: Int,
    val total: Int,
    val color: Color
)

data class UpcomingLeave(
    val type: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val status: String,
    val color: Color
)

data class InstitutionalRule(
    val title: String,
    val description: String,
    val lastUpdated: String
)
