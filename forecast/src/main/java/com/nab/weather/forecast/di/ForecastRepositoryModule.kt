package com.nab.weather.forecast.di

import com.nab.weather.forecast.data.ForecastRepositoryImpl
import com.nab.weather.forecast.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ForecastRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideForecastRepository(forecastRepositoryImpl: ForecastRepositoryImpl): ForecastRepository
}
