package com.piyushprajpti.feature.hostel.dashboard.models

data class StayDetails(
    val roomNumber: String,
    val blockName: String,
    val floor: String,
    val sharingType: String,
    val roommates: List<Roommate>,
    val feeStatus: String,
    val feeStatusColor: String, // "success", "error", "warning"
    val nextPaymentDate: String? = null
)

data class Roommate(
    val name: String,
    val imageUrl: String
)
