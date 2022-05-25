package com.nab.weather.forecast.presentation.model

import android.content.Context
import com.nab.weather.forecast.R
import com.nab.weather.forecast.data.dto.DailyForecastDto
import com.nab.weather.forecast.data.dto.FeelsLikeDto
import com.nab.weather.forecast.data.dto.WeatherDto
import com.nab.weather.utility.DateUtil

object ModelUtil {

    fun buildListDailyForecastModel(
        context: Context,
        listForecastDto: List<DailyForecastDto>
    ): List<BaseListModel> {
        val listModel = mutableListOf<BaseListModel>()
        listForecastDto.forEachIndexed { i, forecastDto ->
            listModel.add(
                DailyForecastModel(
                    context.resources.getString(
                        R.string.weather_forecast_item_date_text,
                        DateUtil.convertLongToString(forecastDto.dt)
                    ),
                    context.resources.getString(
                        R.string.weather_forecast_item_temperature_text,
                        generateAverageTemp(forecastDto.feelsLike)
                    ),
                    context.resources.getString(
                        R.string.weather_forecast_item_pressure_text,
                        forecastDto.pressure
                    ),
                    context.resources.getString(
                        R.string.weather_forecast_item_humidity_text,
                        forecastDto.humidity
                    ),
                    context.resources.getString(
                        R.string.weather_forecast_item_description_text,
                        generateDescription(forecastDto.weather)
                    )
                )
            )
            if (i < listForecastDto.size - 1) listModel.add(BaseListModel.DividerModel)
        }
        return listModel
    }

    private fun generateAverageTemp(feelsLike: FeelsLikeDto?): String {
        // TODO: correct generate temperature in oC
        val averateTemp = (((feelsLike?.day ?: 0f) + (feelsLike?.night ?: 0f) + (feelsLike?.eve
            ?: 0f) + (feelsLike?.morn ?: 0f)) / 4).toInt()
        return "$averateTemp C"
    }

    private fun generateDescription(listWeather: List<WeatherDto>?): String {
        val strBuilder = StringBuilder()
        listWeather?.forEach {
            strBuilder.append(it.description)
        }
        return strBuilder.toString()
    }
}