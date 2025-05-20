package com.skytoph.books.ui.feature_sign_in.state

data class AuthState(
    val isSignedIn: Boolean,
    val isLoading: Boolean = false,
    val errorState: AuthErrorState? = null
)

sealed interface AuthErrorState {

    object NoConnection : AuthErrorState

    object NoCredentials : AuthErrorState

    object GeneralError : AuthErrorState
}