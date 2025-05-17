package com.skytoph.books.core.resources

import java.util.Locale

interface LocaleProvider {
    fun locale(): Locale
}