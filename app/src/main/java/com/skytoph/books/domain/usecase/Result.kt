package com.skytoph.books.domain.usecase

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    object NoConnection : Result<Nothing>
    object ErrorGeneral : Result<Nothing>
}