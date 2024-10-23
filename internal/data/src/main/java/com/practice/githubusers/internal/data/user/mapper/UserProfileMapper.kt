package com.practice.githubusers.internal.data.user.mapper

import com.practice.githubusers.internal.data.user.response.UserDataResponse
import com.practices.githubusers.internal.domain.base.mapper.Mapper
import com.practices.githubusers.internal.domain.user.model.UserDetail
import com.practices.githubusers.internal.domain.user.repository.UserRepository

/**
 * A UseCase responsible for mapping a user from the [UserRepository]
 * to a user [UserDetail] that can be displayed on UI.
 **/
class UserProfileMapper : Mapper<UserDataResponse, UserDetail> {

    override fun map(from: UserDataResponse): UserDetail {
        return from.toUser()
    }
}