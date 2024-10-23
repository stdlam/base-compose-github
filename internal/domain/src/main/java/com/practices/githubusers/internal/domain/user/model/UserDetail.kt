package com.practices.githubusers.internal.domain.user.model

import com.practices.githubusers.internal.domain.base.extensions.empty

data class UserDetail(
    val userId: Int,
    val userName: String,
    val avatar: String,
    val location: String,
    val blogUrl: String,
    val followers: Int,
    val following: Int
) {

    companion object {
        fun empty() = UserDetail(
            userId = 0,
            userName = String.empty(),
            avatar = String.empty(),
            location = String.empty(),
            blogUrl = String.empty(),
            followers = 0,
            following = 0
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDetail

        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }
}
