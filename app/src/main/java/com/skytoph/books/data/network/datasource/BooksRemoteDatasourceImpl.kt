package com.skytoph.books.data.network.datasource

import com.skytoph.books.core.key.KeyProvider
import com.skytoph.books.data.network.model.CategoryNetwork
import com.skytoph.books.data.network.model.CategoryResultsNetwork
import com.skytoph.books.data.network.service.BooksService

class BooksRemoteDatasourceImpl(
    private val service: BooksService,
    private val keyProvider: KeyProvider
) : BooksRemoteDatasource {

    override suspend fun fetchBooksWithCategories(): List<CategoryNetwork> =
        service.books(apiKey = keyProvider.booksApiKey).results?.lists ?: emptyList()

    override suspend fun fetchCategoryDetails(category: String): CategoryResultsNetwork? =
        service.categories(apiKey = keyProvider.booksApiKey, category = category).results
}