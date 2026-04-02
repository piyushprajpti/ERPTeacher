package com.piyushprajpti.feature.exam

import kotlinx.datetime.LocalDate

data class ExamScheduleState(
    val isLoading: Boolean = false,
    val selectedDate: LocalDate = LocalDate(2026, 4, 15),
    val startDate: LocalDate = LocalDate(2026, 4, 1),
    val endDate: LocalDate = LocalDate(2026, 4, 30),
    val examDuties: List<ExamDuty> = emptyList()
)

data class ExamDuty(
    val id: String,
    val examName: String,
    val subject: String,
    val roomNumber: String,
    val block: String,
    val startTime: String,
    val endTime: String,
    val date: LocalDate,
    val status: ExamStatus = ExamStatus.UPCOMING,
    val studentCount: Int
)

enum class ExamStatus {
    COMPLETED, ONGOING, UPCOMING
}
