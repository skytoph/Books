package com.skytoph.books.data.network.datasource

import com.skytoph.books.data.network.model.CategoryNetwork
import com.skytoph.books.data.network.model.CategoryResultsNetwork

interface BooksRemoteDatasource {
    suspend fun fetchBooksWithCategories(): List<CategoryNetwork>
    suspend fun fetchCategoryDetails(category: String): CategoryResultsNetwork?
}