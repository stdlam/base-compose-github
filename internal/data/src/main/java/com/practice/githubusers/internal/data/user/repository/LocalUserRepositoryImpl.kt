package com.practice.githubusers.internal.data.user.repository

import com.practice.githubusers.internal.data.source.database.githubusers.dao.GithubUsersDao
import com.practice.githubusers.internal.data.source.database.repository.LocalRepository
import com.practice.githubusers.internal.data.user.mapper.LocalUserMapper
import com.practices.githubusers.internal.domain.user.model.UserItem
import com.practices.githubusers.internal.domain.user.repository.LocalUserRepository

// This class is not used because this app doesn't use local database anymore.
class LocalUserRepositoryImpl(val dao: GithubUsersDao) : LocalUserRepository, LocalRepository() {
    override fun getLocalUsers(success: (List<UserItem>) -> Unit, error: (Throwable?) -> Unit) {
        asyncDataLocal(
            query = {
                success.invoke(
                    LocalUserMapper().map(dao.getLocalUsers())
                )
            },
            onError = {
                error.invoke(it)
            }
        )
    }

    override fun insertAll(
        users: List<UserItem>,
        success: (Int) -> Unit,
        error: (Throwable?) -> Unit
    ) {
        asyncDataLocal(
            query = {
                //dao.insertAll(users)
                success.invoke(1)
            },
            onError = {
                error.invoke(it)
            }
        )
    }

    override fun removeAll(success: (Int) -> Unit, error: (Throwable?) -> Unit) {
        TODO("Not yet implemented")
    }

}