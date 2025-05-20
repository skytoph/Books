package com.skytoph.books.core.util

interface NetworkExceptionCheck {
    fun isNetworkUnavailable(exception: Exception): Boolean
}

