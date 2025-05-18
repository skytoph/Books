package com.skytoph.books.ui.feature_categories.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.skytoph.books.ui.feature_categories.component.CategoriesContent
import com.skytoph.books.ui.feature_categories.viewmodel.CategoriesViewModel
import com.skytoph.books.ui.model.CategoryUi

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = hiltViewModel(), navigateToBooks: (CategoryUi) -> Unit) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initializeAppBar()
    }

    CategoriesContent(state = state, onCategoryClick = navigateToBooks)
}