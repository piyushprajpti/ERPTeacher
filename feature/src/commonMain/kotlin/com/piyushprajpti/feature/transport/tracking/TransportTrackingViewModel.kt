package com.piyushprajpti.feature.transport.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TransportTrackingViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _cameraEvents = Channel<CameraAction>(Channel.BUFFERED)
    val cameraEvents: Flow<CameraAction> = _cameraEvents.receiveAsFlow()

    private val _state = MutableStateFlow(TransportTrackingState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TransportTrackingState()
    )

    private var isDataLoaded = false
    private var simulationJob: Job? = null

    private fun loadData() {
        // Prevent redundant data loading
        if (isDataLoaded) return
        isDataLoaded = true

        // Realistic route between Bus, My Location (in between), and College
        val dummyRoute = listOf(
            Location(23.0420, 72.5640), // Bus starting point
            Location(23.0405, 72.5643),
            Location(23.0390, 72.5648),
            Location(23.0375, 72.5655),
            Location(23.0360, 72.5662),
            Location(23.0345, 72.5670),
            Location(23.0330, 72.5677),
            Location(23.0315, 72.5685), // User Location ("My Location" / Pickup point)
            Location(23.0300, 72.5691),
            Location(23.0285, 72.5697),
            Location(23.0270, 72.5702),
            Location(23.0255, 72.5707),
            Location(23.0240, 72.5711),
            Location(23.0225, 72.5714)  // College Campus
        )

        val userLocation = Location(23.0315, 72.5685)
        val collegeLocation = Location(23.0225, 72.5714)

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
                collegeLocation = collegeLocation,
                busLocation = dummyRoute.first(),
                userLocation = userLocation,
                routePath = dummyRoute
            )
        }

        startSimulation(dummyRoute)
    }

    private fun startSimulation(route: List<Location>) {
        simulationJob?.cancel()
        simulationJob = viewModelScope.launch {
            var currentIndex = 0
            while (isActive) {
                delay(3500L)
                if (currentIndex < route.size - 1) {
                    currentIndex++
                } else {
                    currentIndex = 0 // loop simulation for live demo
                }
                val nextLocation = route[currentIndex]
                val remainingPoints = (route.size - 1 - currentIndex).coerceAtLeast(0)
                val distanceKm = remainingPoints * 0.18 + 0.2
                val roundedDistance = ((distanceKm * 10).toInt() / 10.0)
                val etaMinutes = (remainingPoints * 0.6 + 1).toInt()
                val speed = (32..40).random()

                _state.update {
                    it.copy(
                        busLocation = nextLocation,
                        distance = "$roundedDistance km",
                        eta = "$etaMinutes mins",
                        currentSpeed = "$speed km/h"
                    )
                }
            }
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
                _state.update { it.copy(cameraTrigger = CameraAction.FocusMyLocation) }
                viewModelScope.launch {
                    _cameraEvents.send(CameraAction.FocusMyLocation)
                }
            }
            TransportTrackingEvent.ZoomIn -> {
                _state.update { it.copy(cameraTrigger = CameraAction.ZoomIn) }
                viewModelScope.launch {
                    _cameraEvents.send(CameraAction.ZoomIn)
                }
            }
            TransportTrackingEvent.ZoomOut -> {
                _state.update { it.copy(cameraTrigger = CameraAction.ZoomOut) }
                viewModelScope.launch {
                    _cameraEvents.send(CameraAction.ZoomOut)
                }
            }
            is TransportTrackingEvent.PermissionResult -> {
                _state.update { it.copy(isLocationPermissionGranted = event.isGranted) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        simulationJob?.cancel()
    }
}
