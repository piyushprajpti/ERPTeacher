package com.piyushprajpti.feature.transport.tracking

data class TransportTrackingState(
    val routeName: String = "",
    val distance: String = "",
    val eta: String = "",
    val driverName: String = "",
    val driverRating: String = "",
    val driverImage: String = "",
    val busNumber: String = "",
    val currentSpeed: String = "",
    val isAlertEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val collegeLocation: Location = Location(0.0, 0.0),
    val busLocation: Location = Location(0.0, 0.0),
    val userLocation: Location = Location(0.0, 0.0),
    val cameraTrigger: CameraTrigger? = null,
    val isLocationPermissionGranted: Boolean = false
)

data class Location(
    val latitude: Double,
    val longitude: Double
)

sealed class CameraTrigger {
    data object ZoomIn : CameraTrigger()
    data object ZoomOut : CameraTrigger()
    data object FocusMyLocation : CameraTrigger()
}
