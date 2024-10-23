package com.practice.githubusers.internal.data.user.response

import com.practices.githubusers.internal.domain.base.extensions.empty
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.practices.githubusers.internal.domain.user.model.UserDetail

@JsonClass(generateAdapter = true)
data class UserDataResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "login") val username: String?,
    @Json(name = "avatar_url") val avatar: String?,
    @Json(name = "html_url") val htmlUrl: String?,
    @Json(name = "blog") val blogUrl: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "followers") val followers: Int?,
    @Json(name = "following") val following: Int?
) {

    fun toUser() = mapTo(this)

    companion object {
        fun mapTo(o: UserDataResponse) = UserDetail(
            userId = o.id,
            userName = o.username ?: String.empty(),
            avatar = o.avatar ?: String.empty(),
            location = o.location ?: String.empty(),
            blogUrl = o.blogUrl ?: String.empty(),
            followers = o.followers ?: 0,
            following = o.following ?: 0
        )
    }
}