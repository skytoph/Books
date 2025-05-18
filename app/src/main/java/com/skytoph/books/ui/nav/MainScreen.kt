package com.skytoph.books.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.skytoph.books.ui.appbar.AppBarViewModel
import com.skytoph.books.ui.component.AppBar
import com.skytoph.books.ui.component.PopupSnackbar
import com.skytoph.books.ui.snackbar.SnackbarMessage
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AppBarViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val appBarState by viewModel.appBarState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                state = appBarState,
                navigateUp = navController::navigateUp,
                modifier = Modifier.statusBarsPadding()
            )
        },
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
                .consumeWindowInsets(innerPadding)
        ) {
            MainNavGraph(controller = navController, showMessage = {
                coroutineScope.launch { snackbarHostState.showSnackbar(it) }
            })
        }
    }
}