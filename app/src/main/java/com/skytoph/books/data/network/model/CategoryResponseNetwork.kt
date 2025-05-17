package com.skytoph.books.data.network.model

import com.google.gson.annotations.SerializedName

data class CategoryResponseNetwork(

    @SerializedName("results")
    val results: CategoryResultsNetwork?,

    @SerializedName("status")
    val status: String
)

data class CategoryResultsNetwork(

    @SerializedName("published_date")
    val publishedDate: String
)