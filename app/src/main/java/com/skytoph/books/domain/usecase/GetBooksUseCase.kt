package com.skytoph.books.domain.usecase

import com.skytoph.books.core.util.NetworkExceptionCheck
import com.skytoph.books.domain.model.Book
import com.skytoph.books.domain.repository.BooksRepository

interface GetBooksUseCase {
    suspend operator fun invoke(categoryId: Int): Result<List<Book>>
}

class GetBooksUseCaseImpl(
    private val repository: BooksRepository,
    networkMapper: NetworkExceptionCheck
) : GetBooksUseCase, GetDataUseCaseAbstract<List<Book>>(networkMapper) {

    override suspend fun invoke(categoryId: Int): Result<List<Book>> = handle {
        repository.getBooks(categoryId)
    }
}

