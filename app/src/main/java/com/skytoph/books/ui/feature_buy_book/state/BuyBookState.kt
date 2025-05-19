package com.skytoph.books.ui.feature_buy_book.state

data class BuyBookState(
    val url: String,
    val canNavigateBack: Boolean = false
)