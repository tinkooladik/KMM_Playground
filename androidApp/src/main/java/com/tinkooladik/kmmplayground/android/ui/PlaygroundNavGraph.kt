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
import com.tinkooladik.kmmplayground.android.ui.launches.details.LaunchDetailsRoute
import com.tinkooladik.kmmplayground.android.ui.launches.details.LaunchDetailsViewModel

@Composable
fun PlaygroundNavGraph(
    diContainer: DiContainer,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit = {},
    onLaunchSelected: (Int) -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestination.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(AppDestination.Home.route) {
            HomeRoute(
                viewModel = HomeViewModel(diContainer.greetingService),
                openDrawer = openDrawer
            )
        }
        composable(AppDestination.Launches.route) {
            LaunchesRoute(
                viewModel = LaunchesViewModel(diContainer.repository),
                openDrawer = openDrawer,
                navigateToDetails = onLaunchSelected
            )
        }
        composable(AppDestination.LaunchDetails.route) { navBackStackEntry ->
            val flightNumber = navBackStackEntry.arguments?.getString("flightNumber")?.toIntOrNull()
            requireNotNull(flightNumber) { "Flight number is required" }
            LaunchDetailsRoute(
                viewModel = LaunchDetailsViewModel(diContainer.repository, flightNumber),
                onBackPressed = { navController.popBackStack(AppDestination.Launches.route, false) }
            )
        }
    }
}