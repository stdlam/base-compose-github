package com.practices.githubusers.graph

import com.practices.githubusers.graph.DestinationArg.USER_PROFILE_ARG

object DestinationArg {
    const val USER_PROFILE_ARG = "username"
}

object Destination {
    const val USER_PROFILE = "detail?$USER_PROFILE_ARG={$USER_PROFILE_ARG}"
    const val HOME_SCREEN = "home"
    const val DEFAULT_BTS = ""
}