package com.skytoph.books.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skytoph.books.ui.feature_sign_in.viewmodel.AuthViewModel
import com.skytoph.books.ui.nav.auth.authNavigation

@Composable
fun BooksApp(
    navController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = if (viewModel.isSignedIn()) Graph.Books else Graph.Auth
    ) {
        authNavigation(controller = navController, viewModel = viewModel)
        composable<Graph.Books> {
            MainScreen()
        }
    }
}