package com.skytoph.books.ui.feature_books.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.skytoph.books.R
import com.skytoph.books.ui.model.BookUi
import com.skytoph.books.ui.preview.BooksPreviewProvider
import com.skytoph.books.ui.theme.BooksTheme

@Composable
fun BookItem(
    book: BookUi,
    isExpanded: Boolean = false,
    onExpand: () -> Unit = {},
    onBuy: () -> Unit = {},
) {
    Card(
        modifier = Modifier.Companion
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.Companion
                .animateContentSize()
                .clip(MaterialTheme.shapes.medium)
                .clickable(
                    onClick = onExpand,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                )
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.Companion
                    .background(color = Color.Companion.White, shape = MaterialTheme.shapes.extraSmall)
                    .size(width = 88.dp, height = 120.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(book.bookImage)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Companion.Fit,
                    contentDescription = book.title,
                    placeholder = painterResource(R.drawable.book),
                    modifier = Modifier.Companion
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.Companion.width(16.dp))

            Column {
                Row {
                    Column(
                        modifier = Modifier.Companion
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = stringResource(R.string.book_label_author, book.author),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stringResource(R.string.book_label_publisher, book.publisher),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stringResource(R.string.book_label_rank, book.rank),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Button(
                        onClick = onBuy,
                        shape = MaterialTheme.shapes.medium,
                        enabled = book.buyLinks.isNotEmpty()
                    ) {
                        Text(stringResource(R.string.book_label_button_buy))
                    }
                }

                Crossfade(
                    targetState = isExpanded,
                    label = stringResource(R.string.label_crossfade_book_description)
                ) { isExpanded ->
                    Text(
                        text = book.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                        overflow = TextOverflow.Companion.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BooksPreview(@PreviewParameter(BooksPreviewProvider::class) data: List<BookUi>) {
    BooksTheme {
        BookItem(book = data.first())
    }
}