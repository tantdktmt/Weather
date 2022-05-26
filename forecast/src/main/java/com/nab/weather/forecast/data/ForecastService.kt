package com.nab.weather.forecast.data

import com.nab.weather.forecast.data.dto.ForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("daily")
    suspend fun getDailyForecast(
        @Query("q") query: String,
        @Query("cnt") count: Int,
        @Query("appid") appId: String?
    ): Response<ForecastDto>
}
