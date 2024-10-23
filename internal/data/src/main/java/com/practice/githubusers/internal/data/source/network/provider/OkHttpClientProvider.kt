package com.practice.githubusers.internal.data.source.network.provider

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClientProvider {

    private const val DEFAULT_TIMEOUT_SEC       = 20L // in seconds
    private const val DEFAULT_READ_TIMEOUT_SEC  = 20L // in seconds
    private const val DEFAULT_WRITE_TIMEOUT_SEC = 20L // in seconds
    private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()

    fun getOkHttpClientBuilder(
        context: Context,
        logging: Interceptor,
        header: Interceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_READ_TIMEOUT_SEC, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor(header)
        .cache(Cache(context.cacheDir, CACHE_SIZE))
}