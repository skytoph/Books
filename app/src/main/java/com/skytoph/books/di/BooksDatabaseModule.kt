package com.skytoph.books.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.skytoph.books.R
import com.skytoph.books.core.util.ConvertDate
import com.skytoph.books.data.database.converter.DateConverter
import com.skytoph.books.data.database.converter.LinksConverter
import com.skytoph.books.data.database.datasource.BooksLocalDatasource
import com.skytoph.books.data.database.datasource.BooksLocalDatasourceImpl
import com.skytoph.books.data.database.datasource.database.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BooksDatabaseModule {

    @Provides
    @Singleton
    fun database(
        @ApplicationContext context: Context,
        @IsoConverter converter: ConvertDate,
        gson: Gson
    ): BooksDatabase =
        Room
            .databaseBuilder(
                context = context,
                name = context.resources.getString(R.string.database_name),
                klass = BooksDatabase::class.java
            )
            .addTypeConverter(LinksConverter(gson = gson))
            .addTypeConverter(DateConverter(converter = converter))
            .fallbackToDestructiveMigration(true)
            .build()


    @Provides
    @Singleton
    fun localDatasource(database: BooksDatabase): BooksLocalDatasource =
        BooksLocalDatasourceImpl(database = database)
}