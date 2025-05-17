package com.skytoph.books.data.network.model

import com.google.gson.annotations.SerializedName

data class ResultsNetwork(

    @SerializedName("lists")
    val lists: List<CategoryNetwork>?,

    @SerializedName("published_date")
    val publishedDate: String
)