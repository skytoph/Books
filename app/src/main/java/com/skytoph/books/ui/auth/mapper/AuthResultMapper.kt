package com.skytoph.books.ui.auth.mapper

import com.skytoph.books.domain.usecase.SignInResult
import com.skytoph.books.ui.auth.event.AuthEvent
import com.skytoph.books.ui.auth.state.AuthErrorState

fun SignInResult.mapResult(): AuthEvent = when (this) {
    SignInResult.ErrorGeneral -> AuthEvent.UpdateErrorState(error = AuthErrorState.GeneralError)
    SignInResult.NoConnection -> AuthEvent.UpdateErrorState(error = AuthErrorState.NoConnection)
    SignInResult.NoCredentialsAvailable -> AuthEvent.UpdateErrorState(error = AuthErrorState.NoCredentials)
    SignInResult.Canceled -> AuthEvent.Loading(isLoading = false)
    SignInResult.Success -> AuthEvent.SignedIn(isSignedIn = true)
}