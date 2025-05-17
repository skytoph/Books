package com.skytoph.books.data.repository

import androidx.compose.ui.util.fastDistinctBy
import com.skytoph.books.data.database.datasource.BooksLocalDatasource
import com.skytoph.books.data.database.model.BookEntity
import com.skytoph.books.data.database.model.CategoryEntity
import com.skytoph.books.data.mapper.CategoryDomainMapper
import com.skytoph.books.data.mapper.mapToDomain
import com.skytoph.books.data.mapper.mapToEntity
import com.skytoph.books.data.network.datasource.BooksRemoteDatasource
import com.skytoph.books.data.network.model.CategoryNetwork
import com.skytoph.books.di.DispatcherIo
import com.skytoph.books.domain.model.Book
import com.skytoph.books.domain.model.BookCategory
import com.skytoph.books.domain.repository.BooksRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class BooksRepositoryImpl(
    @DispatcherIo private val workDispatcher: CoroutineDispatcher,
    private val localDatasource: BooksLocalDatasource,
    private val remoteDatasource: BooksRemoteDatasource,
    private val categoryMapper: CategoryDomainMapper
) : BooksRepository {

    override suspend fun getCategories(): List<BookCategory> = withContext(workDispatcher) {
        val categories: List<CategoryEntity> = localDatasource.getCategories().ifEmpty {
            fetchBooks()
            localDatasource.getCategories()
        }
        categories.map { async { it.mapToDomain() } }.awaitAll()
    }

    override suspend fun getBooks(categoryId: Int): List<Book> = withContext(workDispatcher) {
        localDatasource.getBooks(categoryId).map { async { it.mapToDomain() } }.awaitAll()
    }

    private suspend fun fetchBooks() = coroutineScope {
        val networkData = remoteDatasource.fetchBooksWithCategories()

        val categoriesUpdates = networkData.fastDistinctBy { it.updated }
            .map { category -> async { categoryTypeToDate(category) } }

        val books = networkData.map { category -> async { categoryToDomain(category) } }.awaitAll().flatMap { it }

        val categories = categoryMapper.map(networkData, categoriesUpdates.awaitAll().toMap())
            .map { async { it.mapToEntity() } }

        localDatasource.saveBooks(categories = categories.awaitAll(), books = books)
    }

    private suspend fun categoryToDomain(category: CategoryNetwork): List<BookEntity> = coroutineScope {
        category.books?.map { book ->
            async { book.mapToDomain(categoryId = category.listId).mapToEntity() }
        }?.awaitAll() ?: emptyList()
    }

    private suspend fun categoryTypeToDate(category: CategoryNetwork): Pair<String, String?> =
        category.updated to remoteDatasource.fetchCategoryDetails(category.encodedName)?.publishedDate
}
