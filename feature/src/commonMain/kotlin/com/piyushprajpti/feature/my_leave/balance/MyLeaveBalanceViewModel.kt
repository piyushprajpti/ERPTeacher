package com.piyushprajpti.feature.my_leave.balance

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyLeaveBalanceViewModel : ViewModel() {
    private val _state = MutableStateFlow(MyLeaveBalanceState())
    val state = _state.asStateFlow()

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        _state.value = MyLeaveBalanceState(
            leaveBalances = listOf(
                LeaveBalance("Earned Leave", 12, 20, Color(0xFFE16349)),
                LeaveBalance("Sick Leave", 8, 10, Color(0xFF43A047)),
                LeaveBalance("Casual Leave", 5, 5, Color(0xFFFB8C00)),
                LeaveBalance("Maternity/Paternity Leave", 0, 90, Color(0xFF9C27B0)),
                LeaveBalance("Study Leave", 15, 30, Color(0xFF2196F3))
            ),
            upcomingLeaves = listOf(
                UpcomingLeave(
                    type = "Earned",
                    title = "Summer Break",
                    startDate = "June 15",
                    endDate = "June 20, 2024",
                    status = "Approved by Principal",
                    color = Color(0xFFE16349)
                ),
                UpcomingLeave(
                    type = "Sick",
                    title = "Dental Appointment",
                    startDate = "July 02",
                    endDate = "July 02, 2024",
                    status = "Approved by HOD",
                    color = Color(0xFF43A047)
                ),
                UpcomingLeave(
                    type = "Casual",
                    title = "Family Function",
                    startDate = "August 10",
                    endDate = "August 12, 2024",
                    status = "Pending Approval",
                    color = Color(0xFFFB8C00)
                )
            ),
            institutionalRules = InstitutionalRule(
                title = "Standard Leave Policy",
                description = "Leaves must be applied at least 48 hours in advance for planned absences. Sick leaves require medical certificates if exceeding 3 consecutive days.",
                lastUpdated = "Jan 2024"
            )
        )
    }

    fun onEvent(event: MyLeaveBalanceEvent) {
        when (event) {
            MyLeaveBalanceEvent.OnApplyLeaveClick -> {
                // Handle navigation or logic
            }
            MyLeaveBalanceEvent.OnViewAllUpcomingClick -> {
                // Handle navigation
            }
            MyLeaveBalanceEvent.OnViewFullPolicyClick -> {
                // Handle navigation
            }
        }
    }
}
