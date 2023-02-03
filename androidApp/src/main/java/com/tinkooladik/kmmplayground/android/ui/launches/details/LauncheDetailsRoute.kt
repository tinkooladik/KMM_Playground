package com.tinkooladik.kmmplayground.android.ui.launches.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LaunchDetailsRoute(
    viewModel: LaunchDetailsViewModel,
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchDetailsScreen(uiState = uiState, onBack = onBackPressed)
}