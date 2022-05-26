package com.nab.weather

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.nab.weather.common.base.BaseActivity
import com.nab.weather.databinding.ActivityMainBinding
import com.nab.weather.utility.DeviceUtil
import com.nab.weather.utility.MainEvent
import com.nab.weather.utility.MainEventDispatcher
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (DeviceUtil.isDeviceRooted()) {
            Toast.makeText(
                this,
                baseContext.getString(R.string.device_rooted_notice),
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }
}