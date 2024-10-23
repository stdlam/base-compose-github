package com.practice.githubusers.internal.data.source.network.service

import com.practice.githubusers.internal.data.user.response.GithubUserData
import com.practice.githubusers.internal.data.user.response.UserDataResponse
import retrofit2.Call
import retrofit2.http.*

interface UserServiceApi {
    @GET("users")
    fun getRemoteUsers(@Query("since") sinceId: Int = 1,
                       @Query("per_page") limit: Int = 20): Call<List<GithubUserData>>

    @GET("users/{userName}")
    fun getUserProfile(@Path(value = "userName") userName: String): Call<UserDataResponse>
}