package com.nab.weather.forecast.presentation.usecase

import com.nab.weather.forecast.domain.entity.DailyForecastEntity

interface GetCityForecastUseCase {

    suspend operator fun invoke(
        query: String,
        count: Int,
        appId: String
    ): List<DailyForecastEntity>?
}
