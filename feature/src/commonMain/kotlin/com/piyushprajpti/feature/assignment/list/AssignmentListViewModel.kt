package com.piyushprajpti.feature.assignment.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AssignmentListViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(AssignmentListState())
    val state = _state.onStart {
        loadAssignments()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AssignmentListState()
    )

    private fun loadAssignments() {
        val dummyAssignments = listOf(
            Assignment(
                id = "1",
                subject = "PHYSICS",
                unit = "UNIT 3",
                title = "Quantum Mechanics & Wave Theory",
                course = "B.Tech CSE",
                className = "Section A",
                dueDate = "25 Oct",
                dueTime = "11:59 PM"
            ),
            Assignment(
                id = "2",
                subject = "MATHEMATICS",
                unit = "UNIT 2",
                title = "Linear Algebra and Matrices",
                course = "B.Tech CSE",
                className = "Section B",
                dueDate = "28 Oct",
                dueTime = "05:00 PM"
            ),
            Assignment(
                id = "3",
                subject = "CHEMISTRY",
                unit = "UNIT 4",
                title = "Organic Synthesis and Mechanisms",
                course = "B.Tech ME",
                className = "Section C",
                dueDate = "30 Oct",
                dueTime = "10:00 AM"
            ),
            Assignment(
                id = "4",
                subject = "COMPUTER SCIENCE",
                unit = "UNIT 1",
                title = "Data Structures and Algorithms",
                course = "B.Tech IT",
                className = "Section D",
                dueDate = "02 Nov",
                dueTime = "11:59 PM"
            ),
            Assignment(
                id = "5",
                subject = "ENGLISH",
                unit = "UNIT 5",
                title = "Professional Communication Skills",
                course = "B.Arch",
                className = "Section E",
                dueDate = "05 Nov",
                dueTime = "12:00 PM"
            ),
            Assignment(
                id = "6",
                subject = "DATABASE MANAGEMENT",
                unit = "UNIT 3",
                title = "SQL Optimization and Indexing",
                course = "B.Tech CSE",
                className = "Section A",
                dueDate = "08 Nov",
                dueTime = "08:00 PM"
            ),
            Assignment(
                id = "7",
                subject = "OPERATING SYSTEMS",
                unit = "UNIT 2",
                title = "Process Synchronization and Deadlocks",
                course = "B.Tech CSE",
                className = "Section B",
                dueDate = "12 Nov",
                dueTime = "11:00 PM"
            ),
            Assignment(
                id = "8",
                subject = "THERMODYNAMICS",
                unit = "UNIT 4",
                title = "Second Law and Entropy",
                course = "B.Tech ME",
                className = "Section C",
                dueDate = "15 Nov",
                dueTime = "04:00 PM"
            )
        )
        _state.update { it.copy(assignments = dummyAssignments) }
    }

    fun onEvent(event: AssignmentListEvent) {
        when (event) {
            is AssignmentListEvent.OnAddAssignment -> {
                // Navigate to add assignment screen
            }
            is AssignmentListEvent.OnShareAssignment -> {
                // Handle share logic
            }
            is AssignmentListEvent.OnSubmissionsClick -> {
                // Navigate to submissions screen
            }
        }
    }
}
