package com.nab.weather.utility

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CommonUtil {

    fun loadJsonFromAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    inline fun <reified T> getGenericType() = object : TypeToken<T>() {}.type

    inline fun <reified T> parseJson(json: String): T {
        val type = getGenericType<T>()
        return Gson().fromJson(json, type)
    }
}