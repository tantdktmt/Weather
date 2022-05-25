package com.nab.weather.forecast.presentation.model

data class DailyForecastModel(
    val date: String?,
    val averageTemp: String?,
    val pressure: Int?,
    val humidity: Int?,
    val description: String
)
