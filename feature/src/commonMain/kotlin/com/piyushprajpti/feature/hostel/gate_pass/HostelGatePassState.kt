package com.piyushprajpti.feature.hostel.gate_pass

import com.piyushprajpti.feature.hostel.gate_pass.models.GatePass

data class HostelGatePassState(
    val selectedTab: Int = 0,
    val activePass: GatePass? = null,
    val historyPasses: List<GatePass> = emptyList(),
    val requestType: Int = 0, // 0 for Gate Pass, 1 for Guest Pass
    val reason: String = "",
    val departureTime: String = "10:00 AM",
    val returnTime: String = "08:00 PM"
)
