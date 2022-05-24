package com.nab.weather.weatherlib

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.nab.weather.common.base.BaseActivity
import com.nab.weather.utility.MainEvent
import com.nab.weather.utility.MainEventDispatcher
import com.nab.weather.weatherlib.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private val viewModel by viewModels<MainActivityViewModel>()

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getAssociatedViewModel() = viewModel

    override fun observeDataChanged() {
        MainEventDispatcher.listener.onEach {
            when (it) {
                is MainEvent.MainDeepLinkNavigate -> {
                    // TODO: navigate to the deeplink
                }
            }
        }.launchIn(lifecycleScope)
    }

    override fun initView() {
        Toast.makeText(this, "Open MainActivity in Weather app", Toast.LENGTH_SHORT).show()
    }
}