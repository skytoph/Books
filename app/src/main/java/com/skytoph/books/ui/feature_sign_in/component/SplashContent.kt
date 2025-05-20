package com.skytoph.books.ui.feature_sign_in.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skytoph.books.R
import com.skytoph.books.ui.component.Loading
import com.skytoph.books.ui.theme.BooksTheme

@Composable
fun SplashContent(isLoading: Boolean = false, signIn: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.large),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.book),
                    contentDescription = stringResource(R.string.label_icon_logo),
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            Text(
                text = stringResource(R.string.splash_label),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            if (isLoading)
                Loading(modifier = Modifier.size(40.dp))
            else
                Spacer(modifier = Modifier.size(80.dp))
        }
        Button(
            onClick = signIn,
            enabled = !isLoading,
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = stringResource(R.string.button_sign_in_with_google),
            )
        }
    }
}

@Preview
@Composable
private fun SplashPreview() {
    BooksTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SplashContent(isLoading = true)
        }
    }
}