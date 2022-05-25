package com.nab.weather.common.data

import retrofit2.Response

abstract class ApiExecutor {

    suspend inline fun <reified T> getResponse(
        crossinline request: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val result = request.invoke()
            return if (result.isSuccessful) {
                Result.Success(result.body() ?: T::class.java.newInstance())
            } else {
                Result.Error(result.code(), result.message())
            }
        } catch (e: Throwable) {
            Result.Error(Result.Error.CommonCode.UNKNOWN, Result.Error.CommonMessage.UNKNOWN)
        }
    }
}
