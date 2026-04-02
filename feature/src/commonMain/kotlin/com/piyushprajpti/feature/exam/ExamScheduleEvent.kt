package com.piyushprajpti.feature.exam

import kotlinx.datetime.LocalDate

sealed interface ExamScheduleEvent {
    data class SelectDate(val date: LocalDate) : ExamScheduleEvent
}
