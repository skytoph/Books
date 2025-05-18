package com.skytoph.books.domain.usecase

import com.skytoph.books.core.util.NetworkExceptionCheck
import com.skytoph.books.domain.model.BookCategory
import com.skytoph.books.domain.repository.BooksRepository

interface GetCategoriesUseCase {
    suspend operator fun invoke(): Result<List<BookCategory>>
}

class GetCategoriesUseCaseImpl(
    private val repository: BooksRepository,
    networkMapper: NetworkExceptionCheck
) : GetCategoriesUseCase, GetDataUseCaseAbstract<List<BookCategory>>(networkMapper) {

    override suspend fun invoke(): Result<List<BookCategory>> = handle {
        repository.getCategories()
    }
}

