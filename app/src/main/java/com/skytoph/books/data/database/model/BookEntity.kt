package com.skytoph.books.data.database.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class BookEntity(

    @PrimaryKey
    @ColumnInfo(name = "book_id")
    val isbn13: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "publisher")
    val publisher: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "book_image")
    val bookImage: String,

    @ColumnInfo(name = "buy_links")
    val buyLinks: List<BuyLinkEntity>?,

    @ColumnInfo(name = "rank")
    val rank: Int,

    @ColumnInfo(name = "category_id")
    val category: Int,
)