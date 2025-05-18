package com.skytoph.books.ui.mapper

import com.skytoph.books.domain.model.Book
import com.skytoph.books.domain.usecase.Result
import com.skytoph.books.ui.feature_books.state.DataState

fun Result<List<Book>>.mapResult(): DataState = when (this) {
    is Result.Success<List<Book>> ->
        if (data.isEmpty()) DataState.Empty
        else DataState.Success(data.map { it.toBookUi() })

    else -> DataState.Error
}