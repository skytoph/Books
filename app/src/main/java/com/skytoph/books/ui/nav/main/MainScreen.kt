@file:OptIn(ExperimentalMaterial3Api::class)

package com.skytoph.books.ui.nav.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.skytoph.books.R
import com.skytoph.books.ui.appbar.AppBarViewModel
import com.skytoph.books.ui.component.PopupSnackbar
import com.skytoph.books.ui.nav.books.BooksNavGraph
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
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = appBarState.title) },
                navigationIcon = {
                    if (appBarState.canNavigateUp)
                        IconButton(onClick = navController::navigateUp) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.description_navigate_up)
                            )
                        }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                )
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
        ) {
            BooksNavGraph(
                controller = navController,
                showMessage = {
                    coroutineScope.launch { snackbarHostState.showSnackbar(it) }
                },
                updateAppBar = { viewModel.updateAppBar(title = it.title, canNavigateUp = it.canNavigateUp) }
            )
        }
    }
}