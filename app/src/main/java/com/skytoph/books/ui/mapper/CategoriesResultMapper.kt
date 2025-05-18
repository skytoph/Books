package com.skytoph.books.ui.mapper

import com.skytoph.books.domain.model.BookCategory
import com.skytoph.books.domain.usecase.Result
import com.skytoph.books.ui.feature_categories.state.DataState

fun Result<List<BookCategory>>.mapResult(uiMapper: CategoryUiMapper): DataState = when (this) {
    is Result.Success<List<BookCategory>> ->
        if (data.isEmpty()) DataState.Empty
        else DataState.Success(this.data.map { uiMapper.map(it) })

    Result.NoConnection -> DataState.Error.NoConnection
    Result.ErrorGeneral -> DataState.Error.General
}