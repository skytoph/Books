package com.skytoph.books.data.database.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skytoph.books.data.database.model.CategoryEntity

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg category: CategoryEntity)

    @Query("SELECT * FROM categories")
    suspend fun categories(): List<CategoryEntity>
}