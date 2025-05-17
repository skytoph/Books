package com.skytoph.books.data.mapper

import com.skytoph.books.data.database.model.BookEntity
import com.skytoph.books.data.database.model.BuyLinkEntity
import com.skytoph.books.data.network.model.BookNetwork
import com.skytoph.books.domain.model.Book
import com.skytoph.books.domain.model.BookBuyLink
import com.skytoph.books.domain.model.BookInfo

fun BookEntity.mapToDomain(): Book = Book(
    id = isbn13,
    info = BookInfo(
        title = title,
        description = description,
        author = author,
        bookImage = bookImage,
        publisher = publisher
    ),
    buyLinks = buyLinks?.map { BookBuyLink(name = it.name, url = it.url) } ?: emptyList(),
    rank = rank,
    categoryId = category
)

fun Book.mapToEntity(): BookEntity = BookEntity(
    isbn13 = id,
    buyLinks = buyLinks.map { BuyLinkEntity(name = it.name, url = it.url) },
    rank = rank,
    title = info.title,
    description = info.description,
    author = info.author,
    publisher = info.publisher,
    bookImage = info.bookImage,
    category = categoryId
)

fun BookNetwork.mapToDomain(categoryId: Int): Book = Book(
    id = isbn13,
    info = BookInfo(
        title = title,
        description = description,
        author = author,
        bookImage = bookImage,
        publisher = publisher
    ),
    buyLinks = buyLinks?.map { BookBuyLink(name = it.name, url = it.url) } ?: emptyList(),
    rank = rank,
    categoryId = categoryId
)
