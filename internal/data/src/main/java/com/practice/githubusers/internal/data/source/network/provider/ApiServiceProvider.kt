package com.practice.githubusers.internal.data.source.network.provider

import com.practice.githubusers.internal.data.source.network.service.UserServiceApi
import retrofit2.Retrofit

object ApiServiceProvider {

    fun getApiUserService(retrofit: Retrofit): UserServiceApi {
        return retrofit.create(UserServiceApi::class.java)
    }
}