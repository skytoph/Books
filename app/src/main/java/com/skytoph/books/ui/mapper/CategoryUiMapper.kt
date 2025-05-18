package com.skytoph.books.ui.mapper

import com.skytoph.books.domain.model.BookCategory
import com.skytoph.books.ui.model.CategoryUi

interface CategoryUiMapper {
    fun map(category: BookCategory): CategoryUi
}

