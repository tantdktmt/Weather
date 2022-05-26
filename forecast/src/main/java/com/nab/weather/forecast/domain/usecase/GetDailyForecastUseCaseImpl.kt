package com.nab.weather.forecast.domain.usecase

import com.nab.weather.forecast.domain.repository.ForecastRepository
import com.nab.weather.forecast.presentation.usecase.GetDailyForecastUseCase
import javax.inject.Inject

class GetDailyForecastUseCaseImpl @Inject constructor(
    private val repository: ForecastRepository
) : GetDailyForecastUseCase {

    override suspend fun invoke(
        query: String,
        count: Int,
        appId: String?
    ) = repository.getDailyForecast(query, count, appId)
}
