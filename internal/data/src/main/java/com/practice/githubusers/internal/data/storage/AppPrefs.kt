package com.practice.githubusers.internal.data.storage

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPrefs @Inject constructor(
    private val prefs: NormalSharedPreferences
) {

    fun hasFirstRun(): Boolean {
        return prefs.appFirstRun
    }

    fun setFirstRun() {
        prefs.appFirstRun = false
    }

    fun hasOnBoardingStarted(): Boolean {
        return prefs.onBoardingStarted
    }

    fun setOnBoardingStarted() {
        prefs.onBoardingStarted = true
    }
}