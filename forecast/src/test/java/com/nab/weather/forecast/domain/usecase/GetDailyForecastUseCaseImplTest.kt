package com.nab.weather.forecast.domain.usecase

import com.nab.weather.common.data.Result
import com.nab.weather.forecast.domain.repository.FakeForecastRepository
import com.nab.weather.forecast.domain.repository.ForecastRepository
import com.nab.weather.forecast.presentation.usecase.GetDailyForecastUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDailyForecastUseCaseImplTest {

    private lateinit var forecastRepository: ForecastRepository
    private lateinit var getDailyForecastUseCase: GetDailyForecastUseCase

    @Before
    fun createUsecase() {
        forecastRepository = FakeForecastRepository
        getDailyForecastUseCase = GetDailyForecastUseCaseImpl(forecastRepository)
    }

    @Test
    fun getDailyForecast_returnsDataFromRepository() = runTest {
        val query = ""
        val count = 0
        val appId = ""
        getDailyForecastUseCase(query, count, appId).collect {
            when (it) {
                is Result.Success -> {
                    MatcherAssert.assertThat(
                        it.data,
                        Is.`is`(FakeForecastRepository.forecastDto)
                    )
                }
                else -> Unit
            }
        }
    }
}