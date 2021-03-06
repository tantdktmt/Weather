package com.nab.weather.forecast.presentation.model

import com.nab.weather.forecast.data.dto.ForecastDto
import com.nab.weather.utility.DateUtil

object ModelUtil {

    fun buildListDailyForecastModel(
        listForecastDto: List<ForecastDto.Daily>
    ): List<BaseListModel> {
        val listModel = mutableListOf<BaseListModel>()
        listForecastDto.forEachIndexed { i, forecastDto ->
            listModel.add(
                DailyForecastModel(
                    DateUtil.convertLongToString(forecastDto.dt),
                    generateAverageTemp(forecastDto.feelsLike),
                    forecastDto.pressure,
                    forecastDto.humidity,
                    generateDescription(forecastDto.weather)
                )
            )
            if (i < listForecastDto.size - 1) listModel.add(BaseListModel.DividerModel)
        }
        return listModel
    }

    private fun generateAverageTemp(feelsLike: ForecastDto.FeelsLike?): Int {
        // TODO: correct generate temperature in oC
        return (((feelsLike?.day ?: 0f) + (feelsLike?.night ?: 0f) + (feelsLike?.eve
            ?: 0f) + (feelsLike?.morn ?: 0f)) / 4 - 273.15f).toInt()
    }

    private fun generateDescription(listWeather: List<ForecastDto.Weather>?): String {
        val strBuilder = StringBuilder()
        listWeather?.forEach {
            strBuilder.append(it.description)
        }
        return strBuilder.toString()
    }
}