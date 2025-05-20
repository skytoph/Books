package com.skytoph.books.ui.feature_categories.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.skytoph.books.R
import com.skytoph.books.ui.appbar.AppBarState
import com.skytoph.books.ui.feature_categories.component.CategoriesContent
import com.skytoph.books.ui.feature_categories.viewmodel.CategoriesViewModel
import com.skytoph.books.ui.model.CategoryUi

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel(),
    navigateToBooks: (CategoryUi) -> Unit,
    updateAppBar: (AppBarState) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val appBarTitle = stringResource(R.string.title_categories)
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START)
                updateAppBar(AppBarState(title = appBarTitle, canNavigateUp = false))
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    CategoriesContent(
        state = state,
        onCategoryClick = navigateToBooks,
        loadCategories = { viewModel.updateCategories() })
}