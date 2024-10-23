package com.practice.githubusers.internal.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.githubusers.internal.data.source.database.githubusers.LocalUser
import com.practice.githubusers.internal.data.source.database.githubusers.dao.GithubUsersDao

@Database(
    entities = [LocalUser::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun githubUsersDao(): GithubUsersDao
}