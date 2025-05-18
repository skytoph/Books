package com.skytoph.books.di

import android.content.Context
import com.skytoph.books.R
import com.skytoph.books.core.key.KeyProvider
import com.skytoph.books.core.util.ConvertDate
import com.skytoph.books.data.mapper.CategoryDomainMapper
import com.skytoph.books.data.mapper.CategoryDomainMapperImpl
import com.skytoph.books.data.network.datasource.BooksRemoteDatasource
import com.skytoph.books.data.network.datasource.BooksRemoteDatasourceImpl
import com.skytoph.books.data.network.service.BooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BooksNetworkModule {

    @Provides
    fun retrofit(@ApplicationContext context: Context): Retrofit =
        Retrofit.Builder()
            .baseUrl(context.getString(R.string.books_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .apply {
                        addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    }.build()
            )
            .build()

    @Provides
    @Singleton
    fun remoteDatasource(retrofit: Retrofit, keyProvider: KeyProvider): BooksRemoteDatasource =
        BooksRemoteDatasourceImpl(
            service = retrofit.create(BooksService::class.java),
            keyProvider = keyProvider
        )

    @Provides
    fun categoryMapper(converter: ConvertDate): CategoryDomainMapper =
        CategoryDomainMapperImpl(converter = converter)
}