package com.piyushprajpti.feature.hostel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.feature.hostel.dashboard.models.MessMenu
import com.piyushprajpti.feature.hostel.dashboard.models.RecentComplaint
import com.piyushprajpti.feature.hostel.dashboard.models.Roommate
import com.piyushprajpti.feature.hostel.dashboard.models.StayDetails
import com.piyushprajpti.feature.hostel.dashboard.models.WardenDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HostelDashboardViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(HostelDashboardState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HostelDashboardState()
    )

    private fun loadData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    stayDetails = StayDetails(
                        roomNumber = "B-204",
                        blockName = "Aryabhatta Block",
                        floor = "2nd Floor",
                        sharingType = "Double Sharing",
                        roommates = listOf(
                            Roommate("John Doe", "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png"),
                            Roommate("Jane Smith", "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png")
                        ),
                        feeStatus = "Paid",
                        feeStatusColor = "success",
                        nextPaymentDate = "Sept 15, 2024"
                    ),
                    messMenus = listOf(
                        MessMenu("BREAKFAST", "08:00 AM", "Poha, Masala Tea"),
                        MessMenu("LUNCH", "01:00 PM", "Dal Makhani, Steamed Rice, Butter Roti, Shahi Paneer"),
                        MessMenu("DINNER", "08:00 PM", "Mix Veg, Tandoori Roti, Hot Corn Soup")
                    ),
                    recentComplaints = listOf(
                        RecentComplaint(
                            id = "#C-4502",
                            title = "Leaking tap in bathroom",
                            date = "Filed Today",
                            status = "In Progress",
                            statusColor = "info"
                        )
                    ),
                    wardenDetails = WardenDetails(
                        name = "Mr. Rajesh Kumar",
                        phoneNumber = "+91 98765 43210",
                        imageUrl = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png"
                    )
                )
            }
        }
    }

    fun onEvent(event: HostelDashboardEvent) {
        when (event) {
            is HostelDashboardEvent.OnComplaintClick -> {
                // Handle navigation or action
            }
            is HostelDashboardEvent.OnGuestPassClick -> {
                // Handle navigation or action
            }
            is HostelDashboardEvent.OnMessClick -> {
                // Handle navigation or action
            }
            is HostelDashboardEvent.OnViewAllComplaintsClick -> {
                // Handle navigation or action
            }
            is HostelDashboardEvent.OnCallWardenClick -> {
                // Handle call logic
            }
            is HostelDashboardEvent.OnMessageWardenClick -> {
                // Handle message logic
            }
        }
    }
}
