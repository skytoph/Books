package com.skytoph.books.data.network.model

import com.google.gson.annotations.SerializedName

data class BooksResponseNetwork(

    @SerializedName("copyright")
    val copyright: String,

    @SerializedName("num_results")
    val numResults: Int,

    @SerializedName("results")
    val results: ResultsNetwork?,

    @SerializedName("status")
    val status: String
)