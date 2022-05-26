package com.nab.weather.network.di

import android.content.Context
import android.content.pm.ApplicationInfo
import com.nab.weather.config.Config
import com.nab.weather.network.interceptor.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT = 60L

    @Provides
    @Singleton
    fun provideLoggingInterceptor(@ApplicationContext appContext: Context): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()

        logging.level =
            if (isDebuggable(appContext)) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return logging
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(@ApplicationContext appContext: Context) =
        CacheInterceptor(appContext)

    @Provides
    @Singleton
    fun provideDispatcher(): Dispatcher {
        val dispatcher = Dispatcher().apply {
            maxRequests = 1
        }
        return dispatcher
    }

    @Provides
    @Singleton
    fun provideConnectionSpecs(): ConnectionSpec {
        return ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext appContext: Context): Cache {
        val cacheSize = (5 * 1024 * 1024).toLong()
        return Cache(appContext.cacheDir, cacheSize)
    }

    @Provides
    @Suppress("LongParameterList")
    fun provideOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cacheInterceptor: CacheInterceptor,
        dispatcher: Dispatcher,
        cache: Cache,
        connectionSpec: ConnectionSpec
    ): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
            .dispatcher(dispatcher)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectionSpecs(arrayListOf(connectionSpec, ConnectionSpec.CLEARTEXT))
            .cache(cache)
            .addInterceptor(cacheInterceptor)
            .addInterceptor(httpLoggingInterceptor)
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return buildRetrofit(gsonConverterFactory, okHttpClient)
    }

    private fun isDebuggable(appContext: Context) =
        0 != appContext.packageManager?.getApplicationInfo(
            appContext.packageName!!,
            0
        )?.flags?.and(ApplicationInfo.FLAG_DEBUGGABLE) ?: false

    private fun buildRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.API_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }
}
