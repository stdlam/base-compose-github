package com.practice.githubusers.internal.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.practice.githubusers.internal.data.di.scopes.SecretInfo
import com.practice.githubusers.internal.data.di.scopes.StorageInfo
import com.practice.githubusers.internal.data.storage.NormalSharedPreferences
import com.practice.githubusers.internal.data.storage.SecuredSharedPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @StorageInfo
    @Provides
    fun provideStorageName() = "app_storage_shared_prefs"

    @SecretInfo
    @Provides
    fun provideSecretName() = "app_secret_shared_prefs"

    @Singleton
    @Provides
    fun provideDefaultSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideSecuredLocalStorage(
        @ApplicationContext context: Context,
        @SecretInfo secretName: String
    ) = SecuredSharedPreferences(context, secretName)

    @Singleton
    @Provides
    fun provideNormalLocalStorage(
        @ApplicationContext context: Context,
        @StorageInfo storageName: String
    ) = NormalSharedPreferences(context, storageName)
}