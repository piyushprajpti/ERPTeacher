package com.piyushprajpti.feature.events.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class EventDashboardViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(EventDashboardState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = EventDashboardState()
    )

    private fun loadData() {
        val filters = listOf(
            EventFilter("All") { it.primaryText },
            EventFilter("Holidays") { it.orange },
            EventFilter("Academic") { it.blue },
            EventFilter("Cultural") { it.purple },
            EventFilter("Sports") { it.success }
        )

        val majorEvent = MajorEvent(
            title = "Annual Tech Fest 2026",
            dateRange = "March 20-22",
            daysAway = 5,
            description = "Experience the biggest technical festival with workshops, hackathons, and guest lectures."
        )

        val calendarDays = (1..31).map { day ->
            CalendarDay(
                day = day,
                isSelected = day == 5,
                dots = when (day) {
                    3 -> listOf { it.blue }
                    10 -> listOf { it.error }
                    12 -> listOf { it.blue }
                    14 -> listOf { it.orange }
                    18 -> listOf { it.success }
                    20 -> listOf { it.purple }
                    else -> emptyList()
                }
            )
        }

        val allEvents = listOf(
            UpcomingEvent(
                id = "1",
                title = "Data Science Workshop",
                time = "10:00 AM",
                location = "Lab 402",
                date = "12",
                month = "MAR",
                category = "ACADEMIC",
                categoryColor = { it.blue }
            ),
            UpcomingEvent(
                id = "2",
                title = "Holi Celebration",
                time = "All Day",
                location = "Main Lawn",
                date = "14",
                month = "MAR",
                category = "HOLIDAY",
                categoryColor = { it.orange }
            ),
            UpcomingEvent(
                id = "3",
                title = "Inter-College Cricket",
                time = "9:00 AM",
                location = "Sports Arena",
                date = "18",
                month = "MAR",
                category = "SPORTS",
                categoryColor = { it.success }
            ),
             UpcomingEvent(
                id = "4",
                title = "Android Development Seminar",
                time = "2:00 PM",
                location = "Seminar Hall 1",
                date = "22",
                month = "MAR",
                category = "ACADEMIC",
                categoryColor = { it.blue }
            ),
            UpcomingEvent(
                id = "5",
                title = "Music Concert",
                time = "6:00 PM",
                location = "Auditorium",
                date = "25",
                month = "MAR",
                category = "CULTURAL",
                categoryColor = { it.purple }
            )
        )

        _state.update { 
            it.copy(
                filters = filters,
                majorEvent = majorEvent,
                calendarDays = calendarDays,
                upcomingEvents = allEvents
            )
        }
    }

    fun onEvent(event: EventDashboardEvent) {
        when (event) {
            is EventDashboardEvent.OnDateSelected -> {
                _state.update { state ->
                    state.copy(
                        selectedDate = "2026-03-${event.day.toString().padStart(2, '0')}",
                        calendarDays = state.calendarDays.map { it.copy(isSelected = it.day == event.day) }
                    )
                }
            }
            is EventDashboardEvent.OnFilterSelected -> {
                _state.update { it.copy(selectedFilter = event.filter) }
            }
            is EventDashboardEvent.OnEventClick -> {
                messageHelper.showMessage("Event Clicked: ${event.id}")
            }
            EventDashboardEvent.OnViewMajorEventDetails -> {
                messageHelper.showMessage("Major Event Details Clicked")
            }
            else -> {}
        }
    }
}
