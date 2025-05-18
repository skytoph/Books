package com.skytoph.books.ui.model

data class BookUi(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val author: String = "",
    val bookImage: String = "",
    val publisher: String = "",
    val rank: Int = 0,
    val buyLinks: List<LinkUi> = emptyList()
)

data class LinkUi(
    val name: String = "",
    val url: String = ""
)

