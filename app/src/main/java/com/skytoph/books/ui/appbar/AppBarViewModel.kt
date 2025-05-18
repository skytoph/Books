package com.skytoph.books.ui.appbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppBarViewModel @Inject constructor(
    val appBarState: StateFlow<AppBarState>
) : ViewModel()