package com.piyushprajpti.feature.home.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class NotificationViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(NotificationState())
    val state = _state.onStart {
        loadNotifications()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NotificationState()
    )

    fun onEvent(event: NotificationEvent) {
        when (event) {
            is NotificationEvent.OnCategorySelected -> {
                _state.update { it.copy(selectedCategory = event.category) }
            }

            is NotificationEvent.OnNotificationClick -> {
                // Handle notification click
            }

            NotificationEvent.OnBackClick -> {
                // Handle back click - usually via navigation, but we can have it here if needed
            }
        }
    }

    private fun loadNotifications() {
        val dummyNotifications = listOf(
            Notification(
                id = "1",
                title = "Mandatory Semester 4 Registration Deadline",
                description = "Please ensure all registration forms are submitted by 5 PM today to avoid late fees...",
                category = NotificationCategory.URGENT,
                timestamp = "2 hours ago",
                sender = "Admin Office",
                isUrgent = true,
                isRead = false
            ),
            Notification(
                id = "2",
                title = "New Library Timings for Exams",
                description = "The library will now remain open until 2 AM during the midterm week for student convenience...",
                category = NotificationCategory.GENERAL,
                timestamp = "Yesterday",
                sender = "Librarian",
                isUrgent = false,
                isRead = false
            ),
            Notification(
                id = "3",
                title = "Scholarship Disbursement Update",
                description = "Merit scholarships for the Autumn 2023 session have been processed and credited to verified accounts...",
                category = NotificationCategory.FINANCE,
                timestamp = "2 days ago",
                sender = "Accounts Dept",
                isUrgent = false,
                isRead = true
            ),
            Notification(
                id = "4",
                title = "End Semester Examination Schedule",
                description = "The final examination schedule for all departments has been released. Please check the portal for details.",
                category = NotificationCategory.EXAM,
                timestamp = "3 days ago",
                sender = "Examination Cell",
                isUrgent = false,
                isRead = true
            )
        )
        _state.update { it.copy(notifications = dummyNotifications) }
    }
}
