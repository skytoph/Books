package com.skytoph.books.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.skytoph.books.ui.model.BookUi
import com.skytoph.books.ui.model.LinkUi

class BooksPreviewProvider : PreviewParameterProvider<List<BookUi>> {
    override val values: Sequence<List<BookUi>> = sequenceOf(
        List(10) {
            BookUi(
                id = "book_$it",
                title = "Book Title $it",
                description = "Description for book $it",
                author = "Author $it",
                bookImage = "https://example.com/book_$it.jpg",
                publisher = "Publisher $it",
                rank = it + 1,
                buyLinks = listOf(
                    LinkUi(name = "Amazon"),
                    LinkUi(name = "Barnes & Noble")
                )
            )
        }
    )
}

