package com.piyushprajpti.feature.home.notifications

sealed interface NotificationEvent {
    data class OnCategorySelected(val category: String) : NotificationEvent
    data class OnNotificationClick(val notificationId: String) : NotificationEvent
    data object OnBackClick : NotificationEvent
}
