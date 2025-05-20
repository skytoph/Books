package com.skytoph.books.ui.auth.mapper

import com.skytoph.books.ui.auth.messages.Message
import com.skytoph.books.ui.auth.state.AuthErrorState
import com.skytoph.books.ui.snackbar.SnackbarMessage

fun AuthErrorState.map(): SnackbarMessage = when (this) {
    AuthErrorState.GeneralError -> Message.generalError
    AuthErrorState.NoConnection -> Message.noConnection
    AuthErrorState.NoCredentials -> Message.noCredentials
}