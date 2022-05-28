package com.nab.weather.utility

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {

    const val DATE_FORMAT = "EEE, dd MMM yyyy"

    fun convertDateToString(date: Date, format: String = DATE_FORMAT) = try {
        SimpleDateFormat(format, Locale.getDefault()).format(date)
    } catch (e: Exception) {
        null
    }


    fun convertLongToString(time: Long?, format: String = DATE_FORMAT): String? {
        time?.let {
            return convertDateToString(Date(it), format)
        }
        return null
    }
}
