package com.skytoph.books.domain.usecase

import com.skytoph.books.core.util.NetworkExceptionCheck

abstract class GetDataUseCaseAbstract<T>(
    private val networkManager: NetworkExceptionCheck
) {

    suspend fun handle(getData: suspend () -> T): Result<T> = try {
        Result.Success(getData())
    } catch (exception: Exception) {
        //todo log
        if (networkManager.isNetworkUnavailable(exception)) Result.NoConnection
        else Result.ErrorGeneral
    }
}