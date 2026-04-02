package com.piyushprajpti.feature.exam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class ExamScheduleViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(ExamScheduleState())
    val state = _state.onStart {
        loadExamDuties()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ExamScheduleState()
    )

    fun onEvent(event: ExamScheduleEvent) {
        when (event) {
            is ExamScheduleEvent.SelectDate -> {
                _state.update { it.copy(selectedDate = event.date) }
                loadExamDuties()
            }
        }
    }

    private fun loadExamDuties() {
        // Mock data for demonstration
        val allDuties = listOf(
            ExamDuty(
                id = "1",
                examName = "End Semester Examination",
                subject = "Computer Networks",
                roomNumber = "402",
                block = "Block B",
                startTime = "09:00 AM",
                endTime = "12:00 PM",
                date = LocalDate(2026, 4, 15),
                status = ExamStatus.COMPLETED,
                studentCount = 45
            ),
            ExamDuty(
                id = "2",
                examName = "Internal Assessment II",
                subject = "Operating Systems",
                roomNumber = "Lab 10",
                block = "Academic Block",
                startTime = "01:30 PM",
                endTime = "03:00 PM",
                date = LocalDate(2026, 4, 15),
                status = ExamStatus.ONGOING,
                studentCount = 30
            ),
            ExamDuty(
                id = "3",
                examName = "End Semester Examination",
                subject = "Software Engineering",
                roomNumber = "Auditorium",
                block = "Main Block",
                startTime = "09:00 AM",
                endTime = "12:00 PM",
                date = LocalDate(2026, 4, 16),
                status = ExamStatus.UPCOMING,
                studentCount = 120
            ),
            ExamDuty(
                id = "4",
                examName = "Practical Exam",
                subject = "Database Management",
                roomNumber = "Server Room",
                block = "IT Block",
                startTime = "10:00 AM",
                endTime = "01:00 PM",
                date = LocalDate(2026, 4, 17),
                status = ExamStatus.UPCOMING,
                studentCount = 25
            ),
            ExamDuty(
                id = "5",
                examName = "Mid-Term Exam",
                subject = "Artificial Intelligence",
                roomNumber = "305",
                block = "Science Block",
                startTime = "09:30 AM",
                endTime = "11:30 AM",
                date = LocalDate(2026, 4, 15),
                status = ExamStatus.UPCOMING,
                studentCount = 40
            ),
            ExamDuty(
                id = "6",
                examName = "End Semester Examination",
                subject = "Discrete Mathematics",
                roomNumber = "501",
                block = "Block C",
                startTime = "02:00 PM",
                endTime = "05:00 PM",
                date = LocalDate(2026, 4, 15),
                status = ExamStatus.UPCOMING,
                studentCount = 55
            )
        )

        val filteredDuties = allDuties.filter { it.date == _state.value.selectedDate }
        _state.update { it.copy(examDuties = filteredDuties) }
    }
}
