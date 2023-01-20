package com.tinkooladik.kmmplayground.android.ui.launches

import androidx.compose.runtime.Composable

@Composable
fun LaunchesRoute(
    viewModel: LaunchesViewModel,
    openDrawer: () -> Unit
) {
    LaunchesScreen(openDrawer = openDrawer)
}