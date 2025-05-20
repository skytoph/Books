package com.skytoph.books.ui.feature_sign_in.mapper

import com.skytoph.books.ui.feature_sign_in.messages.Message
import com.skytoph.books.ui.feature_sign_in.state.AuthErrorState
import com.skytoph.books.ui.snackbar.SnackbarMessage

fun AuthErrorState.map(): SnackbarMessage = when (this) {
    AuthErrorState.GeneralError -> Message.generalError
    AuthErrorState.NoConnection -> Message.noConnection
    AuthErrorState.NoCredentials -> Message.noCredentials
}