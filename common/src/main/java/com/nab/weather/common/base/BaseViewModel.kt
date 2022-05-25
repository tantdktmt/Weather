package com.nab.weather.common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nab.weather.common.data.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val _loading: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }
    val loading = _loading.asStateFlow()
    protected val _error: MutableSharedFlow<Result.Error> by lazy {
        MutableSharedFlow()
    }
    val error = _error.asSharedFlow()
}
