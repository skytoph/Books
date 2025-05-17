package com.skytoph.books.data.database.datasource

import com.skytoph.books.data.database.model.BookEntity
import com.skytoph.books.data.database.model.CategoryEntity

interface BooksLocalDatasource {
    suspend fun getCategories(): List<CategoryEntity>
    suspend fun getBooks(categoryId: Int): List<BookEntity>
    suspend fun saveBooks(categories: List<CategoryEntity>, books: List<BookEntity>)
}