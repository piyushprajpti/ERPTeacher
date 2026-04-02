package com.piyushprajpti.feature.result

sealed interface ResultEvent {
    data class OnSubjectSelected(val subject: String) : ResultEvent
    data class OnSemesterSelected(val semester: String) : ResultEvent
    data class OnYearSelected(val year: String) : ResultEvent
    data object OnSendInterventionAlert : ResultEvent
    data object OnShareReport : ResultEvent
    data object OnExportResult : ResultEvent
}
