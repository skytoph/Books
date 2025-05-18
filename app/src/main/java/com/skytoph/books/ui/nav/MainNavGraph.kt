package com.skytoph.books.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skytoph.books.R
import com.skytoph.books.ui.feature_books.screen.BooksScreen
import com.skytoph.books.ui.feature_categories.screen.CategoriesScreen
import com.skytoph.books.ui.snackbar.SnackbarMessage

@Composable
fun MainNavGraph(controller: NavHostController, showMessage: (SnackbarMessage) -> Unit) {
    NavHost(
        navController = controller,
        startDestination = BooksRoutes.Categories(title = stringResource(R.string.title_categories)),
        modifier = Modifier.Companion.fillMaxSize()
    ) {
        composable<BooksRoutes.Categories> {
            CategoriesScreen(navigateToBooks = { category ->
                controller.navigate(route = BooksRoutes.Books(categoryId = category.id, categoryName = category.name))
            })
        }

        composable<BooksRoutes.Books> {
            BooksScreen(navigateUp = controller::navigateUp, showMessage = showMessage)
        }
    }
}