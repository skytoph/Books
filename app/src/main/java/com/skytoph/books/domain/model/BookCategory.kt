package com.skytoph.books.domain.model

import java.util.Date

data class BookCategory(
    val id: Int = 0,
    val name: String = "",
    val updated: Date? = null
)