package com.piyushprajpti.feature.schedule

import kotlinx.datetime.LocalDate

data class ScheduleState(
    val isLoading: Boolean = false,
    val selectedDate: LocalDate = LocalDate(2026, 3, 28),
    val startDate: LocalDate = LocalDate(2026, 1, 7),
    val endDate: LocalDate = LocalDate(2026, 5, 4),
    val scheduleItems: List<ScheduleItem> = emptyList()
)

data class ScheduleItem(
    val id: String,
    val subject: String,
    val classSection: String,
    val startTime: String,
    val endTime: String,
    val location: String,
    val room: String? = null,
    val status: ScheduleStatus = ScheduleStatus.UPCOMING,
    val progress: Float? = null,
    val timeRemaining: String? = null,
    val isCancelled: Boolean = false,
    val isFreePeriod: Boolean = false
)

enum class ScheduleStatus {
    COMPLETED, ONGOING, UPCOMING
}
