package com.skytoph.books.domain.usecase

interface SignInUseCase {
    suspend operator fun invoke(): SignInResult
}

