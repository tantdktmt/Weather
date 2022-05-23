package com.nab.weather.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {

    val loading: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }

    fun showLoading() {
        loading.value = true
    }

    fun hideLoading() {
        loading.value = false
    }
}
