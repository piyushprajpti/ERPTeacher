package com.piyushprajpti.feature.library.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.feature.library.dashboard.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LibrarySearchViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(LibrarySearchState())
    val state = _state.onStart {
        loadInitialData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LibrarySearchState()
    )

    private val allBooks = listOf(
        SearchBook(
            book = Book(
                id = "10",
                title = "Introduction to Algorithms",
                author = "Cormen et al.",
                imageUrl = "https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg"
            ),
            status = BookStatus.Available,
            info = "Shelf A-12",
            shelf = "A-12"
        ),
        SearchBook(
            book = Book(
                id = "11",
                title = "Operating Systems",
                author = "Silberschatz",
                imageUrl = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg"
            ),
            status = BookStatus.Issued,
            info = "Due Jun 15"
        ),
        SearchBook(
            book = Book(
                id = "12",
                title = "Computer Networks",
                author = "Tanenbaum",
                imageUrl = "https://m.media-amazon.com/images/I/41T5H8u7fUL._SX331_BO1,204,203,200_.jpg"
            ),
            status = BookStatus.Reserved,
            info = "Pickup 2pm"
        ),
        SearchBook(
            book = Book(
                id = "13",
                title = "Discrete Mathematics",
                author = "Kenneth Rosen",
                imageUrl = "https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg"
            ),
            status = BookStatus.Available,
            info = "Shelf B-04",
            shelf = "B-04"
        ),
        SearchBook(
            book = Book(
                id = "14",
                title = "Modern Physics",
                author = "Arthur Beiser",
                imageUrl = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg"
            ),
            status = BookStatus.Available,
            info = "Shelf C-09",
            shelf = "C-09"
        ),
        SearchBook(
            book = Book(
                id = "15",
                title = "Organic Chemistry",
                author = "Morrison Boyd",
                imageUrl = "https://m.media-amazon.com/images/I/41T5H8u7fUL._SX331_BO1,204,203,200_.jpg"
            ),
            status = BookStatus.Issued,
            info = "Due Jul 02"
        )
    )

    private fun loadInitialData() {
        viewModelScope.launch {
            filterResults()
        }
    }

    fun onEvent(event: LibrarySearchEvent) {
        when (event) {
            is LibrarySearchEvent.OnQueryChange -> {
                _state.update { it.copy(searchQuery = event.query) }
                filterResults()
            }

            is LibrarySearchEvent.OnCategorySelect -> {
                _state.update { it.copy(selectedCategory = event.category) }
                filterResults()
            }

            is LibrarySearchEvent.OnBookClick -> {
                // Handle book click
            }

            LibrarySearchEvent.OnClearQuery -> {
                _state.update { it.copy(searchQuery = "") }
                filterResults()
            }
        }
    }

    private fun filterResults() {
        val query = _state.value.searchQuery.lowercase()
        val category = _state.value.selectedCategory

        val filtered = allBooks.filter { searchBook ->
            val matchesQuery = searchBook.book.title.lowercase().contains(query) ||
                    searchBook.book.author.lowercase().contains(query)

            val matchesCategory = if (category == "All") true else {
                // Mocking category matching
                when (category) {
                    "CS Dept" -> searchBook.book.title.contains("Algorithms") || searchBook.book.title.contains(
                        "Systems"
                    ) || searchBook.book.title.contains("Networks")

                    "Mathematics" -> searchBook.book.title.contains("Mathematics")
                    "Physics" -> searchBook.book.title.contains("Physics")
                    "Chemistry" -> searchBook.book.title.contains("Chemistry")
                    else -> true
                }
            }
            matchesQuery && matchesCategory
        }

        _state.update { it.copy(searchResults = filtered) }
    }
}
