package com.practices.githubusers

import androidx.multidex.MultiDexApplication
import com.practices.githubusers.base.helpers.Constants
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppController : MultiDexApplication() {

    companion object {
        lateinit var instance: AppController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupLogging()
    }

    private fun setupLogging() {
        if (Constants.isDebugMode) {
            Timber.plant(Timber.DebugTree())
        }
    }
}