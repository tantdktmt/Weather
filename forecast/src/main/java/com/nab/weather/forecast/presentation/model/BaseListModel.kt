package com.nab.weather.forecast.presentation.model

sealed class BaseListModel(val viewType: Int) {

    abstract class BaseListDataModel(viewType: Int = ViewType.UNDEFINED) : BaseListModel(viewType)

    object DividerModel : BaseListModel(ViewType.DIVIDER)

    object ViewType {

        const val UNDEFINED = -1
        const val FORECAST = 0
        const val DIVIDER = 1
    }
}
