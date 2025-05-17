package com.skytoph.books.data.database.datasource

import androidx.room.withTransaction
import com.skytoph.books.data.database.datasource.database.BooksDatabase
import com.skytoph.books.data.database.model.BookEntity
import com.skytoph.books.data.database.model.CategoryEntity

class BooksLocalDatasourceImpl(
    private val database: BooksDatabase
) : BooksLocalDatasource {

    override suspend fun getCategories(): List<CategoryEntity> =
        database.categoriesDao().categories()

    override suspend fun getBooks(categoryId: String): List<BookEntity> =
        database.booksDao().books(categoryId).books

    override suspend fun saveBooks(categories: List<CategoryEntity>, books: List<BookEntity>) {
        database.withTransaction {
            database.categoriesDao().insert(*categories.toTypedArray())
            database.booksDao().insert(*books.toTypedArray())
        }
    }
}