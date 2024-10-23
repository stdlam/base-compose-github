package com.practices.githubusers.internal.core.navigation.compositionLocal

import androidx.compose.runtime.staticCompositionLocalOf

interface Navigator {
    val bottomSheetDestination: String

    fun goToNextScreen(nextDestination: String)
    fun<T: Any> goToNextScreenWithArgument(serializer: T)
    fun goBack()
    fun closeBTS() = goBack()
    fun close()
}

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    noLocalProvidedFor("LocalNavigator")
}