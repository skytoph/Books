package com.skytoph.books.ui.feature_books.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.skytoph.books.ui.feature_books.component.BooksContent
import com.skytoph.books.ui.feature_books.messages.BooksMessages
import com.skytoph.books.ui.feature_books.state.DataState
import com.skytoph.books.ui.feature_books.viewmodel.BooksViewModel
import com.skytoph.books.ui.snackbar.SnackbarMessage

@Composable
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    showMessage: (SnackbarMessage) -> Unit,
    navigateUp: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.data) {
        if (state.data is DataState.Empty) {
            showMessage(BooksMessages.failedToLoadBooks)
            navigateUp()
        }
    }

    BooksContent(state = state)
}

