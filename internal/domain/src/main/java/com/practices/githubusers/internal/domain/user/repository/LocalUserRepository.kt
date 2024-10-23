package com.practices.githubusers.internal.domain.user.repository

import com.practices.githubusers.internal.domain.user.model.UserItem

interface LocalUserRepository {
    fun getLocalUsers(success: (List<UserItem>) -> Unit, error: (Throwable?) -> Unit)
    fun insertAll(users: List<UserItem>, success: (Int) -> Unit, error: (Throwable?) -> Unit)
    fun removeAll(success: (Int) -> Unit, error: (Throwable?) -> Unit)
}