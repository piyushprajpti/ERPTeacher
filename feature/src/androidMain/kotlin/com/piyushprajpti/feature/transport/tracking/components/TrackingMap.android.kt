package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.transport.tracking.CameraAction
import com.piyushprajpti.feature.transport.tracking.TransportTrackingState
import kotlinx.coroutines.flow.Flow

@Composable
actual fun TrackingMap(
    state: TransportTrackingState,
    cameraEvents: Flow<CameraAction>,
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
    onMyLocation: () -> Unit,
    modifier: Modifier
) {
    val busLatLng = LatLng(state.busLocation.latitude, state.busLocation.longitude)
    val collegeLatLng = LatLng(state.collegeLocation.latitude, state.collegeLocation.longitude)
    val userLatLng = LatLng(state.userLocation.latitude, state.userLocation.longitude)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            if (state.userLocation.latitude != 0.0) userLatLng else busLatLng,
            14.5f
        )
    }

    var isInitialCentered by remember { mutableStateOf(false) }
    LaunchedEffect(state.userLocation, state.busLocation) {
        if (!isInitialCentered && state.userLocation.latitude != 0.0) {
            isInitialCentered = true
            cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(userLatLng, 14.5f))
        }
    }

    // Handle Camera Actions from UI buttons (ZoomIn, ZoomOut, FocusMyLocation)
    LaunchedEffect(cameraEvents) {
        cameraEvents.collect { action ->
            when (action) {
                CameraAction.FocusMyLocation -> {
                    cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(userLatLng, 16f))
                }
                CameraAction.ZoomIn -> {
                    cameraPositionState.animate(CameraUpdateFactory.zoomIn())
                }
                CameraAction.ZoomOut -> {
                    cameraPositionState.animate(CameraUpdateFactory.zoomOut())
                }
            }
        }
    }

    // Also handle cameraTrigger from state if updated externally
    LaunchedEffect(state.cameraTrigger) {
        when (state.cameraTrigger) {
            CameraAction.FocusMyLocation -> {
                cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(userLatLng, 16f))
            }
            CameraAction.ZoomIn -> {
                cameraPositionState.animate(CameraUpdateFactory.zoomIn())
            }
            CameraAction.ZoomOut -> {
                cameraPositionState.animate(CameraUpdateFactory.zoomOut())
            }
            null -> {}
        }
    }

    val pathLatLngs = remember(state.routePath, state.busLocation, state.userLocation, state.collegeLocation) {
        if (state.routePath.isNotEmpty()) {
            state.routePath.map { LatLng(it.latitude, it.longitude) }
        } else {
            listOf(
                LatLng(state.busLocation.latitude, state.busLocation.longitude),
                LatLng(state.userLocation.latitude, state.userLocation.longitude),
                LatLng(state.collegeLocation.latitude, state.collegeLocation.longitude)
            )
        }
    }

    val busMarkerState = remember(busLatLng) { MarkerState(position = busLatLng) }
    val userMarkerState = remember(userLatLng) { MarkerState(position = userLatLng) }
    val collegeMarkerState = remember(collegeLatLng) { MarkerState(position = collegeLatLng) }

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
        // Draw route path between Bus, My Location, and College
        if (pathLatLngs.size >= 2) {
            // Glow / background path line
            Polyline(
                points = pathLatLngs,
                color = CustomTheme.colors.primary.copy(alpha = 0.35f),
                width = 18f
            )
            // Main solid route path line
            Polyline(
                points = pathLatLngs,
                color = CustomTheme.colors.primary,
                width = 10f
            )
        }

        // Bus Marker
        MarkerComposable(
            state = busMarkerState,
            title = "Bus - ${state.routeName}"
        ) {
            BusMarker(routeName = state.routeName)
        }

        // User Location Marker (pointed in between Bus and College)
        MarkerComposable(
            state = userMarkerState,
            title = "My Location"
        ) {
            MyLocationMarker()
        }

        // College Destination Marker
        MarkerComposable(
            state = collegeMarkerState,
            title = "College"
        ) {
            CollegeMarker()
        }
    }
}

