package com.nab.weather.forecast.presentation.daily

import androidx.fragment.app.viewModels
import com.nab.weather.common.base.BaseFragment
import com.nab.weather.forecast.BR
import com.nab.weather.forecast.R
import com.nab.weather.forecast.databinding.FragmentDailyForecastBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyForecastFragment :
    BaseFragment<DailyForecastViewModel, FragmentDailyForecastBinding>() {

    private val viewModel by viewModels<DailyForecastViewModel>()

    override fun getLayoutId() = R.layout.fragment_daily_forecast

    override fun getBindingViewModelId() = BR.view_model

    override fun getAssociatedViewModel() = viewModel

    override fun initView() {
    }

    override fun observeDataChanged() {
        with(viewModel) {
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
