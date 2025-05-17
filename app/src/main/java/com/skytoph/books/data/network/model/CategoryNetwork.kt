package com.skytoph.books.data.network.model

import com.google.gson.annotations.SerializedName

data class CategoryNetwork(

    @SerializedName("books")
    val books: List<BookNetwork>?,

    @SerializedName("display_name")
    val displayName: String,

    @SerializedName("list_name_encoded")
    val encodedName: String,

    @SerializedName("updated")
    val updated: String,

    @SerializedName("list_id")
    val listId: Int,
)