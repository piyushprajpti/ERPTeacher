package com.piyushprajpti.feature.hostel.dashboard

sealed interface HostelDashboardEvent {
    data object OnComplaintClick : HostelDashboardEvent
    data object OnGuestPassClick : HostelDashboardEvent
    data object OnMessClick : HostelDashboardEvent
    data object OnViewAllComplaintsClick : HostelDashboardEvent
    data class OnCallWardenClick(val phoneNumber: String) : HostelDashboardEvent
    data class OnMessageWardenClick(val phoneNumber: String) : HostelDashboardEvent
}
