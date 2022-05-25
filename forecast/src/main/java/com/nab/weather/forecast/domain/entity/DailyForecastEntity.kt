package com.nab.weather.forecast.domain.entity

data class DailyForecastEntity(
    val date: Long,
    val feelsLike: FeelsLikeEntity?,
    val pressure: Int?,
    val humidity: Int?,
    val weather: List<WeatherEntity>?,
)

