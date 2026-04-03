package com.piyushprajpti.feature.library.book_detail

sealed interface LibraryBookDetailEvent {
    data object ReserveBook : LibraryBookDetailEvent
}
