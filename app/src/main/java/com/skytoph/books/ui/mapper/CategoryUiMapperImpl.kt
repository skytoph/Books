package com.skytoph.books.ui.mapper

import com.skytoph.books.core.util.ConvertDate
import com.skytoph.books.di.DayMonthConverter
import com.skytoph.books.domain.model.BookCategory
import com.skytoph.books.ui.model.CategoryUi

class CategoryUiMapperImpl(@DayMonthConverter private val converter: ConvertDate) : CategoryUiMapper {

    override fun map(category: BookCategory): CategoryUi = category.run {
        CategoryUi(
            id = id,
            name = name,
            updated = updated?.let { converter.dateToString(updated) }
        )
    }
}