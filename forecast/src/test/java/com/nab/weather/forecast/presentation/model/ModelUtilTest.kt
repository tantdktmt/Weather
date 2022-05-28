package com.nab.weather.forecast.presentation.model

import com.nab.weather.forecast.data.dto.ForecastDto
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Test

class ModelUtilTest {

    @Test
    fun buildListDailyForecastModel_twoDto_returnsTwoModelsOneDivider() {
        val listDto = mutableListOf<ForecastDto.Daily>()
        listDto.add(
            ForecastDto.Daily(
                1653796800L,
                1653776991L,
                1653822711L,
                ForecastDto.Temp(302.8f, 297.53f, 304.51f, 299.95f, 302.64f, 297.78f),
                ForecastDto.FeelsLike(307.8f, 303.05f, 307.41f, 298.67f),
                1007,
                72,
                listOf(ForecastDto.Weather(501, "Rain", "moderate rain", "10d")),
                null,
                null,
                null,
                null,
                null,
                null
            )
        )
        listDto.add(
            ForecastDto.Daily(
                1653796800L,
                1653776991L,
                1653822711L,
                ForecastDto.Temp(302.8f, 297.53f, 304.51f, 299.95f, 302.64f, 297.78f),
                ForecastDto.FeelsLike(307.8f, 303.05f, 307.41f, 298.67f),
                1007,
                72,
                listOf(ForecastDto.Weather(501, "Rain", "moderate rain", "10d")),
                null,
                null,
                null,
                null,
                null,
                null
            )
        )
        val listModel = ModelUtil.buildListDailyForecastModel(listDto)
        MatcherAssert.assertThat(listModel[0].viewType, Is.`is`(BaseListModel.ViewType.FORECAST))
        MatcherAssert.assertThat(listModel[1].viewType, Is.`is`(BaseListModel.ViewType.DIVIDER))
        MatcherAssert.assertThat(listModel[2].viewType, Is.`is`(BaseListModel.ViewType.FORECAST))
    }

    @Test
    fun buildListDailyForecastModel_oneDto_returnsCorrectDate() {
        val listDto = mutableListOf<ForecastDto.Daily>()
        listDto.add(
            ForecastDto.Daily(
                1653796800000L,
                1653776991000L,
                1653822711000L,
                ForecastDto.Temp(302.8f, 297.53f, 304.51f, 299.95f, 302.64f, 297.78f),
                ForecastDto.FeelsLike(307.8f, 303.05f, 307.41f, 298.67f),
                1007,
                72,
                listOf(ForecastDto.Weather(501, "Rain", "moderate rain", "10d")),
                null,
                null,
                null,
                null,
                null,
                null
            )
        )
        val listModel = ModelUtil.buildListDailyForecastModel(listDto)
        MatcherAssert.assertThat((listModel[0] as DailyForecastModel).date, Is.`is`("Sun, 29 May 2022"))
    }
}