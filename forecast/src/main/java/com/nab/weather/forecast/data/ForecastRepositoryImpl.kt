package com.nab.weather.forecast.data

import com.nab.weather.common.data.RefreshableApiExecutor
import com.nab.weather.forecast.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val service: ForecastService
) : ForecastRepository, RefreshableApiExecutor() {

    override suspend fun getCityDailyForecast(query: String, count: Int, appId: String) =
        safeApiCall {
            service.getCityDailyForecast(query, count, appId)
        }
}
