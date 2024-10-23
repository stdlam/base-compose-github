package com.practice.githubusers.internal.data.di

import com.practice.githubusers.internal.data.source.network.service.UserServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.practice.githubusers.internal.data.user.repository.UserRepositoryImpl
import com.practices.githubusers.internal.domain.user.repository.UserRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserRepository(apiService: UserServiceApi): UserRepository =
        UserRepositoryImpl(apiService)
}