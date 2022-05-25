package com.nab.weather.forecast.data.dto

import com.google.gson.annotations.SerializedName

data class DailyForecastDto(
    val dt: Long?,
    val sunrise: Long?,
    val sunset: Long?,
    val temp: TempDto?,
    @SerializedName("feels_like") val feelsLike: FeelsLikeDto?,
    val pressure: Int?,
    val humidity: Int?,
    val weather: List<WeatherDto>?,
    val speed: Float?,
    val deg: Int?,
    val gust: Float?,
    val clouds: Int?,
    val pop: Float?,
    val rain: Float?
)

