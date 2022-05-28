package com.nab.weather.forecast.domain.repository

import com.nab.weather.common.data.Result
import com.nab.weather.forecast.data.dto.ForecastDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeForecastRepository : ForecastRepository {

    private val daily1 = ForecastDto.Daily(
        1653796800L,
        1653776991L,
        1653822711L,
        ForecastDto.Temp(302.8f, 297.53f, 304.51f, 299.95f, 302.64f, 297.78f),
        ForecastDto.FeelsLike(307.8f, 303.05f, 307.41f, 298.67f),
        1007,
        72,
        listOf(ForecastDto.Weather(501, "Rain", "moderate rain", "10d")),
        null,
        null,
        null,
        null,
        null,
        null
    )
    private val daily2 = ForecastDto.Daily(
        1653796800L,
        1653776991L,
        1653822711L,
        ForecastDto.Temp(302.8f, 297.53f, 304.51f, 299.95f, 302.64f, 297.78f),
        ForecastDto.FeelsLike(307.8f, 303.05f, 307.41f, 298.67f),
        1007,
        72,
        listOf(ForecastDto.Weather(501, "Rain", "moderate rain", "10d")),
        null,
        null,
        null,
        null,
        null,
        null
    )
    val forecastDto = ForecastDto(null, null, null, 2, listOf(daily1, daily2))

    override suspend fun getDailyForecast(
        query: String,
        count: Int,
        appId: String?
    ): Flow<Result<ForecastDto?>> {
        return flow {
            emit(Result.Loading)
            emit(Result.Success(forecastDto))
        }
    }
}