package com.nab.weather.forecast.domain.entity

import com.nab.weather.forecast.data.dto.DailyForecastDto

object Mapper {

    fun map(dto: DailyForecastDto) = DailyForecastEntity(
        dto.dt ?: 0,
        FeelsLikeEntity(
            dto.feelsLike?.day,
            dto.feelsLike?.night,
            dto.feelsLike?.eve,
            dto.feelsLike?.morn
        ),
        dto.pressure,
        dto.humidity,
        dto.weather?.map {
            WeatherEntity(it.id, it.main, it.description, it.icon)
        }
    )
}