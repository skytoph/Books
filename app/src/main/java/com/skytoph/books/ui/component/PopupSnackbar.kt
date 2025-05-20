package com.skytoph.books.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skytoph.books.R
import com.skytoph.books.ui.snackbar.SnackbarMessage
import com.skytoph.books.ui.theme.BooksTheme

@Composable
fun PopupSnackbar(message: String, modifier: Modifier = Modifier) {
    Snackbar(
        shape = MaterialTheme.shapes.medium,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun PopupSnackbar(message: SnackbarMessage, modifier: Modifier = Modifier) {
    PopupSnackbar(message = stringResource(message.messageResource), modifier = modifier)
}

@Composable
fun PopupSnackbar(message: SnackbarData, modifier: Modifier = Modifier) {
    PopupSnackbar(message = message.visuals.message, modifier = modifier)
}

@Composable
@Preview
private fun SnackbarPreview() {
    BooksTheme {
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            PopupSnackbar(message = SnackbarMessage(messageResource = R.string.fail_loading_books))
        }
    }
}