package com.tinkooladik.kmmplayground.android.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import com.tinkooladik.kmmplayground.android.R
import com.tinkooladik.kmmplayground.android.ui.AppTopBar
import com.tinkooladik.kmmplayground.android.ui.common.*

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

        UniversalAsync(
            async = uiState.greeting,
            onSuccess = { greeting ->
                Greeting(
                    greeting = greeting,
                    modifier = contentModifier
                )
            },
            modifier = contentModifier,
            emptyResId = R.string.default_greeting
        )
    }
}

@Composable
fun Greeting(
    greeting: String,
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = greeting,
            textAlign = TextAlign.Center
        )
    }
}
