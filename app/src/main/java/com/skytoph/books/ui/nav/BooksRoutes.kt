package com.skytoph.books.ui.nav

import kotlinx.serialization.Serializable

@Serializable
sealed interface BooksRoutes {

    @Serializable
    data class Books(
        val categoryId: Int,
        val categoryName: String,
        val canNavigateBack: Boolean = true
    ) : BooksRoutes

    @Serializable
    data class Categories(
        val title: String,
        val canNavigateBack: Boolean = false
    ) : BooksRoutes

    @Serializable
    data class Buy(
        val url: String,
        val title: String,
    ) : BooksRoutes
}