package com.tinkooladik.kmmplayground.android.ui.launches

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LaunchesRoute(
    viewModel: LaunchesViewModel,
    openDrawer: () -> Unit,
    navigateToDetails: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchesScreen(
        uiState = uiState,
        openDrawer = openDrawer,
        navigateToDetails = navigateToDetails
    )
}