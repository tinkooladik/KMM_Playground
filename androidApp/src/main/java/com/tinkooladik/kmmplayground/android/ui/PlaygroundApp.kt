package com.tinkooladik.kmmplayground.android.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tinkooladik.kmmplayground.android.di.DiContainer
import com.tinkooladik.kmmplayground.android.ui.theme.PlaygroundTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundApp(
    diContainer: DiContainer
) {
    PlaygroundTheme {
        val navController = rememberNavController()
        val navActions = remember(navController) {
            PlaygroundNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: PlaygroundDestinations.HOME_ROUTE

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateToHome = navActions.navigateToHome,
                    navigateToLaunches = navActions.navigateToLaunches,
                    closeDrawer = { coroutineScope.launch { drawerState.close() } }
                )
            },
            drawerState = drawerState,
            gesturesEnabled = true
        ) {
            PlaygroundNavGraph(
                diContainer = diContainer,
                navController = navController,
                openDrawer = { coroutineScope.launch { drawerState.open() } }
            )
        }
    }
}