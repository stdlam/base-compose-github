package com.practice.githubusers.internal.data.di

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.practice.githubusers.internal.data.di.scopes.MoshiNetworkAdapter
import com.practice.githubusers.internal.data.di.scopes.MoshiUserAdapter
import com.practice.githubusers.internal.data.source.network.provider.JsonBuilderProvider

@Module
@InstallIn(SingletonComponent::class)
class JsonModule {

    @Provides
    fun provideGson(): Gson = JsonBuilderProvider.gsonBuilder.create()

    @MoshiNetworkAdapter
    @Provides
    fun provideNetworkMoshi(): Moshi = JsonBuilderProvider.moshiNetworkBuilder.build()

    @MoshiUserAdapter
    @Provides
    fun provideUserMoshi(): Moshi = JsonBuilderProvider.moshiUserBuilder.build()
}