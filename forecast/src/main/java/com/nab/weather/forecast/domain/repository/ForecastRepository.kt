package com.nab.weather.forecast.domain.repository

import com.nab.weather.forecast.data.dto.CityForecastDto

interface ForecastRepository {

    suspend fun getCityDailyForecast(
        query: String,
        count: Int,
        appId: String
    ): CityForecastDto
}
