package com.nab.weather.utility.sharedpref

import android.app.Application

object SharedPreferenceUtil {

    private const val KEY_WEATHER_API_APP_ID = "weather_api_app_id"

    fun init(application: Application) {
        AppSharePreferences.createInstance(application)
    }

    fun clear() {
        AppSharePreferences.getInstance().clear()
    }

    fun saveWeatherApiAppId(appId: String) =
        AppSharePreferences.getInstance().putString(KEY_WEATHER_API_APP_ID, appId)

    fun getWeatherApiAppId() =
        AppSharePreferences.getInstance().getString(KEY_WEATHER_API_APP_ID, "")
}
