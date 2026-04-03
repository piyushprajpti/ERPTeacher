package com.piyushprajpti.feature.hostel.mess

sealed interface HostelMessEvent {
    data class OnDaySelected(val index: Int) : HostelMessEvent
    data class OnRateMeal(val rating: Int) : HostelMessEvent
    data class OnEmojiRateMeal(val index: Int) : HostelMessEvent
    data class OnSkipMealsToggle(val enabled: Boolean) : HostelMessEvent
    data object OnViewSpecialMenuClick : HostelMessEvent
}
