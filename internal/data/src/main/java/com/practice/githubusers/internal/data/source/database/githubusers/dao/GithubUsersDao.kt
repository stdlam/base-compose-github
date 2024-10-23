package com.practice.githubusers.internal.data.source.database.githubusers.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practice.githubusers.internal.data.source.database.githubusers.LocalUser
import com.practice.githubusers.internal.data.source.database.githubusers.LocalUser.Companion.TABLE_NAME

@Dao
interface GithubUsersDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY user_id DESC")
    fun getLocalUsers(): List<LocalUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<LocalUser>)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()
}