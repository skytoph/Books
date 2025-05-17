package com.skytoph.books.data.mapper

import com.skytoph.books.core.util.ConvertDate
import com.skytoph.books.data.network.model.CategoryNetwork
import com.skytoph.books.domain.model.BookCategory

class CategoryDomainMapperImpl(private val converter: ConvertDate) : CategoryDomainMapper {

    override fun map(data: List<CategoryNetwork>, updates: Map<String, String?>): List<BookCategory> =
        data.map { category ->
            category.mapToDomain(updates[category.updated]?.let { converter.stringToDate(it) })
        }
}