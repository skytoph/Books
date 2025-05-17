package com.skytoph.books.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithBooks(

    @Embedded
    val category: CategoryEntity,

    @Relation(
        parentColumn = "category_id",
        entityColumn = "category_id",
    )
    val books: List<BookEntity>
)