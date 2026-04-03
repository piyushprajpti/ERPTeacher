package com.piyushprajpti.feature.syllabus.planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SyllabusPlannerViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(SyllabusPlannerState())
    val state = _state.onStart {
        loadInitialData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SyllabusPlannerState()
    )

    private fun loadInitialData() {
        _state.update {
            it.copy(
                curriculum = listOf(
                    TopicInfo(
                        id = "1",
                        title = "Implementation",
                        subtitle = "Array & Linked List based",
                        status = TopicStatus.COVERED_EARLIER,
                        tag = "Covered earlier",
                        quote = "Visualized using memory stack diagrams."
                    ),
                    TopicInfo(
                        id = "2",
                        title = "Applications",
                        subtitle = "Expression parsing, Undo mechanisms",
                        status = TopicStatus.COVERED_TODAY
                    ),
                    TopicInfo(
                        id = "3",
                        title = "Queue Types",
                        subtitle = "Circular, Deque, Priority Queues",
                        status = TopicStatus.PENDING
                    )
                )
            )
        }
    }

    fun onEvent(event: SyllabusPlannerEvent) {
        when (event) {
            is SyllabusPlannerEvent.OnSubjectChange -> {
                _state.update { it.copy(selectedSubject = event.subject) }
            }
            is SyllabusPlannerEvent.OnTopicStatusChange -> {
                _state.update {
                    it.copy(curriculum = it.curriculum.map { topic ->
                        if (topic.id == event.topicId) topic.copy(status = event.status) else topic
                    })
                }
            }
            is SyllabusPlannerEvent.OnNotesChange -> {
                _state.update {
                    it.copy(curriculum = it.curriculum.map { topic ->
                        if (topic.id == event.topicId) topic.copy(notes = event.notes) else topic
                    })
                }
            }
            is SyllabusPlannerEvent.OnSharingToggle -> {
                _state.update { it.copy(isSharingEnabled = event.enabled) }
            }
            is SyllabusPlannerEvent.OnSave -> {
                // Handle save
            }
        }
    }
}
