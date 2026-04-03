package com.piyushprajpti.feature.hostel.gate_pass.models

data class GatePass(
    val id: String,
    val type: String,
    val status: GatePassStatus,
    val statusText: String,
    val outTime: String,
    val inTime: String,
    val date: String,
    val qrCodeUrl: String? = null,
    val validity: String? = null,
    val reason: String? = null
)

enum class GatePassStatus {
    APPROVED, PENDING, REJECTED, EXPIRED, ACTIVE
}
