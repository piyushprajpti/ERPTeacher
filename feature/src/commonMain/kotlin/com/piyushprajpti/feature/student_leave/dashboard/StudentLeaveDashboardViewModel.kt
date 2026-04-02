package com.piyushprajpti.feature.student_leave.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class StudentLeaveDashboardViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val allLeaves = listOf(
        LeaveRequest(
            id = "1",
            studentName = "Arjun Mehta",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS42",
            semester = "SEM 4",
            leaveType = "Sick Leave",
            startDate = "Mar 10",
            endDate = "Mar 12",
            duration = "3 Days",
            reason = "Recovering from viral fever. Doctor advised complete bed rest for 3 days.",
            attachment = Attachment("Medical_Certificate.pdf", "245 KB", "PDF Document"),
            isNew = true,
            status = LeaveStatus.PENDING
        ),
        LeaveRequest(
            id = "2",
            studentName = "Priya Sharma",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS15",
            semester = "SEM 4",
            leaveType = "Personal Leave",
            startDate = "Mar 15",
            endDate = "Mar 15",
            duration = "1 Day",
            reason = "Attending a family function in my hometown.",
            status = LeaveStatus.PENDING
        ),
        LeaveRequest(
            id = "3",
            studentName = "Rahul Verma",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS22",
            semester = "SEM 4",
            leaveType = "Event Participation",
            startDate = "Mar 12",
            endDate = "Mar 14",
            duration = "3 Days",
            reason = "Participating in Inter-College Tech Fest.",
            attachment = Attachment("Event_Invite.pdf", "1.2 MB", "PDF Document"),
            status = LeaveStatus.PENDING
        ),
        LeaveRequest(
            id = "4",
            studentName = "Ananya Iyer",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS08",
            semester = "SEM 4",
            leaveType = "Sick Leave",
            startDate = "Mar 05",
            endDate = "Mar 06",
            duration = "2 Days",
            reason = "Sudden severe migraine and high fever.",
            status = LeaveStatus.APPROVED
        ),
        LeaveRequest(
            id = "5",
            studentName = "Kabir Singh",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS30",
            semester = "SEM 4",
            leaveType = "Other",
            startDate = "Mar 01",
            endDate = "Mar 01",
            duration = "1 Day",
            reason = "Passport renewal appointment at the regional office.",
            status = LeaveStatus.REJECTED
        ),
        LeaveRequest(
            id = "6",
            studentName = "Sneha Kapur",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS55",
            semester = "SEM 4",
            leaveType = "Emergency",
            startDate = "Mar 18",
            endDate = "Mar 20",
            duration = "3 Days",
            reason = "Family emergency at home requiring my presence.",
            isNew = true,
            status = LeaveStatus.PENDING
        ),
        LeaveRequest(
            id = "7",
            studentName = "Vikram Aditya",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS11",
            semester = "SEM 4",
            leaveType = "Sick Leave",
            startDate = "Mar 11",
            endDate = "Mar 11",
            duration = "1 Day",
            reason = "Food poisoning after eating outside food.",
            status = LeaveStatus.PENDING
        ),
        LeaveRequest(
            id = "8",
            studentName = "Meera Nair",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS19",
            semester = "SEM 4",
            leaveType = "Personal Leave",
            startDate = "Mar 22",
            endDate = "Mar 23",
            duration = "2 Days",
            reason = "Brother's wedding ceremony.",
            attachment = Attachment("Wedding_Card.jpg", "800 KB", "Image"),
            isNew = true,
            status = LeaveStatus.PENDING
        ),
        LeaveRequest(
            id = "9",
            studentName = "Ishaan Malhotra",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS27",
            semester = "SEM 4",
            leaveType = "Medical Leave",
            startDate = "Feb 25",
            endDate = "Mar 02",
            duration = "6 Days",
            reason = "Recovering from minor surgery.",
            attachment = Attachment("Discharge_Summary.pdf", "1.5 MB", "PDF Document"),
            status = LeaveStatus.APPROVED
        ),
        LeaveRequest(
            id = "10",
            studentName = "Zara Khan",
            studentImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
            rollNo = "2024CS03",
            semester = "SEM 4",
            leaveType = "Sick Leave",
            startDate = "Mar 08",
            endDate = "Mar 09",
            duration = "2 Days",
            reason = "Severe throat infection.",
            status = LeaveStatus.REJECTED
        )
    )

    private val _state = MutableStateFlow(StudentLeaveDashboardState())
    val state = _state.onStart {
        updateLeaves()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = StudentLeaveDashboardState()
    )

    fun onEvent(event: StudentLeaveDashboardEvent) {
        when (event) {
            is StudentLeaveDashboardEvent.OnTabSelected -> {
                _state.update { it.copy(selectedTab = event.tab) }
                updateLeaves()
            }
            is StudentLeaveDashboardEvent.OnApproveClick -> {
                approveLeave(event.leaveId)
            }
            is StudentLeaveDashboardEvent.OnRejectClick -> {
                rejectLeave(event.leaveId)
            }
            StudentLeaveDashboardEvent.OnMyLeavesClick -> {
                messageHelper.showMessage("Navigating to My Leaves...")
            }
        }
    }

    private fun updateLeaves() {
        val currentTab = _state.value.selectedTab
        val filteredLeaves = allLeaves.filter { it.status == currentTab }
        val pendingCount = allLeaves.count { it.status == LeaveStatus.PENDING }
        _state.update { it.copy(leaves = filteredLeaves, pendingCount = pendingCount) }
    }

    private fun approveLeave(id: String) {
        messageHelper.showMessage("Leave Approved for $id")
    }

    private fun rejectLeave(id: String) {
        messageHelper.showMessage("Leave Rejected for $id")
    }
}