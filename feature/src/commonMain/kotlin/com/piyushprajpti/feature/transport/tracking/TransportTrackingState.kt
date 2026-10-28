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
    val routePath: List<Location> = emptyList(),
    val cameraTrigger: CameraTrigger? = null,
    val isLocationPermissionGranted: Boolean = false
)

data class Location(
    val latitude: Double,
    val longitude: Double
)

sealed interface CameraAction {
    data object ZoomIn : CameraAction
    data object ZoomOut : CameraAction
    data object FocusMyLocation : CameraAction
}

typealias CameraTrigger = CameraAction

