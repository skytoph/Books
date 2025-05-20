package com.skytoph.books.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skytoph.books.R

@Composable
fun ErrorFullscreen(
    modifier: Modifier = Modifier,
    error: String,
    isLoading: Boolean = false,
    retry: (() -> Unit)? = null
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = error,
                modifier = modifier
            )
            retry?.let {
                Button(
                    onClick = retry,
                    enabled = !isLoading
                ) {
                    Text(text = stringResource(R.string.button_label_retry))
                }
            }
        }
    }
}