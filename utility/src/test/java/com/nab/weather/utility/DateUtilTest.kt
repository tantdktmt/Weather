package com.nab.weather.utility

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Test

class DateUtilTest {

    @Test
    fun convertLongToString_timeNull_returnsNull() {
        val result = DateUtil.convertLongToString(null)
        MatcherAssert.assertThat(result, `is`(CoreMatchers.nullValue()))
    }

    @Test
    fun convertLongToString_normalTime_returnsNormalValue() {
        val time = 1653748965000L
        val result = DateUtil.convertLongToString(time)
        MatcherAssert.assertThat(result, `is`("Sat, 28 May 2022"))
    }

    @Test
    fun convertLongToString_invalidFormat_returnsNull() {
        val time = 1653748965000L
        val format = "Efg, dd MMM yyyy"
        val result = DateUtil.convertLongToString(time, format)
        MatcherAssert.assertThat(result, `is`(CoreMatchers.nullValue()))
    }
}