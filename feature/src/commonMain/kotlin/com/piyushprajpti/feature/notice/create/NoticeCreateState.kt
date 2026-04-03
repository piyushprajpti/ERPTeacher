package com.piyushprajpti.feature.notice.create

data class NoticeCreateState(
    val title: String = "",
    val type: String = "Event",
    val types: List<String> = listOf("Event", "Holiday", "Exam", "Placement", "Other"),
    val priority: String = "Normal",
    val targetAudience: List<String> = listOf("CS Dept", "Semester 4", "Semester 6"),
    val date: String = "",
    val time: String = "",
    val venue: String = "",
    val description: String = "",
    val isScheduleEnabled: Boolean = false,
    val isLoading: Boolean = false
)