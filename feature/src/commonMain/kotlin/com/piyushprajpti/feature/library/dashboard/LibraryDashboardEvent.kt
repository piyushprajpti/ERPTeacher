package com.piyushprajpti.feature.library.dashboard

sealed interface LibraryDashboardEvent {
    data object OnSearchClick : LibraryDashboardEvent
    data object OnReadingHistoryClick : LibraryDashboardEvent
    data class OnRenewBook(val bookId: String) : LibraryDashboardEvent
    data class OnBookClick(val bookId: String) : LibraryDashboardEvent
    data object OnViewAllIssuedBooksClick : LibraryDashboardEvent
}