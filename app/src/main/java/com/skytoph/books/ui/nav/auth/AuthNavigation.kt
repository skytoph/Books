package com.skytoph.books.ui.nav.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.skytoph.books.ui.feature_sign_in.screen.SplashScreen
import com.skytoph.books.ui.feature_sign_in.viewmodel.AuthViewModel
import com.skytoph.books.ui.nav.Graph

fun NavGraphBuilder.authNavigation(controller: NavHostController, viewModel: AuthViewModel) {
    navigation<Graph.Auth>(startDestination = AuthRoutes.Splash) {
        composable<AuthRoutes.Splash> {
            SplashScreen(
                viewModel = viewModel,
                navigateForward = {
                    controller.popBackStack(controller.graph.startDestinationId, true)
                    controller.navigate(Graph.Books)
                }
            )
        }
    }
}