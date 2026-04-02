package com.piyushprajpti.feature.home.notifications

data class NotificationState(
    val isLoading: Boolean = false,
    val notifications: List<Notification> = emptyList(),
    val categories: List<String> = listOf("All", "General", "Academic", "Exam", "Finance"),
    val selectedCategory: String = "All"
)

data class Notification(
    val id: String,
    val title: String,
    val description: String,
    val category: NotificationCategory,
    val timestamp: String,
    val sender: String,
    val isUrgent: Boolean = false,
    val isRead: Boolean = false
)

enum class NotificationCategory {
    GENERAL, ACADEMIC, EXAM, FINANCE, URGENT
}
