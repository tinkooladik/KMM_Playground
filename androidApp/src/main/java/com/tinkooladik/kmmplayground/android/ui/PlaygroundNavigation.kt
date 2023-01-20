package com.tinkooladik.kmmplayground.android.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

object PlaygroundDestinations {
    const val HOME_ROUTE = "home"
    const val LAUNCHES_ROUTE = "launches"
}

class PlaygroundNavigationActions(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(PlaygroundDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToLaunches: () -> Unit = {
        navController.navigate(PlaygroundDestinations.LAUNCHES_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
