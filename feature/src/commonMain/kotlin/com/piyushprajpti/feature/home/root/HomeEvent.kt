package com.piyushprajpti.feature.home.root

sealed interface HomeEvent {
    data class OnQuickActionClick(val action: QuickAction) : HomeEvent
    data object OnNotificationClick : HomeEvent
}
