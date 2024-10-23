package com.practices.githubusers.internal.domain.user.repository

import kotlinx.coroutines.flow.Flow
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.user.model.UserDetail
import com.practices.githubusers.internal.domain.user.model.UserItem

interface UserRepository {
    fun getGithubUsers(sinceId: Int): Flow<Resource<List<UserItem>>>

    fun getProfile(username: String): Flow<Resource<UserDetail>>
}