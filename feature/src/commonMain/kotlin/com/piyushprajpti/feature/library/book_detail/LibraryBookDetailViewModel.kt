package com.piyushprajpti.feature.library.book_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LibraryBookDetailViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(LibraryBookDetailState())
    val state = _state.onStart {
        loadBookDetail()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LibraryBookDetailState()
    )

    fun onEvent(event: LibraryBookDetailEvent) {
        when (event) {
            LibraryBookDetailEvent.ReserveBook -> reserveBook()
        }
    }

    private fun loadBookDetail() {
        viewModelScope.launch {
            loadingHelper.showLoading()
            _state.update {
                it.copy(
                    book = BookDetail(
                        id = "1",
                        title = "Introduction to Algorithms, 4th Ed.",
                        authors = listOf("Thomas H. Cormen", "Charles E. Leiserson"),
                        publisher = "MIT PRESS",
                        year = "2022",
                        isbn = "978-0262046305",
                        description = "A comprehensive guide to the design and analysis of computer algorithms. The 4th edition includes new chapters on matchings in bipartite graphs, online algorithms, and machine learning. It covers a broad range of algorithms in depth, yet makes their design and analysis accessible to all levels of readers.",
                        coverImage = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg",
                        totalCopies = 3,
                        issuedCopies = 2,
                        isAvailable = true
                    ),
                    relatedBooks = listOf(
                        BookSummary(
                            "2",
                            "Algorithm Design",
                            "https://m.media-amazon.com/images/I/41T5H8u7fUL._SX331_BO1,204,203,200_.jpg"
                        ),
                        BookSummary(
                            "3",
                            "Clean Code",
                            "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg"
                        ),
                        BookSummary(
                            "4",
                            "Computer Networks",
                            "https://m.media-amazon.com/images/I/41T5H8u7fUL._SX331_BO1,204,203,200_.jpg"
                        ),
                        BookSummary(
                            "5",
                            "Data Structures",
                            "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg"
                        ),
                    )
                )
            }
            loadingHelper.hideLoading()
        }
    }

    private fun reserveBook() {
        viewModelScope.launch {
            messageHelper.showMessage("Book reserved successfully!")
        }
    }
}
