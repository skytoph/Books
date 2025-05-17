package com.skytoph.books.domain.model

data class Book(
    val id: Long = 0L,
    val info: BookInfo = BookInfo(),
    val buyLinks: List<BookBuyLink> = emptyList(),
    val rank: Int = 0
)