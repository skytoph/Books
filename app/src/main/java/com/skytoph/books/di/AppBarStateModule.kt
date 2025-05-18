package com.skytoph.books.di

import com.skytoph.books.ui.appbar.AppBarState
import com.skytoph.books.ui.appbar.InitAppBar
import com.skytoph.books.ui.appbar.InitAppBarImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppBarStateModule {

    @Provides
    @Singleton
    fun state(): MutableStateFlow<AppBarState> = MutableStateFlow(AppBarState())

    @Provides
    fun stateImmutable(state: MutableStateFlow<AppBarState>): StateFlow<AppBarState> = state

    @Provides
    fun initAppBar(state: MutableStateFlow<AppBarState>): InitAppBar = InitAppBarImpl(state = state)
}