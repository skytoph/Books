package com.skytoph.books.ui.feature_categories.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.skytoph.books.ui.component.ErrorFullscreen
import com.skytoph.books.ui.component.Loading
import com.skytoph.books.ui.feature_categories.state.CategoriesUiState
import com.skytoph.books.ui.feature_categories.state.DataState
import com.skytoph.books.ui.model.CategoryUi
import com.skytoph.books.ui.preview.CategoriesPreviewProvider
import com.skytoph.books.ui.theme.BooksTheme

@Composable
fun CategoriesContent(state: CategoriesUiState, onCategoryClick: (CategoryUi) -> Unit = { }) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (state.data) {
            DataState.Loading -> Loading(modifier = Modifier.align(Alignment.Center))

            is DataState.Success -> CategoriesList(
                categories = state.data.categories,
                onCategoryClick = onCategoryClick
            )

            else -> ErrorFullscreen(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun CategoriesList(categories: List<CategoryUi>, onCategoryClick: (CategoryUi) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = categories) { category ->
            CategoryItem(category = category, onClick = { onCategoryClick(category) })
        }
        item {
            Spacer(Modifier.height(0.dp))
        }
    }
}

@Preview
@Composable
private fun CategoriesPreview(@PreviewParameter(CategoriesPreviewProvider::class) data: List<CategoryUi>) {
    BooksTheme {
        CategoriesContent(state = CategoriesUiState(data = DataState.Success(data)))
    }
}