package com.skytoph.books.di

import com.google.gson.Gson
import com.skytoph.books.data.database.converter.LinksConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LinksConverterModule {

    @Provides
    fun converter(gson: Gson): LinksConverter = LinksConverter(gson)
}