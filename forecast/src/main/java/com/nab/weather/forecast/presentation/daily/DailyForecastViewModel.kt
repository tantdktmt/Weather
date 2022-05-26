package com.nab.weather.forecast.presentation.daily

import androidx.lifecycle.viewModelScope
import com.nab.weather.common.base.BaseViewModel
import com.nab.weather.common.data.Result
import com.nab.weather.config.Config
import com.nab.weather.forecast.presentation.model.BaseListModel
import com.nab.weather.forecast.presentation.model.ModelUtil
import com.nab.weather.forecast.presentation.usecase.GetDailyForecastUseCase
import com.nab.weather.utility.CryptoUtil
import com.nab.weather.utility.sharedpref.SharedPreferenceUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(
    private val getDailyForecastUseCase: GetDailyForecastUseCase,
    private val cryptoUtil: CryptoUtil
) : BaseViewModel() {

    private val _listForecastModel: MutableStateFlow<List<BaseListModel>> by lazy {
        MutableStateFlow(mutableListOf())
    }
    val listForecastModel = _listForecastModel.asStateFlow()

    fun getDailyForecast(keyword: String) {
        viewModelScope.launch {
            getDailyForecastUseCase(
                keyword,
                Config.DAILY_FORECAST_SEARCH_COUNT,
                cryptoUtil.getWeatherApiAppId()
            ).collect {
                when (it) {
                    is Result.Loading -> _loading.value = true
                    is Result.Success -> {
                        it.data?.list?.let {
                            _listForecastModel.value = ModelUtil.buildListDailyForecastModel(it)
                        }
                        _loading.value = false
                    }
                    is Result.Error -> {
                        _error.emit(Result.Error(it.code, it.message))
                        _listForecastModel.value = listOf()
                        _loading.value = false
                    }
                }
            }
        }
    }
}
