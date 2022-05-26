package com.nab.weather.forecast.presentation.model

data class DailyForecastModel(
    val date: String?,
    val averageTemp: Int,
    val pressure: Int?,
    val humidity: Int?,
    val description: String
) : BaseListModel.BaseListDataModel(ViewType.FORECAST)
