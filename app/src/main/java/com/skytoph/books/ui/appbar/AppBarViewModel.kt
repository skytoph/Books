package com.skytoph.books.ui.appbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppBarViewModel @Inject constructor() : ViewModel() {

    val appBarState: StateFlow<AppBarState>
        field = MutableStateFlow(AppBarState())

    fun updateAppBar(title: String, canNavigateUp: Boolean) {
        appBarState.value = appBarState.value.copy(title = title, canNavigateUp = canNavigateUp)
    }
}