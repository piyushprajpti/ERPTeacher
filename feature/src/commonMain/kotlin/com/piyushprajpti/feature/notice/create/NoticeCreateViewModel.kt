package com.piyushprajpti.feature.notice.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class NoticeCreateViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(NoticeCreateState())
    val state = _state.onStart {
        // load initial data here if needed
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NoticeCreateState()
    )

    fun onEvent(event: NoticeCreateEvent) {
        when (event) {
            is NoticeCreateEvent.OnTitleChange -> {
                _state.update { it.copy(title = event.title) }
            }
            is NoticeCreateEvent.OnTypeChange -> {
                _state.update { it.copy(type = event.type) }
            }
            is NoticeCreateEvent.OnPriorityChange -> {
                _state.update { it.copy(priority = event.priority) }
            }
            is NoticeCreateEvent.OnTargetAudienceRemove -> {
                _state.update { 
                    it.copy(targetAudience = it.targetAudience.filter { audience -> audience != event.audience })
                }
            }
            is NoticeCreateEvent.OnAddMoreAudience -> {
                // Logic to add more audience
            }
            is NoticeCreateEvent.OnDateChange -> {
                _state.update { it.copy(date = event.date) }
            }
            is NoticeCreateEvent.OnTimeChange -> {
                _state.update { it.copy(time = event.time) }
            }
            is NoticeCreateEvent.OnVenueChange -> {
                _state.update { it.copy(venue = event.venue) }
            }
            is NoticeCreateEvent.OnDescriptionChange -> {
                _state.update { it.copy(description = event.description) }
            }
            is NoticeCreateEvent.OnScheduleToggle -> {
                _state.update { it.copy(isScheduleEnabled = event.enabled) }
            }
            is NoticeCreateEvent.OnPreview -> {
                // Handle preview logic
            }
            is NoticeCreateEvent.OnPublish -> {
                // Handle publish logic
            }
        }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }
}