package com.practices.githubusers.internal.domain.user.usercase

import com.practices.githubusers.internal.domain.base.ResourceUseCase
import com.practices.githubusers.internal.domain.base.functional.Resource
import com.practices.githubusers.internal.domain.user.model.UserDetail
import com.practices.githubusers.internal.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A UseCase responsible for fetching a list of users from the [UserRepository].
 *
 * This UseCase encapsulates the logic for retrieving user data from the repository.
 * It provides a single function, `execute()`, which returns a [Flow] of [Resource<UserDetail>].
 **/

class GetProfileUseCase @Inject constructor(
    private val repo: UserRepository
) : ResourceUseCase<UserDetail, String>() {
    override fun execute(params: String): Flow<Resource<UserDetail>> {
        return repo.getProfile(params)
    }

}