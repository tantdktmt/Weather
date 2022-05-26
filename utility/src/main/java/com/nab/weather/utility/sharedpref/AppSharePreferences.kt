package com.nab.weather.utility.sharedpref

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nab.weather.config.Config

class AppSharePreferences(var sharePreferenceName: String, var application: Application?) {

    fun clear() {
        val appRef =
            application?.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE) ?: return
        val editor = appRef.edit()
        editor.clear().apply()
    }

    fun putString(key: String, value: String) {
        if (application == null) return
        val appRef =
            application?.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE) ?: return
        val editor = appRef.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return getString(key, null)
    }

    fun getString(key: String, default: String?): String? {
        if (application == null) return default

        val appRef = application?.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE)
            ?: return default

        return appRef.getString(key, default)
    }

    fun putInt(key: String, value: Int) {
        if (application == null) return

        val appRef =
            application?.getSharedPreferences(
                sharePreferenceName,
                Context.MODE_PRIVATE
            )
                ?: return

        val editor = appRef.edit()

        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int {
        return getInt(key, -1)
    }

    fun getInt(key: String, default: Int): Int {
        if (application == null) return default

        val appRef =
            application?.getSharedPreferences(
                sharePreferenceName,
                Context.MODE_PRIVATE
            )
                ?: return default

        return appRef.getInt(key, default)
    }

    fun putFloat(key: String, value: Float) {
        if (application == null) return

        val appRef =
            application?.getSharedPreferences(
                sharePreferenceName,
                Context.MODE_PRIVATE
            )
                ?: return

        val editor = appRef.edit()

        editor.putFloat(key, value)
        editor.apply()
    }

    fun getFloat(key: String): Float {
        return getFloat(key, -1f)
    }

    fun getFloat(key: String, default: Float): Float {
        if (application == null) return default

        val appRef =
            application?.getSharedPreferences(
                sharePreferenceName,
                Context.MODE_PRIVATE
            )
                ?: return default

        return appRef.getFloat(key, default)
    }

    fun putBoolean(key: String, value: Boolean) {
        if (application == null) return

        val appRef =
            application?.getSharedPreferences(
                sharePreferenceName,
                Context.MODE_PRIVATE
            )
                ?: return

        val editor = appRef.edit()

        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return getBoolean(key, false)
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        if (application == null) return default

        val appRef =
            application?.getSharedPreferences(
                sharePreferenceName,
                Context.MODE_PRIVATE
            )
                ?: return default

        return appRef.getBoolean(key, default)
    }

    inline fun <reified T> getObject(key: String): T? {
        return try {
            val data = getString(key, "{}")
            val type = object : TypeToken<T>() {}.type
            Gson().fromJson(data, type)
        } catch (e: Exception) {
            null
        }
    }

    fun putObject(key: String, data: Any?) {
        val stringJson = Gson().toJson(data)
        putString(key, stringJson)
    }

    companion object {
        @Volatile
        private var instance: AppSharePreferences? = null

        fun createInstance(application: Application): AppSharePreferences =
            instance ?: synchronized(this) {
                if (instance == null) {
                    return AppSharePreferences(
                        Config.APP_SHARED_PREFERENCE_NAME,
                        application
                    ).also {
                        instance = it
                    }
                }
                return instance as AppSharePreferences
            }

        fun getInstance(): AppSharePreferences = instance as AppSharePreferences
    }
}
