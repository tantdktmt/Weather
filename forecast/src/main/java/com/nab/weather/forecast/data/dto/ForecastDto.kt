package com.nab.weather.forecast.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ForecastDto(
    val city: City?,
    val cod: String?,
    val message: Double?,
    val cnt: Int?,
    val list: List<Daily>?
) {

    @Keep
    data class City(
        val id: Int?,
        val name: String?,
        val coord: Coord?,
        val country: String?,
        val population: Int?,
        val timezone: Long?
    )

    @Keep
    data class Coord(
        val lon: Double?,
        val lat: Double?
    )

    @Keep
    data class Daily(
        val dt: Long?,
        val sunrise: Long?,
        val sunset: Long?,
        val temp: Temp?,
        @SerializedName("feels_like") val feelsLike: FeelsLike?,
        val pressure: Int?,
        val humidity: Int?,
        val weather: List<Weather>?,
        val speed: Float?,
        val deg: Int?,
        val gust: Float?,
        val clouds: Int?,
        val pop: Float?,
        val rain: Float?
    )

    @Keep
    data class Temp(
        val day: Float?,
        val min: Float?,
        val max: Float?,
        val night: Float?,
        val eve: Float?,
        val morn: Float?
    )

    @Keep
    data class FeelsLike(
        val day: Float?,
        val night: Float?,
        val eve: Float?,
        val morn: Float?
    )

    @Keep
    data class Weather(
        val id: Int?,
        val main: String?,
        val description: String?,
        val icon: String?
    )
}

