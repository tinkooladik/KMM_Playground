package com.tinkooladik.kmmplayground.android.ui.launches.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tinkooladik.kmmplayground.android.R
import com.tinkooladik.kmmplayground.android.ui.AppBackTopBar
import com.tinkooladik.kmmplayground.android.ui.common.Async
import com.tinkooladik.kmmplayground.android.ui.common.UniversalAsync
import com.tinkooladik.kmmplayground.android.ui.launches.LaunchStatus
import com.tinkooladik.kmmplayground.android.ui.theme.PlaygroundTheme
import com.tinkooladik.kmmplayground.entity.RocketLaunch
import com.tinkooladik.kmmplayground.entity.TestData.LAUNCH_1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchDetailsScreen(
    uiState: LaunchDetailsUiState,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            AppBackTopBar(
                title = "Launch Details",
                onBack = onBack,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier
    ) { innerPadding ->
        val contentModifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize()

        UniversalAsync(
            async = uiState.launch,
            onSuccess = { launches ->
                LaunchDetails(
                    launch = launches,
                    modifier = contentModifier
                )
            },
            modifier = contentModifier,
            emptyResId = R.string.empty_launches
        )
    }
}

@Composable
fun LaunchDetails(
    launch: RocketLaunch,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {

        LaunchStatus(
            launch = launch,
            modifier = Modifier.align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Mission ${launch.missionName}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Flight number: ${launch.flightNumber}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Mission name: ${launch.missionName}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Launch date: ${launch.launchDateUTC}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Rocket name: ${launch.rocket.name}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Rocket type: ${launch.rocket.type}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview("LaunchDetailsScreen")
@Preview("LaunchDetailsScreen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LaunchDetailsPreview() {
    PlaygroundTheme {
        Surface {
            LaunchDetailsScreen(
                uiState = LaunchDetailsUiState(
                    Async.Success(LAUNCH_1)
                )
            )
        }
    }
}

