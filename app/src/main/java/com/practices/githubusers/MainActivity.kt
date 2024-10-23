package com.practices.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practices.githubusers.internal.core.navigation.compositionLocal.LocalNavigator
import com.practices.githubusers.internal.core.navigation.compositionLocal.Navigator
import com.practices.githubusers.graph.Destination
import com.practices.githubusers.ui.theme.GithubUsersTheme
import com.practices.githubusers.graph.MainNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), Navigator {

    private lateinit var navController: NavHostController

    override var bottomSheetDestination: String by mutableStateOf(Destination.DEFAULT_BTS)
    override fun goToNextScreen(nextDestination: String) {
        navController.navigate(nextDestination)
    }

    override fun <T : Any> goToNextScreenWithArgument(serializer: T) {

    }

    override fun goBack() {
        if (navController.currentDestination?.route == Destination.HOME_SCREEN) {
            finish()
        } else {
            navController.navigateUp()
        }
    }

    override fun close() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            GithubUsersTheme {
                CompositionLocalProvider(
                    LocalNavigator provides this,
                    LocalDensity provides Density(
                        density = LocalDensity.current.density,
                        fontScale = 1f
                    )
                ) {
                    Scaffold {
                        Box(modifier = Modifier.padding(it)) {
                            MainNavGraph(
                                startDestination = Destination.HOME_SCREEN,
                                navHostController = navController
                            )
                        }
                    }
                }
            }
        }
    }

}