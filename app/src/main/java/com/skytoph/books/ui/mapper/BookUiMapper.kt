package com.skytoph.books.ui.mapper

import com.skytoph.books.domain.model.Book
import com.skytoph.books.ui.model.BookUi
import com.skytoph.books.ui.model.LinkUi

fun Book.toBookUi(): BookUi = BookUi(
    id = id,
    title = info.title,
    description = info.description,
    author = info.author,
    bookImage = info.bookImage,
    publisher = info.publisher,
    rank = rank,
    buyLinks = buyLinks.map { link -> LinkUi(name = link.name, url = link.url) }
)