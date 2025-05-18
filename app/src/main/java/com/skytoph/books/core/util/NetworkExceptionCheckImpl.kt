package com.skytoph.books.core.util

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkExceptionCheckImpl : NetworkExceptionCheck {
    override fun isNetworkUnavailable(exception: Exception): Boolean = when (exception) {
        is UnknownHostException -> true
        is SocketTimeoutException -> true
        is ConnectException -> true
        else -> false
    }
}