package com.skytoph.books.ui.feature_books

import com.skytoph.books.ui.feature_books.state.BooksUiState
import com.skytoph.books.ui.feature_books.state.DataState
import com.skytoph.books.ui.model.BookUi
import com.skytoph.books.ui.model.LinkUi
import kotlinx.coroutines.flow.MutableStateFlow

sealed interface BooksEvent {
    fun handle(state: MutableStateFlow<BooksUiState>)

    class UpdateData(val data: DataState) : BooksEvent {
        override fun handle(state: MutableStateFlow<BooksUiState>) {
            state.value = state.value.copy(data = data)
        }
    }

    class Expand(val index: Int) : BooksEvent {
        override fun handle(state: MutableStateFlow<BooksUiState>) {
            state.value = state.value.copy(data = state.value.data.expand(index))
        }
    }

    class UpdateBuyDialog(val book: BookUi? = null) : BooksEvent {
        override fun handle(state: MutableStateFlow<BooksUiState>) {
            state.value = state.value.copy(buyBookItemSelected = book)
        }
    }

    class UpdateOpenLink(val link: LinkUi? = null) : BooksEvent {
        override fun handle(state: MutableStateFlow<BooksUiState>) {
            state.value = state.value.copy(buyBookOpenLink = link)
        }
    }
}