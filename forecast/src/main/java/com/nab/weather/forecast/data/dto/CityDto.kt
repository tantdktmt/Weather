package com.nab.weather.forecast.data.dto

data class CityDto(
    val id: Int?,
    val name: String?,
    val coord: CoordDto?,
    val country: String?,
    val population: Int?,
    val timezone: Long?
)

