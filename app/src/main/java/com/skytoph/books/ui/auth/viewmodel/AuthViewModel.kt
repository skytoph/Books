package com.skytoph.books.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skytoph.books.domain.auth.IsSignedIn
import com.skytoph.books.domain.usecase.CheckAuthStateUseCase
import com.skytoph.books.domain.usecase.SignInUseCase
import com.skytoph.books.ui.auth.event.AuthEvent
import com.skytoph.books.ui.auth.mapper.mapResult
import com.skytoph.books.ui.auth.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithGoogle: SignInUseCase,
    private val isSignedIn: CheckAuthStateUseCase,
) : ViewModel(), IsSignedIn {

    val state: StateFlow<AuthState>
        field = MutableStateFlow<AuthState>(AuthState(isSignedIn = isSignedIn()))

    override fun isSignedIn(): Boolean = isSignedIn.invoke()

    fun onEvent(event: AuthEvent) {
        event.handle(state)
    }

    fun signIn() = viewModelScope.launch {
        onEvent(AuthEvent.Loading(true))
        onEvent(signInWithGoogle().mapResult())
    }
}