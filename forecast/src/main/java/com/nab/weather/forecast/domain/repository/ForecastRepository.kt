package com.nab.weather.forecast.domain.repository

import com.nab.weather.common.data.Result
import com.nab.weather.forecast.data.dto.ForecastDto
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    suspend fun getDailyForecast(
        query: String,
        count: Int,
        appId: String?
    ): Flow<Result<ForecastDto?>>
}
