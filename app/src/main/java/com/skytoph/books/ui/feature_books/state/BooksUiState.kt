package com.skytoph.books.ui.feature_books.state

import com.skytoph.books.ui.model.BookUi

data class BooksUiState(
    val data: DataState = DataState.Loading,
    val categoryTitle: String = "",
)

sealed interface DataState {

    data class Success(val books: List<BookUi>) : DataState

    object Empty : DataState

    object Loading : DataState

    object Error : DataState
}