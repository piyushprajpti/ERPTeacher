package com.piyushprajpti.feature.library.dashboard

data class LibraryDashboardState(
    val issuedBooks: List<Book> = emptyList(),
    val newArrivals: List<Book> = emptyList(),
    val popularBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false
)

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val rating: Double? = null,
    val dueDate: String? = null,
    val isRenewable: Boolean = false
)