package com.skytoph.books.ui.feature_categories.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.skytoph.books.R
import com.skytoph.books.ui.model.CategoryUi
import com.skytoph.books.ui.preview.CategoriesPreviewProvider
import com.skytoph.books.ui.theme.BooksTheme

@Composable
fun CategoryItem(
    category: CategoryUi,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.Companion
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .clickable(onClick = onClick)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.Companion.weight(1f)
            )
            category.updated?.let {
                Spacer(modifier = Modifier.Companion.width(4.dp))
                Column(horizontalAlignment = Alignment.Companion.End) {
                    Text(
                        text = stringResource(R.string.label_category_published),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CategoryPreview(@PreviewParameter(CategoriesPreviewProvider::class) data: List<CategoryUi>) {
    BooksTheme {
        CategoryItem(category = data.first())
    }
}