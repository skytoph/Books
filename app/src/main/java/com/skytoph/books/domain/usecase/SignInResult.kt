package com.skytoph.books.domain.usecase

sealed interface SignInResult {
    object Success : SignInResult
    object NoConnection : SignInResult
    object NoCredentialsAvailable : SignInResult
    object ErrorGeneral : SignInResult
}