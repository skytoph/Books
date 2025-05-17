package com.skytoph.books.data.mapper

import com.skytoph.books.data.network.model.CategoryNetwork
import com.skytoph.books.domain.model.BookCategory

interface CategoryDomainMapper {
    fun map(data: List<CategoryNetwork>, updates: Map<String, String?>): List<BookCategory>
}