package com.nab.weather.forecast.presentation.usecase

import com.nab.weather.common.data.Result
import com.nab.weather.forecast.data.dto.ForecastDto
import kotlinx.coroutines.flow.Flow

interface GetDailyForecastUseCase {

    suspend operator fun invoke(
        query: String,
        count: Int,
        appId: String?
    ): Flow<Result<ForecastDto?>>
}
