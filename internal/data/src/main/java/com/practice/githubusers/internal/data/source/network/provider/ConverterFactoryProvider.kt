package com.practice.githubusers.internal.data.source.network.provider

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ConverterFactoryProvider {

    fun getConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    fun getConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }
}