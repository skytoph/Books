package com.skytoph.books.ui.appbar

import kotlinx.coroutines.flow.MutableStateFlow


class InitAppBarImpl(private val state: MutableStateFlow<AppBarState>) : InitAppBar {
    override fun initAppBar(title: String, canNavigateUp: Boolean) {
        state.value = state.value.copy(title = title, canNavigateUp = canNavigateUp)
    }
}