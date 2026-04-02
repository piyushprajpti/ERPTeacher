package com.piyushprajpti.feature.home.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.onStart {
        loadHomeData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeState()
    )

    private fun loadHomeData() {
        _state.update {
            it.copy(
                userName = "Arjun Mehta",
                department = "CS Dept",
                currentDate = "Monday, Oct 23",
                pendingTasks = listOf(
                    PendingTask("Ungraded Assignments", 12, PendingTaskType.UNGRADED_ASSIGNMENTS),
                    PendingTask("Leave Applications", 4, PendingTaskType.LEAVE_APPLICATIONS)
                ),
                todaySchedule = listOf(
                    TeacherScheduleItem(
                        subject = "Data Structures - Sec A",
                        section = "Sec A",
                        time = "10:30 AM",
                        room = "Room 402",
                        totalStudents = 60,
                        isAttendanceMarked = false,
                        studentAvatars = listOf(
                            "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png",
                            "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png",
                            "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png"
                        )
                    ),
                    TeacherScheduleItem(
                        subject = "Algorithms - Sec C",
                        section = "Sec C",
                        time = "02:00 PM",
                        room = "Lab 2",
                        totalStudents = 55,
                        isAttendanceMarked = true
                    )
                ),
                classesSummary = ClassesSummary(
                    activeClasses = 4,
                    subjects = 2,
                    totalStudents = 180
                )
            )
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnNotificationClick -> {
                // Handled in UI
            }
            is HomeEvent.OnQuickActionClick -> {
                // Handled in UI
            }
        }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }
}
