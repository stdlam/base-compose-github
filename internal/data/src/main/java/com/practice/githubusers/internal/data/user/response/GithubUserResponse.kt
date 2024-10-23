package com.practice.githubusers.internal.data.user.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.practice.githubusers.internal.data.source.network.response.DataResponse

@JsonClass(generateAdapter = true)
class GithubUserResponse : DataResponse<List<GithubUserData>>()

@JsonClass(generateAdapter = true)
data class GithubUserData(
    @Json(name = "id") val id: Int?,
    @Json(name = "login") val username: String?,
    @Json(name = "avatar_url") val avatar: String?,
    @Json(name = "html_url") val url: String?
)