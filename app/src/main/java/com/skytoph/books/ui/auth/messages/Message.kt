package com.skytoph.books.ui.auth.messages

import com.skytoph.books.R
import com.skytoph.books.ui.snackbar.SnackbarMessage

object Message {
    val noConnection = SnackbarMessage(messageResource = R.string.fail_auth_no_connection)
    val noCredentials = SnackbarMessage(messageResource = R.string.fail_auth_no_credentials)
    val generalError = SnackbarMessage(messageResource = R.string.fail_auth_general)
}