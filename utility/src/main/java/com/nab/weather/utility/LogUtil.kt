package com.nab.weather.utility

import android.util.Log
import com.nab.weather.config.Config

object LogUtil {

    fun d(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(Config.DEBUG_LOG_TAG, message)
        }
    }

    fun e(message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(Config.DEBUG_LOG_TAG, message)
        }
    }
}
