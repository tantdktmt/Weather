package com.nab.weather.forecast.presentation.daily

import android.app.Application
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.nab.weather.common.base.BaseViewModel
import com.nab.weather.forecast.presentation.model.BaseListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(
    @ApplicationContext val applicationContext: Context
) : BaseViewModel(applicationContext as Application) {

    private val _listForecastModel: MutableStateFlow<List<BaseListModel>> by lazy {
        MutableStateFlow(mutableListOf())
    }
    val listForecastModel = _listForecastModel.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            // TESTING: replace with dynamic params
        }
    }
}
