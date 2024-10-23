package com.practice.githubusers.internal.data.user.mapper

import com.practice.githubusers.internal.data.user.response.GithubUserData
import com.practices.githubusers.internal.domain.base.mapper.Mapper
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.internal.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * A UseCase responsible for mapping a list of users from the [UserRepository]
 * to a list of users that can be displayed on UI.
 **/

class GithubUsersMapper: Mapper<List<GithubUserData>, List<UserItem>> {
    override fun map(from: List<GithubUserData>): List<UserItem> {
        val users = from.map { githubUserData ->
            UserItem(
                githubUserData.id ?: 0,
                githubUserData.username ?: "",
                githubUserData.avatar ?: "",
                githubUserData.url ?: ""
            )
        }
        return users
    }
}