package com.skytoph.books.domain.usecase

import com.skytoph.books.domain.auth.IsSignedIn

class CheckAuthStateUseCaseImpl(
    private val client: IsSignedIn,
) : CheckAuthStateUseCase {

    override fun invoke(): Boolean = client.isSignedIn()
}