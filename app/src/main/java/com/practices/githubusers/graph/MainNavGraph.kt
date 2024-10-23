package com.practices.githubusers.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practices.githubusers.graph.DestinationArg.USER_PROFILE_ARG
import com.practices.githubusers.internal.core.navigation.compositionLocal.LocalNavigator
import com.practices.githubusers.presentation.detail.DetailUserScreenWrapper
import com.practices.githubusers.presentation.home.HomeScreenWrapper

@Composable
fun MainNavGraph(
    startDestination: String,
    navHostController: NavHostController = rememberNavController()
) {
    val navigator = LocalNavigator.current
    val coroutinesScope = rememberCoroutineScope()

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ) {
        composable(route = Destination.USER_PROFILE) { navBackStackEntry ->
            val username = navBackStackEntry.arguments?.getString(USER_PROFILE_ARG)
            if (username?.isNotEmpty() == true) {
                DetailUserScreenWrapper(
                    username = username,
                    onClick = {

                    },
                    onBack = {
                        navigator.goBack()
                    }
                )
            }

        }

        composable(route = Destination.HOME_SCREEN) {
            HomeScreenWrapper(
                onUserClick = { item, _ ->
                    navigator.goToNextScreen(Destination.USER_PROFILE.replace("{$USER_PROFILE_ARG}", item.name))
                }
            )
        }
    }
}