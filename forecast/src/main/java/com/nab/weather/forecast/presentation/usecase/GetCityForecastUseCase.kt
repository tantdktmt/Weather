package com.nab.weather.forecast.presentation.usecase

import com.nab.weather.common.data.Result
import com.nab.weather.forecast.data.dto.CityForecastDto
import kotlinx.coroutines.flow.Flow

interface GetCityForecastUseCase {

    suspend operator fun invoke(
        query: String,
        count: Int,
        appId: String
    ): Flow<Result<CityForecastDto?>>
}
