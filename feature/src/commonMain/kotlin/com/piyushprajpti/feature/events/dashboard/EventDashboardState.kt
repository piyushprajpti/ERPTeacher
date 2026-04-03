package com.piyushprajpti.feature.events.dashboard

import androidx.compose.ui.graphics.Color
import com.piyushprajpti.college_erp_system.theme.CustomColorScheme

data class EventDashboardState(
    val isLoading: Boolean = false,
    val selectedDate: String = "2026-03-05",
    val currentMonth: String = "March 2026",
    val majorEvent: MajorEvent? = null,
    val selectedFilter: String = "All",
    val filters: List<EventFilter> = emptyList(),
    val upcomingEvents: List<UpcomingEvent> = emptyList(),
    val calendarDays: List<CalendarDay> = emptyList()
)

data class MajorEvent(
    val title: String,
    val dateRange: String,
    val daysAway: Int,
    val description: String
)

data class EventFilter(
    val name: String,
    val color: (CustomColorScheme) -> Color
)

data class UpcomingEvent(
    val id: String,
    val title: String,
    val time: String,
    val location: String,
    val date: String,
    val month: String,
    val category: String,
    val categoryColor: (CustomColorScheme) -> Color
)

data class CalendarDay(
    val day: Int,
    val isSelected: Boolean = false,
    val dots: List<(CustomColorScheme) -> Color> = emptyList(),
    val isCurrentMonth: Boolean = true
)
