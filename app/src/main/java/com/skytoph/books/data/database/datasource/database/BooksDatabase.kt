package com.skytoph.books.data.database.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skytoph.books.data.database.model.BookEntity
import com.skytoph.books.data.database.model.CategoryEntity

@Database(entities = [CategoryEntity::class, BookEntity::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    abstract fun categoriesDao(): CategoriesDao
}