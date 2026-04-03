package com.piyushprajpti.feature.library.search

sealed interface LibrarySearchEvent {
    data class OnQueryChange(val query: String) : LibrarySearchEvent
    data class OnCategorySelect(val category: String) : LibrarySearchEvent
    data class OnBookClick(val bookId: String) : LibrarySearchEvent
    data object OnClearQuery : LibrarySearchEvent
}
