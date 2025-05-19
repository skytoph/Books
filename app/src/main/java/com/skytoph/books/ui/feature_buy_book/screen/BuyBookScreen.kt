package com.skytoph.books.ui.feature_buy_book.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.skytoph.books.ui.appbar.AppBarState
import com.skytoph.books.ui.feature_buy_book.component.WebView
import com.skytoph.books.ui.feature_buy_book.viewmodel.BuyBookViewModel

@Composable
fun BuyBookScreen(viewModel: BuyBookViewModel = hiltViewModel(), updateAppBar: (AppBarState) -> Unit) {
    val state by viewModel.state.collectAsState()
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START)
                updateAppBar(AppBarState(title = state.linkName, canNavigateUp = true))
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    WebView(
        url = state.url,
        canNavigateBack = state.canNavigateBack,
        handler = { viewModel.updateCanNavigateBack(it) }
    )
}

