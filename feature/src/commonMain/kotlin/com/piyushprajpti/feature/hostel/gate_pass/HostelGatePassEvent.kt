package com.piyushprajpti.feature.hostel.gate_pass

sealed interface HostelGatePassEvent {
    data class OnTabChange(val index: Int) : HostelGatePassEvent
    data class OnRequestTypeChange(val type: Int) : HostelGatePassEvent
    data class OnReasonChange(val reason: String) : HostelGatePassEvent
    data class OnDepartureTimeChange(val time: String) : HostelGatePassEvent
    data class OnReturnTimeChange(val time: String) : HostelGatePassEvent
    data object OnSubmitRequest : HostelGatePassEvent
}
