package com.piyushprajpti.feature.events.dashboard

sealed interface EventDashboardEvent {
    data object OnBackClick : EventDashboardEvent
    data class OnDateSelected(val day: Int) : EventDashboardEvent
    data object OnNextMonth : EventDashboardEvent
    data object OnPreviousMonth : EventDashboardEvent
    data class OnFilterSelected(val filter: String) : EventDashboardEvent
    data class OnEventClick(val id: String) : EventDashboardEvent
    data object OnViewMajorEventDetails : EventDashboardEvent
}
