package com.nab.weather

import android.app.Application
import com.nab.weather.utility.sharedpref.SharedPreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferenceUtil.init(this)
    }
}