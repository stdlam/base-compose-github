package com.practice.githubusers.internal.data.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.practice.githubusers.internal.data.di.scopes.SecretInfo
import javax.inject.Inject

class SecuredSharedPreferences @Inject constructor(
    @ApplicationContext applicationContext: Context,
    @SecretInfo secretName: String
) : BaseSharedPreferences() {

    /*init {
        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            "${secretName}_${VERSION}",
            masterKey,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }*/

    init {
        sharedPreferences = applicationContext.getSharedPreferences(
            "${secretName}_$VERSION",
            Context.MODE_PRIVATE
        )
    }

    var userAccessToken: String
        get() = this[PREFS_USER_ACCESS_TOKEN, ""]
        set(value) {
            this[PREFS_USER_ACCESS_TOKEN] = value
        }

    var userRefreshToken: String
        get() = this[PREFS_USER_REFRESH_TOKEN, ""]
        set(value) {
            this[PREFS_USER_REFRESH_TOKEN] = value
        }

    fun remove() {
        this.userAccessToken = ""
        this.userRefreshToken = ""
    }

    companion object {
        const val PREFS_USER_ACCESS_TOKEN = "user_access_token"
        const val PREFS_USER_REFRESH_TOKEN = "user_refresh_token"
    }
}