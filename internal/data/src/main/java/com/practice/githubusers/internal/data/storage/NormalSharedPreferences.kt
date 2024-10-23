package com.practice.githubusers.internal.data.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NormalSharedPreferences @Inject constructor(
    @ApplicationContext applicationContext: Context,
    storageName: String
) : BaseSharedPreferences() {

    init {
        sharedPreferences = applicationContext.getSharedPreferences(
            "${storageName}_$VERSION",
            Context.MODE_PRIVATE
        )
    }

    var appFirstRun: Boolean
        get() = this[PREFS_APP_FIRST_RUN, false]
        set(value) {
            this[PREFS_APP_FIRST_RUN] = value
        }

    var onBoardingStarted: Boolean
        get() = this[PREFS_BOARDING_STARTED, false]
        set(value) {
            this[PREFS_BOARDING_STARTED] = value
        }

    var userAuth: String
        get() = this[PREFS_USER_OWNER, ""]
        set(value) {
            this[PREFS_USER_OWNER] = value
        }

    fun remove() {
        super.remove(
            PREFS_USER_OWNER
        )
    }

    companion object {
        const val PREFS_APP_FIRST_RUN = "app_first_run"
        const val PREFS_BOARDING_STARTED = "on_boarding_started"
        const val PREFS_USER_OWNER = "user_owner"
    }
}