package com.practices.githubusers.internal.domain.user.usercase

import kotlinx.coroutines.flow.Flow
import com.practices.githubusers.internal.domain.base.ResourceUseCase
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.internal.domain.user.repository.UserRepository
import javax.inject.Inject

/**
 * A UseCase responsible for fetching a list of users from the [UserRepository].
 *
 * This UseCase encapsulates the logic for retrieving user data from the repository.
 * It provides a single function, `execute()`, which returns a [Flow] of [Resource<List<UserItem>>].
 **/

class GetGithubUsersUseCase @Inject constructor(
    private val repo: UserRepository
) : ResourceUseCase<List<UserItem>, GetGithubUsersUseCase.Params>() {

    override fun execute(params: Params): Flow<Resource<List<UserItem>>> {
        return repo.getGithubUsers(params.sinceId)
    }

    data class Params(val sinceId: Int)
}