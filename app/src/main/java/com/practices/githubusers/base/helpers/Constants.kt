package com.practices.githubusers.base.helpers

import com.practices.githubusers.BuildConfig

object Constants {
    val isDebugMode: Boolean     = BuildConfig.DEBUG
    const val isDevMode: Boolean = BuildConfig.FLAVOR == "dev"
}