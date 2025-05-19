package com.skytoph.books.ui.feature_buy_book.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.skytoph.books.ui.feature_buy_book.component.WebView
import com.skytoph.books.ui.feature_buy_book.viewmodel.BuyBookViewModel

@Composable
fun BuyBookScreen(viewModel: BuyBookViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    WebView(
        url = state.url,
        canNavigateBack = state.canNavigateBack,
        handler = { viewModel.updateCanNavigateBack(it) }
    )
}

