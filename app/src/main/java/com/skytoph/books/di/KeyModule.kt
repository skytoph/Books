package com.skytoph.books.di

import com.skytoph.books.core.key.KeyProvider
import com.skytoph.books.core.key.KeyProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object KeyModule {

    @Provides
    fun keyProvider(): KeyProvider = KeyProviderImpl
}