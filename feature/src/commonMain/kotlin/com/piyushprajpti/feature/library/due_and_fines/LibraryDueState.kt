package com.piyushprajpti.feature.library.due_and_fines

data class LibraryDueState(
    val overdueBooks: List<OverdueBook> = emptyList(),
    val recentActivities: List<RecentActivity> = emptyList(),
    val totalFine: Double = 0.0,
    val calculationInfo: String = "",
    val lastUpdated: String = ""
)

data class OverdueBook(
    val title: String,
    val dueDate: String,
    val overdueDays: Int,
    val finePerDay: Double,
    val totalFine: Double
)

data class RecentActivity(
    val title: String,
    val status: String,
    val date: String
)
