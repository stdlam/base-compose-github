package com.practice.githubusers.internal.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.practice.githubusers.internal.data.source.database.AppDatabase
import com.practice.githubusers.internal.data.source.database.migrations.MIGRATION_1_2
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).addMigrations(MIGRATION_1_2)
            .build()

    @Singleton
    @Provides
    fun provideGithubUsersDao(appDatabase: AppDatabase) = appDatabase.githubUsersDao()
}