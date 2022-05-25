package com.nab.weather.common.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class RefreshableApiExecutor {

    suspend inline fun <reified T> safeApiCall(
        crossinline call: suspend () -> Response<T>): T {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                val resultSuccess = response.body() ?: T::class.java.newInstance()

                if (response.isSuccessful) {
                    return@withContext resultSuccess
                } else {
                    val type = object : TypeToken<T>() {}.type
                    return@withContext Gson().fromJson<T>(response.errorBody()?.string(), type)
                }
            } catch (e: Exception) {
                val response = T::class.java.newInstance()
                return@withContext response
            }
        }
    }
}
