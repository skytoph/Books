package com.skytoph.books.ui.feature_sign_in.event

import com.skytoph.books.ui.feature_sign_in.state.AuthErrorState
import com.skytoph.books.ui.feature_sign_in.state.AuthState
import kotlinx.coroutines.flow.MutableStateFlow

sealed interface AuthEvent {
    fun handle(state: MutableStateFlow<AuthState>)

    class SignedIn(private val isSignedIn: Boolean) : AuthEvent {
        override fun handle(state: MutableStateFlow<AuthState>) {
            state.value = state.value.copy(isSignedIn = isSignedIn, isLoading = false)
        }
    }

    class Loading(private val isLoading: Boolean) : AuthEvent {
        override fun handle(state: MutableStateFlow<AuthState>) {
            state.value = state.value.copy(isLoading = isLoading)
        }
    }

    class UpdateErrorState(private val error: AuthErrorState? = null) : AuthEvent {
        override fun handle(state: MutableStateFlow<AuthState>) {
            state.value = state.value.copy(errorState = error, isLoading = false)
        }
    }
}