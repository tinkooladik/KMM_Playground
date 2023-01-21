package com.tinkooladik.kmmplayground.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tinkooladik.kmmplayground.android.di.DiContainer
import com.tinkooladik.kmmplayground.android.ui.home.HomeRoute
import com.tinkooladik.kmmplayground.android.ui.home.HomeViewModel
import com.tinkooladik.kmmplayground.android.ui.launches.LaunchesRoute
import com.tinkooladik.kmmplayground.android.ui.launches.LaunchesViewModel

@Composable
fun PlaygroundNavGraph(
    diContainer: DiContainer,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = PlaygroundDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(PlaygroundDestinations.HOME_ROUTE) {
            HomeRoute(
                HomeViewModel(diContainer.greetingService),
                openDrawer
            )
        }
        composable(PlaygroundDestinations.LAUNCHES_ROUTE) {
            LaunchesRoute(
                LaunchesViewModel(diContainer.repository),
                openDrawer
            )
        }
    }
}