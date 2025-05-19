package com.skytoph.books.ui.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skytoph.books.R
import com.skytoph.books.ui.component.ScaleTransitionDirection
import com.skytoph.books.ui.component.scaleIntoContainer
import com.skytoph.books.ui.component.scaleOutOfContainer
import com.skytoph.books.ui.feature_books.screen.BooksScreen
import com.skytoph.books.ui.feature_buy_book.screen.BuyBookScreen
import com.skytoph.books.ui.feature_categories.screen.CategoriesScreen
import com.skytoph.books.ui.snackbar.SnackbarMessage

@Composable
fun MainNavGraph(controller: NavHostController, showMessage: (SnackbarMessage) -> Unit) {
    NavHost(
        navController = controller,
        startDestination = BooksRoutes.Categories(title = stringResource(R.string.title_categories)),
        modifier = Modifier.Companion.fillMaxSize()
    ) {
        composable<BooksRoutes.Categories>(
            exitTransition = { fadeOut(tween(delayMillis = 60)) },
            popEnterTransition = { fadeIn(tween(delayMillis = 60)) }
        ) {
            CategoriesScreen(navigateToBooks = { category ->
                controller.navigate(route = BooksRoutes.Books(categoryId = category.id, categoryName = category.name))
            })
        }

        composable<BooksRoutes.Books>(
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut(tween(delayMillis = 60))
            },
            popEnterTransition = { fadeIn(tween(delayMillis = 60)) }
        ) {
            BooksScreen(
                navigateUp = controller::navigateUp,
                showMessage = showMessage,
                openLink = { controller.navigate(route = BooksRoutes.Buy(url = it.url, title = it.name)) }
            )
        }

        composable<BooksRoutes.Buy>(
            enterTransition = {
                scaleIntoContainer()
            },
            exitTransition = {
                scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
            },
        ) {
            BuyBookScreen()
        }
    }
}