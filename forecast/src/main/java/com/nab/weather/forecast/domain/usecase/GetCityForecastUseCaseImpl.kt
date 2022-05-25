package com.nab.weather.forecast.domain.usecase

import com.nab.weather.forecast.domain.entity.DailyForecastEntity
import com.nab.weather.forecast.domain.entity.Mapper
import com.nab.weather.forecast.domain.repository.ForecastRepository
import com.nab.weather.forecast.presentation.usecase.GetCityForecastUseCase
import javax.inject.Inject

class GetCityForecastUseCaseImpl @Inject constructor(
    private val repository: ForecastRepository
) : GetCityForecastUseCase {

    override suspend fun invoke(
        query: String,
        count: Int,
        appId: String
    ): List<DailyForecastEntity>? {
        val cityForecastDto = repository.getCityDailyForecast(query, count, appId).data
        val listForecastEntity = cityForecastDto?.list?.map {
            Mapper.map(it)
        }
        return listForecastEntity
    }
}
