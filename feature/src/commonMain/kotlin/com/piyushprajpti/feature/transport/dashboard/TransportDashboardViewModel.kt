package com.piyushprajpti.feature.transport.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TransportDashboardViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(TransportDashboardState())
    val state = _state.onStart {
        loadDashboardData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TransportDashboardState()
    )

    private fun loadDashboardData() {
        _state.update {
            it.copy(
                busName = "Route 42",
                routeNumber = "GJ-01-AX-1234",
                busNumber = "42",
                pickupPoint = "Sector 11 Main Gate",
                scheduledTime = "08:15 AM",
                eta = "8 mins away",
                currentPosition = "Near Sector 5 Park",
                feeStatus = "Paid",
                feeValidUntil = "11 Sep 2025",
                driverName = "Vikram Singh",
                driverPhone = "+919876543210",
                driverImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
                timeline = listOf(
                    TimelineItem("Home (Base)", "07:45 AM", isPast = true),
                    TimelineItem("Sector 5 Crossing", "Current", isCurrent = true, isPast = true),
                    TimelineItem("Sector 11 Main Gate", "08:15 AM", isPickupPoint = true),
                    TimelineItem("College Campus", "08:45 AM")
                )
            )
        }
    }

    fun onEvent(event: TransportDashboardEvent) {
        when (event) {
            TransportDashboardEvent.OnCallClick -> {
                // Dialer will be handled in UI via Intent
            }
            TransportDashboardEvent.OnTrackLiveClick -> {
                messageHelper.showMessage("Opening Live Tracking...")
            }
            TransportDashboardEvent.OnSupportClick -> {
                messageHelper.showMessage("Connecting to Transport Office...")
            }
        }
    }
}
