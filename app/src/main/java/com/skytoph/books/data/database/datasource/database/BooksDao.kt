package com.skytoph.books.data.database.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.skytoph.books.data.database.model.BookEntity
import com.skytoph.books.data.database.model.CategoryWithBooks

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg book: BookEntity)

    @Transaction
    @Query("SELECT * FROM books WHERE book_id = :categoryId")
    suspend fun books(categoryId: Int): CategoryWithBooks
}