package com.skytoph.books.data.network.model


import com.google.gson.annotations.SerializedName

data class BuyLinkNetwork(

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)