package com.practice.githubusers.internal.data.user.mapper

import com.practice.githubusers.internal.data.source.database.githubusers.LocalUser
import com.practices.githubusers.internal.domain.base.mapper.Mapper
import com.practices.githubusers.internal.domain.user.model.UserItem

// This mapper is not used because this app doesn't use local database anymore.
class LocalUserMapper : Mapper<List<LocalUser>, List<UserItem>> {
    override fun map(from: List<LocalUser>): List<UserItem> {
        return from.map { localUser ->
            UserItem(
                localUser.id,
                localUser.userName,
                localUser.avatar,
                localUser.url
            )
        }
    }

}