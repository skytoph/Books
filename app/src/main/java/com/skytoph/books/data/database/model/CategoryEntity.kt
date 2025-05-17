package com.skytoph.books.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "categories")
data class CategoryEntity(

    @PrimaryKey
    @ColumnInfo(name = "category_id")
    val id: Int,

    @ColumnInfo(name = "category_name")
    val name: String,

    @ColumnInfo(name = "published_date")
    val published: Date?
)