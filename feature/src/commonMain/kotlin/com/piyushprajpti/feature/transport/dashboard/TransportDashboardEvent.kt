package com.piyushprajpti.feature.transport.dashboard

sealed interface TransportDashboardEvent {
    data object OnCallClick : TransportDashboardEvent
    data object OnTrackLiveClick : TransportDashboardEvent
    data object OnSupportClick : TransportDashboardEvent
}
