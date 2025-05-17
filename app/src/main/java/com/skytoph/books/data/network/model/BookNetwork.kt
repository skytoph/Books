package com.skytoph.books.data.network.model


import com.google.gson.annotations.SerializedName

data class BookNetwork(

    @SerializedName("author")
    val author: String,

    @SerializedName("book_image")
    val bookImage: String,

    @SerializedName("buy_links")
    val buyLinks: List<BuyLinkNetwork>?,

    @SerializedName("description")
    val description: String,

    @SerializedName("publisher")
    val publisher: String,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("primary_isbn13")
    val isbn13: String,
)