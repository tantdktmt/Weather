package com.nab.weather.utility

import android.util.Log
import com.nab.weather.config.Constant

object LogUtil {

    fun d(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(Constant.DEBUG_LOG_TAG, message)
        }
    }

    fun e(message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(Constant.DEBUG_LOG_TAG, message)
        }
    }
}
