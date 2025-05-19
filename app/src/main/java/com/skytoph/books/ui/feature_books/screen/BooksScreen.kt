@file:OptIn(ExperimentalMaterial3Api::class)

package com.skytoph.books.ui.feature_books.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.skytoph.books.ui.feature_books.BooksEvent
import com.skytoph.books.ui.feature_books.component.BooksContent
import com.skytoph.books.ui.feature_books.messages.BooksMessages
import com.skytoph.books.ui.feature_books.state.DataState
import com.skytoph.books.ui.feature_books.viewmodel.BooksViewModel
import com.skytoph.books.ui.feature_buy_book.component.LinksModalBottomSheet
import com.skytoph.books.ui.model.LinkUi
import com.skytoph.books.ui.snackbar.SnackbarMessage

@Composable
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    showMessage: (SnackbarMessage) -> Unit,
    navigateUp: () -> Unit = {},
    openLink: (LinkUi) -> Unit = {}
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initializeAppBar()
    }

    LaunchedEffect(state.data) {
        if (state.data is DataState.Empty) {
            showMessage(BooksMessages.failedToLoadBooks)
            navigateUp()
        }
    }

    BooksContent(
        state = state,
        expand = { viewModel.onEvent(BooksEvent.Expand(it)) },
        buy = { viewModel.onEvent(BooksEvent.UpdateBuyDialog(it)) }
    )

    state.buyBookItemSelected?.let { book ->
        LinksModalBottomSheet(
            links = book.buyLinks,
            onDismissRequest = { viewModel.onEvent(BooksEvent.UpdateBuyDialog()) },
            onLinkClick = { viewModel.onEvent(BooksEvent.UpdateOpenLink(it)) }
        )
    }

    state.buyBookOpenLink?.let { link ->
        viewModel.onEvent(BooksEvent.UpdateOpenLink())
        openLink(link)
    }
}