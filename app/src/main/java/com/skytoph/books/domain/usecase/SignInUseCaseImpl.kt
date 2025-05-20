package com.skytoph.books.domain.usecase

import com.skytoph.books.domain.auth.SignInClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignInUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val client: SignInClient,
) : SignInUseCase {

    override suspend fun invoke(): SignInResult = withContext(dispatcher) {
        client.signIn()
    }
}

