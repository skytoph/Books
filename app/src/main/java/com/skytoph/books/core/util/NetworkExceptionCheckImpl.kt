package com.skytoph.books.core.util

import com.google.firebase.FirebaseNetworkException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkExceptionCheckImpl : NetworkExceptionCheck {
    override fun isNetworkUnavailable(exception: Exception): Boolean = when (exception) {
        is UnknownHostException -> true
        is SocketTimeoutException -> true
        is SocketException -> true
        is FirebaseNetworkException -> true
        else -> false
    }
}