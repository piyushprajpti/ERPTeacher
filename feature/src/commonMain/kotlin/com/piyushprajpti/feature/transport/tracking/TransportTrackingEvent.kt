package com.piyushprajpti.feature.transport.tracking

sealed interface TransportTrackingEvent {
    data class ToggleAlert(val enabled: Boolean) : TransportTrackingEvent
    data object Refresh : TransportTrackingEvent
    data object ZoomIn : TransportTrackingEvent
    data object ZoomOut : TransportTrackingEvent
    data object FocusMyLocation : TransportTrackingEvent
    data class PermissionResult(val isGranted: Boolean) : TransportTrackingEvent
}
