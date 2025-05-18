package com.skytoph.books.data.database.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.skytoph.books.data.database.converter.DateConverter
import com.skytoph.books.data.database.converter.LinksConverter
import com.skytoph.books.data.database.model.BookEntity
import com.skytoph.books.data.database.model.CategoryEntity

@Database(entities = [CategoryEntity::class, BookEntity::class], version = 1)
@TypeConverters(DateConverter::class, LinksConverter::class)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    abstract fun categoriesDao(): CategoriesDao
}