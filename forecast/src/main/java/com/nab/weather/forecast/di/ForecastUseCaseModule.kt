package com.nab.weather.forecast.di

import com.nab.weather.forecast.domain.usecase.GetCityForecastUseCaseImpl
import com.nab.weather.forecast.presentation.usecase.GetCityForecastUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ForecastUseCaseModule {

    @Binds
    abstract fun provideGetListJobUseCase(
        getCityForecastUseCaseImpl: GetCityForecastUseCaseImpl
    ): GetCityForecastUseCase
}
