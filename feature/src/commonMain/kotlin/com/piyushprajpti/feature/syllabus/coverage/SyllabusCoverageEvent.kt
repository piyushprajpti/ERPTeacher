package com.piyushprajpti.feature.syllabus.coverage

sealed interface SyllabusCoverageEvent {
    data object OnBatchMarkTopics : SyllabusCoverageEvent
    data object OnExportReport : SyllabusCoverageEvent
    data class OnUnitClick(val unitId: String) : SyllabusCoverageEvent
}
