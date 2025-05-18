package com.skytoph.books.ui.feature_categories.state

import com.skytoph.books.ui.model.CategoryUi

data class CategoriesUiState(
    val data: DataState = DataState.Loading
)

sealed interface DataState {

    data class Success(val categories: List<CategoryUi>) : DataState

    object Loading : DataState

    object Empty : DataState

    sealed interface Error : DataState {

        object NoConnection : Error

        object General : Error
    }
}


