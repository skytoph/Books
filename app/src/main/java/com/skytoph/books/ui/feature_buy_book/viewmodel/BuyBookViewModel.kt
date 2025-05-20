package com.skytoph.books.ui.feature_buy_book.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.skytoph.books.ui.feature_buy_book.state.BuyBookState
import com.skytoph.books.ui.nav.books.BooksRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BuyBookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route: BooksRoutes.Buy = savedStateHandle.toRoute()

    val state: StateFlow<BuyBookState>
        field = MutableStateFlow<BuyBookState>(BuyBookState(url = route.url, linkName = route.title))

    fun updateCanNavigateBack(canNavigateBackHandled: Boolean) {
        state.value = state.value.copy(canNavigateBack = canNavigateBackHandled)
    }
}