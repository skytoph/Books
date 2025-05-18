package com.skytoph.books.di

import android.content.Context
import com.skytoph.books.core.resources.DateFormatProvider
import com.skytoph.books.core.resources.DateFormatProviderImpl
import com.skytoph.books.core.resources.LocaleProvider
import com.skytoph.books.core.resources.LocaleProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ResourcesModule {

    @Provides
    fun locale(): LocaleProvider = LocaleProviderImpl()

    @Provides
    fun dateFormat(@ApplicationContext context: Context): DateFormatProvider =
        DateFormatProviderImpl(context.resources)
}