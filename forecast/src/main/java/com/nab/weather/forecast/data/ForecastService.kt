package com.nab.weather.forecast.data

import com.nab.weather.common.data.dto.ResponseDto
import com.nab.weather.forecast.data.dto.CityForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("daily")
    suspend fun getCityDailyForecast(
        @Query("q") query: String,
        @Query("cnt") count: Int,
        @Query("appid") appId: String
    ): Response<ResponseDto<CityForecastDto>>
}
