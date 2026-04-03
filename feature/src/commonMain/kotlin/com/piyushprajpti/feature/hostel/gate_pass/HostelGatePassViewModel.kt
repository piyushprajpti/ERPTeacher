package com.piyushprajpti.feature.hostel.gate_pass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Toast
import com.piyushprajpti.components.message_helper.model.ToastType
import com.piyushprajpti.feature.hostel.gate_pass.models.GatePass
import com.piyushprajpti.feature.hostel.gate_pass.models.GatePassStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HostelGatePassViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(HostelGatePassState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HostelGatePassState()
    )

    private fun loadData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    activePass = GatePass(
                        id = "GP-8821",
                        type = "Standard Exit Pass",
                        status = GatePassStatus.APPROVED,
                        statusText = "APPROVED",
                        outTime = "10:00 AM",
                        inTime = "08:00 PM",
                        date = "Today",
                        validity = "Valid until 08:00 PM • Today",
                        qrCodeUrl = "https://img.freepik.com/premium-vector/modern-square-sample-black-qr-code-icon-white-background_131186-1524.jpg"
                    ),
                    historyPasses = listOf(
                        GatePass(
                            id = "GP-8750",
                            type = "Weekend Home Visit",
                            status = GatePassStatus.EXPIRED,
                            statusText = "EXPIRED",
                            outTime = "09:00 AM",
                            inTime = "06:00 PM",
                            date = "Oct 12",
                            reason = "Weekend home"
                        ),
                        GatePass(
                            id = "GP-8692",
                            type = "Personal Work",
                            status = GatePassStatus.EXPIRED,
                            statusText = "EXPIRED",
                            outTime = "02:00 PM",
                            inTime = "05:00 PM",
                            date = "Oct 08",
                            reason = "Personal work"
                        ),
                        GatePass(
                            id = "GP-8600",
                            type = "Night Out",
                            status = GatePassStatus.REJECTED,
                            statusText = "REJECTED",
                            outTime = "08:00 PM",
                            inTime = "08:00 AM",
                            date = "Sept 25",
                            reason = "Family function"
                        )
                    )
                )
            }
        }
    }

    fun onEvent(event: HostelGatePassEvent) {
        when (event) {
            is HostelGatePassEvent.OnTabChange -> _state.update { it.copy(selectedTab = event.index) }
            is HostelGatePassEvent.OnRequestTypeChange -> _state.update { it.copy(requestType = event.type) }
            is HostelGatePassEvent.OnReasonChange -> _state.update { it.copy(reason = event.reason) }
            is HostelGatePassEvent.OnDepartureTimeChange -> _state.update { it.copy(departureTime = event.time) }
            is HostelGatePassEvent.OnReturnTimeChange -> _state.update { it.copy(returnTime = event.time) }
            HostelGatePassEvent.OnSubmitRequest -> {
                if (_state.value.reason.isBlank()) {
                    messageHelper.setToast(Toast(type = ToastType.WARNING, text = "Please enter reason"))
                    return
                }
                messageHelper.setToast(Toast(type = ToastType.SUCCESS, text = "Request submitted successfully"))
                _state.update { it.copy(reason = "") }
            }
        }
    }
}
