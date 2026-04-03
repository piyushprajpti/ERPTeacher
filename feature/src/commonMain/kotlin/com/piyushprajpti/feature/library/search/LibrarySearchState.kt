package com.piyushprajpti.feature.library.search

import com.piyushprajpti.feature.library.dashboard.Book

data class LibrarySearchState(
    val searchQuery: String = "",
    val selectedCategory: String = "All",
    val categories: List<String> = listOf("All", "CS Dept", "Mathematics", "E-Book", "Physics", "Chemistry", "Fiction"),
    val searchResults: List<SearchBook> = emptyList(),
)

data class SearchBook(
    val book: Book,
    val status: BookStatus,
    val info: String,
    val shelf: String? = null
)

sealed interface BookStatus {
    data object Available : BookStatus
    data object Issued : BookStatus
    data object Reserved : BookStatus
}
