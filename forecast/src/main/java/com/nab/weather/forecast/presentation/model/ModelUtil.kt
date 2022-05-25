package com.nab.weather.forecast.presentation.model

import com.nab.weather.forecast.domain.entity.DailyForecastEntity
import com.nab.weather.forecast.domain.entity.FeelsLikeEntity
import com.nab.weather.forecast.domain.entity.WeatherEntity
import com.nab.weather.utility.DateUtil

object ModelUtil {

    fun buildListDailyForecastModel(listEntity: List<DailyForecastEntity>): List<DailyForecastModel> {
        val listModel = mutableListOf<DailyForecastModel>()
        listEntity.forEachIndexed { i, entity ->
            listModel.add(
                DailyForecastModel(
                    DateUtil.convertLongToString(entity.date),
                    generateAverageTemp(entity.feelsLike),
                    entity.pressure,
                    entity.humidity,
                    generateDescription(entity.weather)
                )
            )
        }
        return listModel
    }

    private fun generateAverageTemp(feelsLike: FeelsLikeEntity?): String? {
        // TODO: correct generate temperature in oC
        val averateTemp = (((feelsLike?.day ?: 0f) + (feelsLike?.night ?: 0f) + (feelsLike?.eve
            ?: 0f) + (feelsLike?.morn ?: 0f)) / 4).toInt()
        return "$averateTemp C"
    }

    private fun generateDescription(listWeather: List<WeatherEntity>?): String {
        val strBuilder = StringBuilder()
        listWeather?.forEach {
            strBuilder.append(it.description)
        }
        return strBuilder.toString()
    }
}