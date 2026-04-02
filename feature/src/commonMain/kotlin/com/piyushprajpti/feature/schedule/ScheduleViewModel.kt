package com.piyushprajpti.feature.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ScheduleViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(ScheduleState())
    val state = _state.onStart {
        loadSchedule()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ScheduleState()
    )

    fun onEvent(event: ScheduleEvent) {
        when (event) {
            is ScheduleEvent.SelectDate -> {
                _state.update { it.copy(selectedDate = event.date) }
                loadSchedule()
            }
        }
    }

    private fun loadSchedule() {
        // Mock data for demonstration
        val mockItems = listOf(
            ScheduleItem(
                id = "1",
                subject = "Calculus III",
                classSection = "B.Tech CSE - 2A",
                startTime = "08:30 AM",
                endTime = "10:00 AM",
                location = "L-402",
                status = ScheduleStatus.COMPLETED
            ),
            ScheduleItem(
                id = "2",
                subject = "Data Structures",
                classSection = "B.Tech IT - 3B",
                startTime = "10:30 AM",
                endTime = "12:00 PM",
                location = "Lab Room 12-A",
                status = ScheduleStatus.ONGOING,
                progress = 0.65f,
                timeRemaining = "35 MINS REMAINING"
            ),
            ScheduleItem(
                id = "3",
                subject = "Database Systems",
                classSection = "B.Tech CSE - 2B",
                startTime = "01:30 PM",
                endTime = "03:00 PM",
                location = "Auditorium C",
                status = ScheduleStatus.UPCOMING
            ),
            ScheduleItem(
                id = "4",
                subject = "Modern Philosophy",
                classSection = "B.A. Phil - 1A",
                startTime = "03:30 PM",
                endTime = "05:00 PM",
                location = "Room 102",
                status = ScheduleStatus.UPCOMING
            ),
            ScheduleItem(
                id = "5",
                subject = "No Classes",
                classSection = "",
                startTime = "05:00 PM",
                endTime = "06:00 PM",
                location = "",
                isFreePeriod = true
            ),
            ScheduleItem(
                id = "6",
                subject = "Algorithm Design",
                classSection = "M.Tech CSE - 1",
                startTime = "06:00 PM",
                endTime = "07:30 PM",
                location = "Room 205",
                isCancelled = true,
                status = ScheduleStatus.UPCOMING
            )
        )
        _state.update { it.copy(scheduleItems = mockItems) }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }
}
