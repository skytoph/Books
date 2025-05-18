package com.skytoph.books.di

import com.skytoph.books.core.resources.DateFormatProvider
import com.skytoph.books.core.resources.LocaleProvider
import com.skytoph.books.core.util.ConvertDate
import com.skytoph.books.core.util.ConvertDateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IsoConverter

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DayMonthConverter

@Module
@InstallIn(SingletonComponent::class)
object ConverterModule {

    @Provides
    @IsoConverter
    fun converterIso(@IsoFormat formatProvider: DateFormatProvider, localeProvider: LocaleProvider): ConvertDate =
        ConvertDateImpl(
            formatProvider = formatProvider,
            localeProvider = localeProvider
        )

    @Provides
    @DayMonthConverter
    fun converterDayMonth(
        @DayMonthFormat formatProvider: DateFormatProvider,
        localeProvider: LocaleProvider
    ): ConvertDate =
        ConvertDateImpl(
            formatProvider = formatProvider,
            localeProvider = localeProvider
        )
}