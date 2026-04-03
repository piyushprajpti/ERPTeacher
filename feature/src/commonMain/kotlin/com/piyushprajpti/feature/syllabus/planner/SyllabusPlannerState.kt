package com.piyushprajpti.feature.syllabus.planner

data class SyllabusPlannerState(
    val selectedSubject: String = "Data Structures - CS Dept",
    val subjects: List<String> = listOf("Data Structures - CS Dept", "Algorithms - CS Dept", "Operating Systems - CS Dept"),
    val currentUnit: UnitInfo = UnitInfo(
        number = 2,
        title = "Stacks & Queues",
        progress = 0.85f,
        status = "On Track",
        targetCompletion = "Oct 15, 2023"
    ),
    val curriculum: List<TopicInfo> = emptyList(),
    val isSharingEnabled: Boolean = true,
    val isLoading: Boolean = false
)

data class UnitInfo(
    val number: Int,
    val title: String,
    val progress: Float,
    val status: String,
    val targetCompletion: String
)

data class TopicInfo(
    val id: String,
    val title: String,
    val subtitle: String,
    val status: TopicStatus,
    val tag: String? = null,
    val quote: String? = null,
    val notes: String = ""
)

enum class TopicStatus {
    COVERED_TODAY, COVERED_EARLIER, PENDING
}
