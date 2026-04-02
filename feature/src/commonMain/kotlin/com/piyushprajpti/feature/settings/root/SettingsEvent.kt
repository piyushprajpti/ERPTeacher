package com.piyushprajpti.feature.settings.root

sealed interface SettingsEvent {
    data class ChangeTheme(val theme: AppTheme) : SettingsEvent
    data class ToggleAllNotifications(val enabled: Boolean) : SettingsEvent
    data class ToggleAttendanceAlerts(val enabled: Boolean) : SettingsEvent
    data class ToggleClassAlerts(val enabled: Boolean) : SettingsEvent
    data class ToggleAnnouncements(val enabled: Boolean) : SettingsEvent
    data object OnLogoutClick : SettingsEvent
}
