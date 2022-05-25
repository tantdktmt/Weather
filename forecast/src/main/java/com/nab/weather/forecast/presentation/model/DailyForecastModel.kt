package com.nab.weather.forecast.presentation.model

data class DailyForecastModel(
    val date: String,
    val averageTemp: String,
    val pressure: String,
    val humidity: String,
    val description: String
) : BaseListModel.BaseListDataModel(ViewType.FORECAST)
