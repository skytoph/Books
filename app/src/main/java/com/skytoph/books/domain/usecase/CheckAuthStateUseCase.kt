package com.skytoph.books.domain.usecase

interface CheckAuthStateUseCase {
    operator fun invoke(): Boolean
}