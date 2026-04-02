package com.piyushprajpti.feature.attendance.mark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AttendanceMarkViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(AttendanceMarkState())
    val state = _state.onStart {
        loadDummyData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AttendanceMarkState()
    )

    private fun loadDummyData() {
        val students = listOf(
            StudentAttendance(
                id = "1",
                name = "Alexander Wright",
                rollNo = "ROLL NO: CS-102",
                avatarUrl = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
                status = AttendanceStatus.PRESENT
            ),
            StudentAttendance(
                id = "2",
                name = "Elena Kostic",
                rollNo = "ROLL NO: CS-103",
                avatarUrl = null,
                status = AttendanceStatus.UNMARKED
            ),
            StudentAttendance(
                id = "3",
                name = "Marcus Chen",
                rollNo = "ROLL NO: CS-104",
                avatarUrl = "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png",
                status = AttendanceStatus.ABSENT
            ),
            StudentAttendance(
                id = "4",
                name = "Sarah Lopez",
                rollNo = "ROLL NO: CS-105",
                avatarUrl = null,
                status = AttendanceStatus.UNMARKED
            ),
            StudentAttendance(
                id = "5",
                name = "David Okoro",
                rollNo = "ROLL NO: CS-106",
                avatarUrl = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
                status = AttendanceStatus.UNMARKED
            ),
            StudentAttendance(
                id = "6",
                name = "Emma Wilson",
                rollNo = "ROLL NO: CS-107",
                avatarUrl = "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png",
                status = AttendanceStatus.LATE
            ),
            StudentAttendance(
                id = "7",
                name = "James Bond",
                rollNo = "ROLL NO: CS-108",
                avatarUrl = null,
                status = AttendanceStatus.UNMARKED
            ),
            StudentAttendance(
                id = "8",
                name = "Olivia Brown",
                rollNo = "ROLL NO: CS-109",
                avatarUrl = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
                status = AttendanceStatus.PRESENT
            ),
            StudentAttendance(
                id = "9",
                name = "William Taylor",
                rollNo = "ROLL NO: CS-110",
                avatarUrl = null,
                status = AttendanceStatus.ABSENT
            ),
            StudentAttendance(
                id = "10",
                name = "Sophia Miller",
                rollNo = "ROLL NO: CS-111",
                avatarUrl = "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png",
                status = AttendanceStatus.LATE
            )
        )
        _state.update {
            it.copy(
                sessionActiveTime = "Session active for 24 mins",
                students = students,
                totalStudents = students.size
            )
        }
        updateCounts()
    }

    fun onEvent(event: AttendanceMarkEvent) {
        when (event) {
            is AttendanceMarkEvent.OnStatusChange -> {
                _state.update { currentState ->
                    val updatedStudents = currentState.students.map {
                        if (it.id == event.studentId) it.copy(status = event.status) else it
                    }
                    currentState.copy(students = updatedStudents)
                }
                updateCounts()
            }
            AttendanceMarkEvent.OnSubmitClick -> {
                // Handle submit
            }
            AttendanceMarkEvent.OnSaveDraftClick -> {
                // Handle save draft
            }
            AttendanceMarkEvent.OnFilterClick -> {
                // Handle filter
            }
            AttendanceMarkEvent.OnBackClick -> {
                // Handled in screen
            }
        }
    }

    private fun updateCounts() {
        _state.update { currentState ->
            currentState.copy(
                presentCount = currentState.students.count { it.status == AttendanceStatus.PRESENT },
                lateCount = currentState.students.count { it.status == AttendanceStatus.LATE },
                absentCount = currentState.students.count { it.status == AttendanceStatus.ABSENT }
            )
        }
    }
}