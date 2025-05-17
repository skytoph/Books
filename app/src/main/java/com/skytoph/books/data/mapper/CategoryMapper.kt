package com.skytoph.books.data.mapper

import com.skytoph.books.data.database.model.CategoryEntity
import com.skytoph.books.data.network.model.CategoryNetwork
import com.skytoph.books.domain.model.BookCategory
import java.util.Date

fun CategoryEntity.mapToDomain(): BookCategory = BookCategory(
    id = id,
    name = name,
    updated = published
)

fun BookCategory.mapToEntity(): CategoryEntity = CategoryEntity(
    id = id,
    name = name,
    published = updated
)

fun CategoryNetwork.mapToDomain(publishedDate: Date?): BookCategory = BookCategory(
    id = this.listId,
    name = this.displayName,
    updated = publishedDate
)