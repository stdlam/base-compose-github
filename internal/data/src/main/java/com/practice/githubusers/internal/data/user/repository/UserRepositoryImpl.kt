package com.practice.githubusers.internal.data.user.repository

import com.practice.githubusers.internal.data.source.network.repository.NetworkRepository
import com.practice.githubusers.internal.data.source.network.service.UserServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import com.practice.githubusers.internal.data.user.mapper.GithubUsersMapper
import com.practice.githubusers.internal.data.user.mapper.UserProfileMapper
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.user.model.UserDetail
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.internal.domain.user.repository.UserRepository

class UserRepositoryImpl(
    private val service: UserServiceApi
) : UserRepository, NetworkRepository() {

    override fun getGithubUsers(sinceId: Int): Flow<Resource<List<UserItem>>> {
        return safeNetworkFlow(
            service.getRemoteUsers(sinceId),
            GithubUsersMapper()
        )
    }

    override fun getProfile(username: String): Flow<Resource<UserDetail>> =
        safeNetworkFlow(
            service.getUserProfile(username),
            UserProfileMapper()
        )
}