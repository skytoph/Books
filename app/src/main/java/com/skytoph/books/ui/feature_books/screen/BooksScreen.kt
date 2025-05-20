@file:OptIn(ExperimentalMaterial3Api::class)

package com.skytoph.books.ui.feature_books.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.skytoph.books.ui.appbar.AppBarState
import com.skytoph.books.ui.feature_books.component.BooksContent
import com.skytoph.books.ui.feature_books.event.BooksEvent
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
    openLink: (LinkUi) -> Unit = {},
    updateAppBar: (AppBarState) -> Unit
) {
    val state by viewModel.state.collectAsState()

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START)
                updateAppBar(AppBarState(title = state.categoryTitle, canNavigateUp = true))
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(state.data) {
        if (state.data is DataState.Error) {
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