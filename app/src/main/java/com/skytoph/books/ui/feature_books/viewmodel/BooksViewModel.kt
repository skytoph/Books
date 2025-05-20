package com.skytoph.books.ui.feature_books.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.skytoph.books.domain.usecase.GetBooksUseCase
import com.skytoph.books.ui.feature_books.event.BooksEvent
import com.skytoph.books.ui.feature_books.state.BooksUiState
import com.skytoph.books.ui.mapper.mapResult
import com.skytoph.books.ui.nav.books.BooksRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooks: GetBooksUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route: BooksRoutes.Books = savedStateHandle.toRoute()

    val state: StateFlow<BooksUiState>
        field = MutableStateFlow<BooksUiState>(BooksUiState(categoryTitle = route.categoryName))

    init {
        initializeBooks()
    }

    private fun initializeBooks() {
        state.onStart { onEvent(BooksEvent.UpdateData(getBooks(route.categoryId).mapResult())) }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: BooksEvent) = event.handle(state)
}