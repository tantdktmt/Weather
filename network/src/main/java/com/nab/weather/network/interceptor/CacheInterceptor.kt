package com.nab.weather.network.interceptor

import android.content.Context
import com.nab.weather.config.Config
import com.nab.weather.utility.CommonUtil
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CacheInterceptor @Inject constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = if (CommonUtil.isNetworkAvailable(context))
            request.newBuilder().header(
                Config.KEY_HEADER_CACHE_CONTROL,
                "public, max-age=" + Config.CACHE_CONTROL_MAX_AGE
            )
                .build()
        else
            request.newBuilder()
                .header(
                    Config.KEY_HEADER_CACHE_CONTROL,
                    "public, only-if-cached, max-stale=" + Config.CACHE_CONTROL_MAX_STALE
                )
                .build()
        return chain.proceed(request)
    }
}
