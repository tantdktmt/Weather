package com.nab.weather.common.data.dto

data class ResponseDto<T>(
    var status: StatusDto?,
    val data: T?
)
