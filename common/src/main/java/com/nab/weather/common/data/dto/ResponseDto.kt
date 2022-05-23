package com.nab.weather.common.data.dto

data class ResponseDto<T>(
    val status: StatusDto?,
    val data: T?
)
