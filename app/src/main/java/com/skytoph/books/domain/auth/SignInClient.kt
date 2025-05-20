package com.skytoph.books.domain.auth

import com.skytoph.books.domain.usecase.SignInResult


interface IsSignedIn {
    fun isSignedIn(): Boolean
}

interface SignIn {
    suspend fun signIn(): SignInResult
}

interface SignInClient : SignIn, IsSignedIn
