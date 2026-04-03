package com.piyushprajpti.feature.syllabus.planner

sealed interface SyllabusPlannerEvent {
    data class OnSubjectChange(val subject: String) : SyllabusPlannerEvent
    data class OnTopicStatusChange(val topicId: String, val status: TopicStatus) : SyllabusPlannerEvent
    data class OnNotesChange(val topicId: String, val notes: String) : SyllabusPlannerEvent
    data class OnSharingToggle(val enabled: Boolean) : SyllabusPlannerEvent
    data object OnSave : SyllabusPlannerEvent
}
