package com.tinkooladik.kmmplayground.android.ui.launches

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
import com.tinkooladik.kmmplayground.android.ui.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchesScreen(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Launches",
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

        Box(
            modifier = contentModifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "launches screen",
                textAlign = TextAlign.Center
            )
        }
    }
}
