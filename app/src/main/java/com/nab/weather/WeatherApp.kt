package com.nab.weather

import android.app.Application
import com.nab.weather.config.Config
import com.nab.weather.utility.sharedpref.SharedPreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferenceUtil.init(this)
        // Hardcode OpenWeatherApi appId
        SharedPreferenceUtil.saveWeatherApiAppId(Config.WEATHER_API_APP_ID)
    }
}