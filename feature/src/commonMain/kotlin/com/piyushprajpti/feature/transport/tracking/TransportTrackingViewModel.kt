package com.piyushprajpti.feature.transport.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TransportTrackingViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(TransportTrackingState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TransportTrackingState()
    )

    private var isDataLoaded = false

    private fun loadData() {
        // Prevent redundant data loading
        if (isDataLoaded) return
        isDataLoaded = true

        _state.update {
            it.copy(
                routeName = "Route 42",
                distance = "2.4 km",
                eta = "8 mins",
                driverName = "Vikram Singh",
                driverRating = "4.8",
                driverImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
                busNumber = "GJ-01-AX-1234",
                currentSpeed = "35 km/h",
                isAlertEnabled = true,
                collegeLocation = Location(23.0225, 72.5714),
                busLocation = Location(23.0330, 72.5850),
                userLocation = Location(23.0120, 72.5600)
            )
        }
    }

    fun onEvent(event: TransportTrackingEvent) {
        when (event) {
            is TransportTrackingEvent.ToggleAlert -> {
                // Only update if value actually changed
                if (_state.value.isAlertEnabled != event.enabled) {
                    _state.update { it.copy(isAlertEnabled = event.enabled) }
                }
            }

            TransportTrackingEvent.Refresh -> {
                loadData()
            }

            TransportTrackingEvent.FocusMyLocation -> {
                _state.update { it.copy(cameraTrigger = CameraTrigger.FocusMyLocation) }
                resetCameraTrigger()
            }
            TransportTrackingEvent.ZoomIn -> {
                _state.update { it.copy(cameraTrigger = CameraTrigger.ZoomIn) }
                resetCameraTrigger()
            }
            TransportTrackingEvent.ZoomOut -> {
                _state.update { it.copy(cameraTrigger = CameraTrigger.ZoomOut) }
                resetCameraTrigger()
            }
            is TransportTrackingEvent.PermissionResult -> {
                _state.update { it.copy(isLocationPermissionGranted = event.isGranted) }
            }
        }
    }

    private fun resetCameraTrigger() {
        _state.update { it.copy(cameraTrigger = null) }
    }
}
