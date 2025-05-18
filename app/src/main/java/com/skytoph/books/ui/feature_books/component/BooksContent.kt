package com.skytoph.books.ui.feature_books.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun BooksContent(state: BooksUiState) {
    Box(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        when (state.data) {
            DataState.Loading -> Loading(modifier = Modifier.Companion.align(Alignment.Companion.Center))
            is DataState.Success -> BooksList(books = state.data.books)
            else -> ErrorFullscreen(modifier = Modifier.Companion.align(Alignment.Companion.Center))
        }
    }
}

@Composable
fun BooksList(books: List<BookUi>) {
    LazyColumn {
        items(books) { category ->
            BookItem(category)
        }
    }
}

@Composable
private fun BookItem(category: BookUi) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.Companion
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = category.title, color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview
@Composable
private fun BooksPreview(@PreviewParameter(BooksPreviewProvider::class) data: List<BookUi>) {
    BooksTheme {
        BooksContent(state = BooksUiState(data = DataState.Success(books = data)))
    }
}