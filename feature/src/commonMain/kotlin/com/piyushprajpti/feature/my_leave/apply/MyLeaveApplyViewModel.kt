package com.piyushprajpti.feature.leave.leave_apply

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyLeaveApplyViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(MyLeaveApplyState())
    val state = _state.onStart {
        // load initial data here
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MyLeaveApplyState()
    )

    fun onEvent(event: MyLeaveApplyEvent) {
        when (event) {
            is MyLeaveApplyEvent.OnLeaveApplyTypeChange -> {
                _state.update { it.copy(selectedLeaveType = event.leaveType) }
            }

            is MyLeaveApplyEvent.OnFromDateChange -> {
                _state.update {
                    val newState = it.copy(fromDate = event.date)
                    newState.copy(
                        totalDays = calculateTotalDays(
                            newState.fromDate,
                            newState.toDate
                        )
                    )
                }
            }

            is MyLeaveApplyEvent.OnToDateChange -> {
                _state.update {
                    val newState = it.copy(toDate = event.date)
                    newState.copy(
                        totalDays = calculateTotalDays(
                            newState.fromDate,
                            newState.toDate
                        )
                    )
                }
            }

            is MyLeaveApplyEvent.OnReasonChange -> {
                if (event.reason.length <= 300) {
                    _state.update { it.copy(reason = event.reason) }
                }
            }

            MyLeaveApplyEvent.OnSubmit -> {
                submitApplication()
            }

            MyLeaveApplyEvent.OnBackClick -> {
                // Handled in Screen
            }
        }
    }

    private fun calculateTotalDays(from: Long?, to: Long?): Int {
        if (from == null || to == null) return 0
        val diff = to - from
        if (diff < 0) return 0
        return (diff / (1000 * 60 * 60 * 24)).toInt() + 1
    }

    private fun submitApplication() {
        if (_state.value.fromDate == null || _state.value.toDate == null) {
            messageHelper.showError("Please select dates")
            return
        }
        if (_state.value.reason.isBlank()) {
            messageHelper.showError("Please provide a reason")
            return
        }

        viewModelScope.launch {
            loadingHelper.showLoading()
            delay(2000) // Simulate API call
            loadingHelper.hideLoading()
            messageHelper.showMessage("Leave application submitted successfully")
        }
    }
}