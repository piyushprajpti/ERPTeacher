package com.piyushprajpti.feature.attendance.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AttendanceDashboardViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(AttendanceDashboardState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AttendanceDashboardState()
    )

    private fun loadData() {
        _state.update {
            it.copy(
                currentDate = "Thursday, Oct 24th",
                activeSession = ClassItem(
                    id = "1",
                    subject = "Data Structures & Algorithms",
                    year = "B.Tech - 3rd Year",
                    section = "Section A",
                    startTime = "09:00 AM",
                    endTime = "10:30 AM",
                    room = "Lab 402, Science Block",
                    status = ClassStatus.ONGOING,
                    studentsEnrolled = 64
                ),
                todayClasses = listOf(
                    ClassItem(
                        id = "2",
                        subject = "Introduction to Technology",
                        year = "B.Tech - 1st Year",
                        section = "Section B",
                        startTime = "08:00 AM",
                        endTime = "09:00 AM",
                        room = "Room 102",
                        status = ClassStatus.COMPLETED
                    ),
                    ClassItem(
                        id = "1",
                        subject = "Data Structures & Algorithms",
                        year = "B.Tech - 3rd Year",
                        section = "Section A",
                        startTime = "09:00 AM",
                        endTime = "10:30 AM",
                        room = "Lab 402",
                        status = ClassStatus.ONGOING
                    ),
                    ClassItem(
                        id = "3",
                        subject = "Discrete Mathematics",
                        year = "B.Tech - 2nd Year",
                        section = "Section C",
                        startTime = "11:00 AM",
                        endTime = "12:00 PM",
                        room = "Hall B",
                        status = ClassStatus.UPCOMING
                    ),
                    ClassItem(
                        id = "4",
                        subject = "Advanced Operating Systems",
                        year = "B.Tech - 4th Year",
                        section = "Section A",
                        startTime = "02:30 PM",
                        endTime = "04:00 PM",
                        room = "Sem Room 101",
                        status = ClassStatus.UPCOMING
                    )
                )
            )
        }
    }

    fun onEvent(event: AttendanceDashboardEvent) {
        when (event) {
            is AttendanceDashboardEvent.OnClassClick -> {
                // Handle class click
            }
            AttendanceDashboardEvent.OnBackClick -> {
                // Handled in screen
            }
        }
    }
}