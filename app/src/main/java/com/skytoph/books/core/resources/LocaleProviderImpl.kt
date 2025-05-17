package com.skytoph.books.core.resources

import java.util.Locale

class LocaleProviderImpl : LocaleProvider {
    override fun locale(): Locale = Locale.getDefault()
}