package com.skytoph.books.ui.feature_books.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.skytoph.books.R
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
    Crossfade(targetState = state.data) { data ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (data) {
                DataState.Loading -> Loading(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                )

                is DataState.Success -> BooksList(
                    books = data.books,
                    expandedIndex = data.expanded,
                    expand = expand,
                    buy = buy
                )

                DataState.Empty -> ErrorFullscreen(
                    modifier = Modifier.align(Alignment.Center),
                    error = stringResource(R.string.fail_books_list_empty)
                )

                DataState.Error -> {}
            }
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
        item {
            Spacer(Modifier.height(0.dp))
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