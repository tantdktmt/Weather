package com.nab.weather.forecast.data.dto

data class CityForecastDto(
    val city: CityDto?,
    val cod: String?,
    val message: Double?,
    val cnt: Int?,
    val list: List<DailyForecastDto>?
)

