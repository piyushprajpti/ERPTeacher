package com.piyushprajpti.feature.library.dashboard

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

class LibraryDashboardViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(LibraryDashboardState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LibraryDashboardState()
    )

    private fun loadData() {
        viewModelScope.launch {
            loadingHelper.showLoading()
            _state.update {
                it.copy(
                    issuedBooks = listOf(
                        Book(
                            id = "1",
                            title = "Clean Code",
                            author = "Robert C. Martin",
                            imageUrl = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg",
                            dueDate = "DUE IN 2 DAYS",
                            isRenewable = true
                        ),
                        Book(
                            id = "2",
                            title = "The Clean Coder",
                            author = "Robert C. Martin",
                            imageUrl = "https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg",
                            dueDate = "DUE IN 5 DAYS",
                            isRenewable = false
                        )
                    ),
                    newArrivals = listOf(
                        Book(
                            id = "3",
                            title = "Pragmatic Programmer",
                            author = "Andrew Hunt",
                            imageUrl = "https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg"
                        ),
                        Book(
                            id = "4",
                            title = "Refactoring",
                            author = "Martin Fowler",
                            imageUrl = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg"
                        ),
                        Book(
                            id = "5",
                            title = "Design Patterns",
                            author = "Erich Gamma",
                            imageUrl = "https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg"
                        ),
                        Book(
                            id = "6",
                            title = "Code Complete",
                            author = "Steve McConnell",
                            imageUrl = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg"
                        )
                    ),
                    popularBooks = listOf(
                        Book(
                            id = "7",
                            title = "You Don't Know JS",
                            author = "Kyle Simpson",
                            imageUrl = "https://m.media-amazon.com/images/I/41T5H8u7fUL._SX331_BO1,204,203,200_.jpg",
                            rating = 4.9
                        ),
                        Book(
                            id = "8",
                            title = "The Mythical Man-Month",
                            author = "Frederick Brooks",
                            imageUrl = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg",
                            rating = 4.7
                        ),
                        Book(
                            id = "9",
                            title = "Introduction to Algorithms",
                            author = "Thomas H. Cormen",
                            imageUrl = "https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg",
                            rating = 4.8
                        )
                    )
                )
            }
            loadingHelper.hideLoading()
        }
    }

    fun onEvent(event: LibraryDashboardEvent) {
        when (event) {
            is LibraryDashboardEvent.OnRenewBook -> {
                viewModelScope.launch {
                    messageHelper.showMessage("Book renewed successfully")
                }
            }

            is LibraryDashboardEvent.OnBookClick -> {}
            LibraryDashboardEvent.OnSearchClick -> {}
            LibraryDashboardEvent.OnReadingHistoryClick -> {}
            LibraryDashboardEvent.OnViewAllIssuedBooksClick -> {}
        }
    }
}