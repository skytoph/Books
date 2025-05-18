package com.skytoph.books.di

import com.skytoph.books.core.util.ConvertDate
import com.skytoph.books.ui.mapper.CategoryUiMapper
import com.skytoph.books.ui.mapper.CategoryUiMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CategoriesViewModelModule {

    @Provides
    fun uiMapper(@DayMonthConverter converter: ConvertDate): CategoryUiMapper =
        CategoryUiMapperImpl(converter = converter)
}