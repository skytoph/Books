package com.skytoph.books.di

import com.skytoph.books.core.util.NetworkExceptionCheck
import com.skytoph.books.domain.repository.BooksRepository
import com.skytoph.books.domain.usecase.GetBooksUseCase
import com.skytoph.books.domain.usecase.GetBooksUseCaseImpl
import com.skytoph.books.domain.usecase.GetCategoriesUseCase
import com.skytoph.books.domain.usecase.GetCategoriesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun categoriesUseCase(repository: BooksRepository, networkMapper: NetworkExceptionCheck): GetCategoriesUseCase =
        GetCategoriesUseCaseImpl(repository = repository, networkMapper = networkMapper)

    @Provides
    @ViewModelScoped
    fun booksUseCase(repository: BooksRepository, networkMapper: NetworkExceptionCheck): GetBooksUseCase =
        GetBooksUseCaseImpl(repository = repository, networkMapper = networkMapper)
}