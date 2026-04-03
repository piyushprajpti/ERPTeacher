package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.piyushprajpti.feature.transport.tracking.CameraTrigger
import com.piyushprajpti.feature.transport.tracking.TransportTrackingState

@Composable
actual fun TrackingMap(
    state: TransportTrackingState,
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
    onMyLocation: () -> Unit,
    modifier: Modifier
) {
    val busLatLng = LatLng(state.busLocation.latitude, state.busLocation.longitude)
    val collegeLatLng = LatLng(state.collegeLocation.latitude, state.collegeLocation.longitude)
    val userLatLng = LatLng(state.userLocation.latitude, state.userLocation.longitude)
    
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(busLatLng, 15f)
    }

    // Handle Camera Triggers from UI
    LaunchedEffect(state.cameraTrigger) {
        when (state.cameraTrigger) {
            CameraTrigger.FocusMyLocation -> {
                if (state.isLocationPermissionGranted) {
                    cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(userLatLng, 15f))
                }
            }
            CameraTrigger.ZoomIn -> {
                cameraPositionState.animate(CameraUpdateFactory.zoomIn())
            }
            CameraTrigger.ZoomOut -> {
                cameraPositionState.animate(CameraUpdateFactory.zoomOut())
            }
            null -> {}
        }
    }

    // Follow bus when it moves (optional: only if camera isn't being moved by user)
    LaunchedEffect(state.busLocation) {
        if (!cameraPositionState.isMoving) {
            cameraPositionState.animate(CameraUpdateFactory.newLatLng(busLatLng))
        }
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = false,
            compassEnabled = true
        ),
        properties = MapProperties(
            isMyLocationEnabled = state.isLocationPermissionGranted,
            mapType = MapType.NORMAL
        )
    ) {
        MarkerComposable(
            state = MarkerState(position = busLatLng),
            title = "Bus - ${state.routeName}"
        ) {
            BusMarker(routeName = state.routeName)
        }

        MarkerComposable(
            state = MarkerState(position = collegeLatLng),
            title = "College"
        ) {
            CollegeMarker()
        }
    }
}
