package com.skytoph.books.ui.nav.books

import kotlinx.serialization.Serializable

@Serializable
sealed interface BooksRoutes {

    @Serializable
    data class Books(
        val categoryId: Int,
        val categoryName: String,
    ) : BooksRoutes

    @Serializable
    data class Categories(
        val title: String,
    ) : BooksRoutes

    @Serializable
    data class Buy(
        val url: String,
        val title: String,
    ) : BooksRoutes
}