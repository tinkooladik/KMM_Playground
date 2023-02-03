package com.tinkooladik.kmmplayground.android.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder

sealed class AppDestination(val route: String) {
    object Home : AppDestination("home")
    object Launches : AppDestination("launches")
    object LaunchDetails : AppDestination("launchDetails/{flightNumber}") {
        fun createRoute(flightNumber: Int): String = "launchDetails/$flightNumber"
    }
}

class PlaygroundNavigationActions(private val navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(AppDestination.Home.route) {
            navigateTopLevel()
        }
    }
    val navigateToLaunches: () -> Unit = {
        navController.navigate(AppDestination.Launches.route) {
            navigateTopLevel()
        }
    }
    val navigateToLaunchDetails: (Int) -> Unit = { flightNumber ->
        navController.navigate(AppDestination.LaunchDetails.createRoute(flightNumber))
    }

    private fun NavOptionsBuilder.navigateTopLevel() {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
