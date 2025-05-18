package com.skytoph.books.ui.feature_categories.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
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
    LazyColumn {
        items(items = categories) { category ->
            CategoryItem(category = category, onCategoryClick = { onCategoryClick(category) })
        }
    }
}

@Composable
fun CategoryItem(category: CategoryUi, onCategoryClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCategoryClick)
            .padding(16.dp)
    ) {
        Text(text = category.name)
        category.updated?.let { Text(text = it) }
    }
}

@Preview
@Composable
private fun CategoriesPreview(@PreviewParameter(CategoriesPreviewProvider::class) data: List<CategoryUi>) {
    BooksTheme {
        CategoriesContent(state = CategoriesUiState(data = DataState.Success(data)))
    }
}