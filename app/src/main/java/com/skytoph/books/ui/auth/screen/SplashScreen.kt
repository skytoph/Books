package com.skytoph.books.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.skytoph.books.ui.auth.component.SplashContent
import com.skytoph.books.ui.auth.event.AuthEvent
import com.skytoph.books.ui.auth.mapper.map
import com.skytoph.books.ui.auth.viewmodel.AuthViewModel
import com.skytoph.books.ui.component.PopupSnackbar
import com.skytoph.books.ui.snackbar.SnackbarMessage

@Composable
fun SplashScreen(viewModel: AuthViewModel, navigateForward: () -> Unit) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.isSignedIn) {
        if (state.isSignedIn) {
            navigateForward()
        }
    }

    LaunchedEffect(state.errorState) {
        state.errorState?.let { error ->
            snackbarHostState.showSnackbar(error.map())
            viewModel.onEvent(AuthEvent.UpdateErrorState())
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                val visuals = snackbarData.visuals
                if (visuals is SnackbarMessage) PopupSnackbar(visuals)
                else PopupSnackbar(snackbarData)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SplashContent(
                isLoading = state.isLoading,
                signIn = { viewModel.signIn() }
            )
        }
    }
}

