package com.nab.weather

import android.app.Application
import android.content.Context
import com.nab.weather.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() :
    BaseViewModel()
