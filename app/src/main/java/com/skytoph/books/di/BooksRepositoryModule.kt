package com.skytoph.books.di

import com.skytoph.books.data.database.datasource.BooksLocalDatasource
import com.skytoph.books.data.mapper.CategoryDomainMapper
import com.skytoph.books.data.network.datasource.BooksRemoteDatasource
import com.skytoph.books.data.repository.BooksRepositoryImpl
import com.skytoph.books.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BooksRepositoryModule {

    @Provides
    @Singleton
    fun repository(
        @DispatcherIo dispatcherIO: CoroutineDispatcher,
        localDatasource: BooksLocalDatasource,
        remoteDatasource: BooksRemoteDatasource,
        categoryMapper: CategoryDomainMapper
    ): BooksRepository =
        BooksRepositoryImpl(
            workDispatcher = dispatcherIO,
            localDatasource = localDatasource,
            remoteDatasource = remoteDatasource,
            categoryMapper = categoryMapper
        )
}