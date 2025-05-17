package com.skytoph.books.domain.model

data class Book(
    val id: String = "",
    val info: BookInfo = BookInfo(),
    val buyLinks: List<BookBuyLink> = emptyList(),
    val rank: Int = 0,
    val categoryId: Int
)