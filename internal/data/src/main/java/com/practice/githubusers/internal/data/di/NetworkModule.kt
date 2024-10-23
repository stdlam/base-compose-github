package com.practice.githubusers.internal.data.di

import android.content.Context
import com.google.gson.Gson
import com.practice.githubusers.internal.data.di.scopes.MoshiConverter
import com.practice.githubusers.internal.data.di.scopes.CachingHeaderInterceptor
import com.practice.githubusers.internal.data.di.scopes.EndpointInfo
import com.practice.githubusers.internal.data.di.scopes.GsonConverter
import com.practice.githubusers.internal.data.di.scopes.LoggingInterceptor
import com.practice.githubusers.internal.data.di.scopes.MoshiNetworkAdapter
import com.practice.githubusers.internal.data.di.scopes.OkHttp
import com.practice.githubusers.internal.data.di.scopes.RetrofitConfig
import com.practices.githubusers.internal.data.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.practice.githubusers.internal.data.source.network.provider.ConverterFactoryProvider
import com.practice.githubusers.internal.data.source.network.provider.OkHttpClientProvider
import com.practice.githubusers.internal.data.source.network.provider.RetrofitProvider
import com.practice.githubusers.internal.data.secrets.Secrets
import com.practice.githubusers.internal.data.source.network.provider.ApiServiceProvider
import com.practice.githubusers.internal.data.source.network.service.UserServiceApi
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @EndpointInfo
    @Provides
    fun provideBaseApiUrl() = Secrets.apiEndpointUrl

    @GsonConverter
    @Provides
    fun provideConverterFactoryGson(gson: Gson): Converter.Factory =
        ConverterFactoryProvider.getConverterFactory(gson)

    @MoshiConverter
    @Provides
    fun provideConverterFactoryMoshi(@MoshiNetworkAdapter moshi: Moshi): Converter.Factory =
        ConverterFactoryProvider.getConverterFactory(moshi)

    @CachingHeaderInterceptor
    @Provides
    fun providerHeaderInterceptor(
        @ApplicationContext context: Context,
    ): Interceptor {
        return com.practice.githubusers.internal.data.source.network.interceptor.CachingHeaderInterceptor(context)
    }

    @LoggingInterceptor
    @Provides
    fun providerLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }

    @OkHttp
    @Provides
    fun provideOkHttpClient(
        @LoggingInterceptor logging: Interceptor,
        @CachingHeaderInterceptor header: Interceptor,
        @ApplicationContext context: Context,
    ): OkHttpClient =
        OkHttpClientProvider.getOkHttpClientBuilder(context, logging, header).build()

    @RetrofitConfig
    @Provides
    fun provideRetrofit(
        @EndpointInfo baseUrl: String,
        @OkHttp okHttpClient: OkHttpClient,
        // @GsonConverter converterFactory1: Converter.Factory,
        @MoshiConverter converterFactory2: Converter.Factory,
    ): Retrofit = RetrofitProvider
        .getRetrofitBuilder(baseUrl, okHttpClient, converterFactory2)
        .build()

    @Provides
    fun provideApiUserService(@RetrofitConfig retrofit: Retrofit): UserServiceApi =
        ApiServiceProvider.getApiUserService(retrofit)
}