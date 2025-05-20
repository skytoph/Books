package com.skytoph.books.di

import com.skytoph.books.core.util.NetworkExceptionCheck
import com.skytoph.books.core.util.NetworkExceptionCheckImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun networkExceptionMapper(): NetworkExceptionCheck = NetworkExceptionCheckImpl()
}