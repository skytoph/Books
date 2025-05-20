package com.skytoph.books.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatcherIo

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineDispatcherModule {

    @Provides
    @DispatcherIo
    fun dispatcherIo(): CoroutineDispatcher = Dispatchers.IO
}