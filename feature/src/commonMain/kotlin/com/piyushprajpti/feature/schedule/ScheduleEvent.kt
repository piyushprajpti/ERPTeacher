package com.piyushprajpti.feature.schedule

import kotlinx.datetime.LocalDate

sealed interface ScheduleEvent {
    data class SelectDate(val date: LocalDate) : ScheduleEvent
}
