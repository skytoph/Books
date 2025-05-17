package com.skytoph.books.data.network.service

import com.skytoph.books.data.network.model.BooksResponseNetwork
import com.skytoph.books.data.network.model.CategoryResponseNetwork
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksService {

    @GET("lists/overview.json")
    suspend fun books(
        @Query(value = "api-key") apiKey: String
    ): BooksResponseNetwork

    @GET("lists/{category}.json")
    suspend fun categories(
        @Path("category") category: String,
        @Query(value = "api-key") apiKey: String,
    ): CategoryResponseNetwork
}