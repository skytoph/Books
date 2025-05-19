package com.skytoph.books.ui.feature_books.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.skytoph.books.ui.component.ErrorFullscreen
import com.skytoph.books.ui.component.Loading
import com.skytoph.books.ui.feature_books.state.BooksUiState
import com.skytoph.books.ui.feature_books.state.DataState
import com.skytoph.books.ui.model.BookUi
import com.skytoph.books.ui.preview.BooksPreviewProvider
import com.skytoph.books.ui.theme.BooksTheme

@Composable
fun BooksContent(
    state: BooksUiState,
    expand: (Int) -> Unit = {},
    buy: (BookUi) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
    ) {
        when (state.data) {
            DataState.Loading -> Loading(modifier = Modifier.align(Alignment.Center))

            is DataState.Success -> BooksList(
                books = state.data.books,
                expandedIndex = state.data.expanded,
                expand = expand,
                buy = buy
            )

            else -> ErrorFullscreen(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun BooksList(
    books: List<BookUi>,
    expandedIndex: Int? = 0,
    expand: (Int) -> Unit = {},
    buy: (BookUi) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(items = books) { index, book ->
            BookItem(
                book = book,
                isExpanded = expandedIndex == index,
                onExpand = { expand(index) },
                onBuy = { buy(book) }
            )
        }
    }
}

@Preview
@Composable
private fun BooksPreview(@PreviewParameter(BooksPreviewProvider::class) data: List<BookUi>) {
    BooksTheme {
        BooksContent(state = BooksUiState(data = DataState.Success(books = data)))
    }
}