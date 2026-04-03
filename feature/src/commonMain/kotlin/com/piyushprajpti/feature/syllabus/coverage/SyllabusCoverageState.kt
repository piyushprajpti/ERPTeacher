package com.piyushprajpti.feature.syllabus.coverage

data class SyllabusCoverageState(
    val totalProgress: Float = 0.72f,
    val totalUnits: Int = 8,
    val pendingUnits: Int = 3,
    val delayedUnits: Int = 1,
    val units: List<UnitCoverage> = emptyList(),
    val timelineData: List<TimelinePoint> = emptyList(),
    val isLoading: Boolean = false
)

data class UnitCoverage(
    val id: String,
    val unitNumber: Int,
    val title: String,
    val subjectName: String,
    val courseName: String,
    val topicsCovered: Int,
    val totalTopics: Int,
    val status: UnitStatus,
    val dueDateText: String? = null
)

enum class UnitStatus {
    COMPLETED, IN_PROGRESS, LAGGING
}

data class TimelinePoint(
    val label: String,
    val planned: Float,
    val actual: Float
)
