package com.skytoph.books.ui.feature_books.state

import com.skytoph.books.ui.model.BookUi
import com.skytoph.books.ui.model.LinkUi

data class BooksUiState(
    val data: DataState = DataState.Loading,
    val categoryTitle: String = "",
    val buyBookItemSelected: BookUi? = null,
    val buyBookOpenLink: LinkUi? = null,
)

sealed interface DataState {
    fun expand(expand: Int): DataState = this

    data class Success(val books: List<BookUi>, val expanded: Int? = null) : DataState {
        override fun expand(expand: Int): DataState =
            Success(
                books = books,
                expanded = if (expanded == expand) null else expand
            )
    }

    object Empty : DataState

    object Loading : DataState

    object Error : DataState
}