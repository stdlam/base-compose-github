package com.practices.githubusers.internal.domain.user.model

data class UserItem(
    val id: Int,
    val name: String,
    val avatar: String,
    var url: String
)
