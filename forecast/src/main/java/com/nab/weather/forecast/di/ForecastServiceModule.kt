package com.nab.weather.forecast.di

import com.nab.weather.forecast.data.ForecastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForecastServiceModule {

    @Provides
    @Singleton
    fun provideForecastService(retrofit: Retrofit) = retrofit.create(ForecastService::class.java)
}
