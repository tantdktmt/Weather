package com.nab.weather.forecast.presentation.daily

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nab.weather.common.base.BaseFragment
import com.nab.weather.forecast.BR
import com.nab.weather.forecast.R
import com.nab.weather.forecast.databinding.FragmentDailyForecastBinding
import com.nab.weather.utility.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DailyForecastFragment :
    BaseFragment<DailyForecastViewModel, FragmentDailyForecastBinding>() {

    private val viewModel by viewModels<DailyForecastViewModel>()
    private val forecastAdapter by lazy {
        ForecastAdapter()
    }

    override fun getLayoutId() = R.layout.fragment_daily_forecast

    override fun getBindingViewModelId() = BR.view_model

    override fun getAssociatedViewModel() = viewModel

    override fun initView() {
        binding.rvForecast.apply {
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setLayoutManager(layoutManager)
            setHasFixedSize(true)
            adapter = forecastAdapter
        }
    }

    override fun observeDataChanged() {
        with(viewModel) {
            listForecastModel.onEach {
                LogUtil.d("list forecast: ${it.size}")
                if (it.isNotEmpty()) {
                    forecastAdapter.submitList(it)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.run {
            if (listForecastModel.value.isEmpty()) {
                loadData()
            }
        }
    }
}
