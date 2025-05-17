package com.skytoph.books.core.key

import com.skytoph.books.BuildConfig

object KeyProviderImpl : KeyProvider {
    override val booksApiKey: String = BuildConfig.BOOKS_API_KEY
}