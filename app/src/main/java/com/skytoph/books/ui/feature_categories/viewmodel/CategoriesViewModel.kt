package com.skytoph.books.ui.feature_categories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skytoph.books.domain.usecase.GetCategoriesUseCase
import com.skytoph.books.ui.feature_categories.state.CategoriesUiState
import com.skytoph.books.ui.mapper.CategoryUiMapper
import com.skytoph.books.ui.mapper.mapResult
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
) : ViewModel() {

    val state: StateFlow<CategoriesUiState>
        field = MutableStateFlow<CategoriesUiState>(CategoriesUiState())

    init {
        initializeCategories()
    }

    private fun initializeCategories() {
        state.onStart {
            state.value = state.value.copy(data = getCategories().mapResult(mapper))
        }.launchIn(viewModelScope)
    }
}