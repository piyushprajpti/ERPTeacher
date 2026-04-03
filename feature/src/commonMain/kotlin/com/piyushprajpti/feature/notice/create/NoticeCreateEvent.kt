package com.piyushprajpti.feature.notice.create

sealed interface NoticeCreateEvent {
    data class OnTitleChange(val title: String) : NoticeCreateEvent
    data class OnTypeChange(val type: String) : NoticeCreateEvent
    data class OnPriorityChange(val priority: String) : NoticeCreateEvent
    data class OnTargetAudienceRemove(val audience: String) : NoticeCreateEvent
    data object OnAddMoreAudience : NoticeCreateEvent
    data class OnDateChange(val date: String) : NoticeCreateEvent
    data class OnTimeChange(val time: String) : NoticeCreateEvent
    data class OnVenueChange(val venue: String) : NoticeCreateEvent
    data class OnDescriptionChange(val description: String) : NoticeCreateEvent
    data class OnScheduleToggle(val enabled: Boolean) : NoticeCreateEvent
    data object OnPreview : NoticeCreateEvent
    data object OnPublish : NoticeCreateEvent
}