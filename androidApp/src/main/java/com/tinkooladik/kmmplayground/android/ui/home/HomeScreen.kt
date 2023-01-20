package com.tinkooladik.kmmplayground.android.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import com.tinkooladik.kmmplayground.android.ui.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Home",
                openDrawer = openDrawer,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier
    ) { innerPadding ->
        val contentModifier = Modifier
            .padding(innerPadding)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize()

        HomeContent(
            uiState = uiState,
            modifier = contentModifier
        )
    }
}

@Composable
fun HomeContent(
    uiState: HomeUiState,
    modifier: Modifier
) {
    // todo this should be handled with Result class or something like this
    val text = if (uiState.isLoading) "Loading...." else uiState.greeting ?: uiState.errorMessage
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text ?: "Something went wrong",
            textAlign = TextAlign.Center
        )
    }
}