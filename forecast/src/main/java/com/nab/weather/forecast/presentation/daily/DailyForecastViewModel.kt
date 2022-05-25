package com.nab.weather.forecast.presentation.daily

import androidx.lifecycle.viewModelScope
import com.nab.weather.common.base.BaseViewModel
import com.nab.weather.forecast.presentation.model.DailyForecastModel
import com.nab.weather.forecast.presentation.model.ModelUtil
import com.nab.weather.forecast.presentation.usecase.GetCityForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(private val getCityForecastUseCase: GetCityForecastUseCase) :
    BaseViewModel() {

    private val _listForecastModel: MutableStateFlow<List<DailyForecastModel>> by lazy {
        MutableStateFlow(mutableListOf())
    }
    val listForecastModel = _listForecastModel.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            showLoading()
            // TESTING: replace with dynamic params
            getCityForecastUseCase("saigon", 7, "60c6fbeb4b93ac653c492ba806fc346d")?.let {
                _listForecastModel.value = ModelUtil.buildListDailyForecastModel(it)
            }
            hideLoading()
        }
    }
}
