package com.piyushprajpti.feature.transport.dashboard

data class TransportDashboardState(
    val isLoading: Boolean = false,
    val busName: String = "",
    val routeNumber: String = "",
    val busNumber: String = "",
    val pickupPoint: String = "",
    val scheduledTime: String = "",
    val eta: String = "",
    val currentPosition: String = "",
    val timeline: List<TimelineItem> = emptyList(),
    val feeStatus: String = "",
    val feeValidUntil: String = "",
    val driverName: String = "",
    val driverPhone: String = "",
    val driverImage: String = ""
)

data class TimelineItem(
    val title: String = "",
    val time: String = "",
    val isCurrent: Boolean = false,
    val isPast: Boolean = false,
    val isPickupPoint: Boolean = false
)
