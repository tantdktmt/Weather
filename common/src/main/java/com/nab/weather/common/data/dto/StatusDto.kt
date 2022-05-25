package com.nab.weather.common.data.dto

data class StatusDto(
    var code: String?,
    var message: String?
) {

    object CommonCode {

        const val ERROR = "Error"
    }
}
