package com.skytoph.books.ui.feature_categories.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.skytoph.books.domain.usecase.GetCategoriesUseCase
import com.skytoph.books.ui.appbar.InitAppBar
import com.skytoph.books.ui.feature_categories.state.CategoriesUiState
import com.skytoph.books.ui.mapper.CategoryUiMapper
import com.skytoph.books.ui.mapper.mapResult
import com.skytoph.books.ui.nav.BooksRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategories: GetCategoriesUseCase,
    private val mapper: CategoryUiMapper,
    private val appBar: InitAppBar,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state: StateFlow<CategoriesUiState>
        field = MutableStateFlow<CategoriesUiState>(CategoriesUiState())

    val route: BooksRoutes.Categories = savedStateHandle.toRoute()

    init {
        initializeCategories()
    }

    fun initializeAppBar() {
        appBar.initAppBar(title = route.title, canNavigateUp = route.canNavigateBack)
    }

    private fun initializeCategories() {
        state.onStart {
            state.value = state.value.copy(data = getCategories().mapResult(mapper))
        }.launchIn(viewModelScope)
    }
}