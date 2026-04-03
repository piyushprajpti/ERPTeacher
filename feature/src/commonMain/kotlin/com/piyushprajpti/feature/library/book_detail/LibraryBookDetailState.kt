package com.piyushprajpti.feature.library.book_detail

data class LibraryBookDetailState(
    val book: BookDetail? = null,
    val relatedBooks: List<BookSummary> = emptyList()
)

data class BookDetail(
    val id: String,
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val year: String,
    val isbn: String,
    val description: String,
    val coverImage: String,
    val totalCopies: Int,
    val issuedCopies: Int,
    val isAvailable: Boolean
)

data class BookSummary(
    val id: String,
    val title: String,
    val coverImage: String
)
