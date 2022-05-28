package com.nab.weather.forecast.di

import com.nab.weather.forecast.domain.usecase.GetDailyForecastUseCaseImpl
import com.nab.weather.forecast.presentation.usecase.GetDailyForecastUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ForecastUseCaseModule {

    @Binds
    abstract fun provideGetForecastUseCase(
        getDailyForecastUseCaseImpl: GetDailyForecastUseCaseImpl
    ): GetDailyForecastUseCase
}
