package com.skytoph.books.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.skytoph.books.ui.model.CategoryUi

class CategoriesPreviewProvider : PreviewParameterProvider<List<CategoryUi>> {
    override val values: Sequence<List<CategoryUi>> = sequenceOf(
        List(10) {
            CategoryUi(
                id = it,
                name = "Category $it",
                updated = "$it.05"
            )
        }
    )
}