package com.piyushprajpti.feature.hostel.dashboard.models

data class RecentComplaint(
    val id: String,
    val title: String,
    val date: String,
    val status: String,
    val statusColor: String // "success", "error", "warning", "info"
)
