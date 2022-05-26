package com.nab.weather.forecast.data

import com.nab.weather.common.data.ApiExecutor
import com.nab.weather.common.data.Result
import com.nab.weather.forecast.data.dto.ForecastDto
import com.nab.weather.forecast.domain.repository.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val service: ForecastService
) : ForecastRepository, ApiExecutor() {

    override suspend fun getDailyForecast(
        query: String,
        count: Int,
        appId: String?
    ): Flow<Result<ForecastDto?>> {
        return flow {
            emit(Result.Loading)
            val result = getResponse {
                service.getDailyForecast(query, count, appId)
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
