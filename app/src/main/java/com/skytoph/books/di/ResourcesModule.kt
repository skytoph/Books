package com.skytoph.books.di

import android.content.Context
import com.skytoph.books.core.resources.DateFormatDayMonth
import com.skytoph.books.core.resources.DateFormatIso
import com.skytoph.books.core.resources.DateFormatProvider
import com.skytoph.books.core.resources.LocaleProvider
import com.skytoph.books.core.resources.LocaleProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IsoFormat

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DayMonthFormat

@Module
@InstallIn(SingletonComponent::class)
object ResourcesModule {

    @Provides
    fun locale(): LocaleProvider = LocaleProviderImpl()

    @Provides
    @IsoFormat
    fun dateFormat(@ApplicationContext context: Context): DateFormatProvider =
        DateFormatIso(context.resources)

    @Provides
    @DayMonthFormat
    fun dayMonthFormat(@ApplicationContext context: Context): DateFormatProvider =
        DateFormatDayMonth(context.resources)
}