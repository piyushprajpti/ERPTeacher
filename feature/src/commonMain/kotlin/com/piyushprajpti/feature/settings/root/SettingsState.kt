package com.piyushprajpti.feature.settings.root

data class SettingsState(
    val currentTheme: AppTheme = AppTheme.DEVICE_DEFAULT,
    val allNotifications: Boolean = true,
    val attendanceAlerts: Boolean = true,
    val classAlerts: Boolean = true,
    val announcements: Boolean = true,
    val appVersion: String = "v2.4.0"
)
