package com.skytoph.books.di

import com.skytoph.books.core.resources.DateFormatProvider
import com.skytoph.books.core.resources.LocaleProvider
import com.skytoph.books.core.util.ConvertDate
import com.skytoph.books.core.util.ConvertDateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConverterModule {

    @Provides
    fun converter(formatProvider: DateFormatProvider, localeProvider: LocaleProvider): ConvertDate =
        ConvertDateImpl(
            formatProvider = formatProvider,
            localeProvider = localeProvider
        )
}