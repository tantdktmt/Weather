package com.nab.weather.forecast.domain.repository

import com.nab.weather.common.data.Result
import com.nab.weather.forecast.data.dto.CityForecastDto
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    suspend fun getCityDailyForecast(
        query: String,
        count: Int,
        appId: String
    ): Flow<Result<CityForecastDto?>>
}
