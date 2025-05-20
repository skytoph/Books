package com.skytoph.books.ui.feature_categories

import com.skytoph.books.ui.feature_categories.state.CategoriesUiState
import com.skytoph.books.ui.feature_categories.state.DataState
import kotlinx.coroutines.flow.MutableStateFlow

sealed interface CategoriesEvent {
    fun handle(state: MutableStateFlow<CategoriesUiState>)

    class UpdateData(private val data: DataState) : CategoriesEvent {

        override fun handle(state: MutableStateFlow<CategoriesUiState>) {
            state.value = state.value.copy(data = data)
        }
    }
}