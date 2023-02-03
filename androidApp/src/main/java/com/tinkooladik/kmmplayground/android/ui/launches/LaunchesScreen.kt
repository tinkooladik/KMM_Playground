package com.tinkooladik.kmmplayground.android.ui.launches

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tinkooladik.kmmplayground.android.R
import com.tinkooladik.kmmplayground.android.ui.AppTopBar
import com.tinkooladik.kmmplayground.android.ui.common.Async
import com.tinkooladik.kmmplayground.android.ui.common.UniversalAsync
import com.tinkooladik.kmmplayground.android.ui.theme.PlaygroundTheme
import com.tinkooladik.kmmplayground.entity.RocketLaunch
import com.tinkooladik.kmmplayground.entity.TestData.LAUNCH_1
import com.tinkooladik.kmmplayground.entity.TestData.LAUNCH_2
import com.tinkooladik.kmmplayground.entity.TestData.LAUNCH_3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchesScreen(
    uiState: LaunchesUiState,
    openDrawer: () -> Unit,
    navigateToDetails: (Int) -> Unit,
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

        UniversalAsync(
            async = uiState.launches,
            onSuccess = { launches ->
                LaunchesList(
                    launches = launches,
                    modifier = contentModifier,
                    navigateToDetails = navigateToDetails
                )
            },
            modifier = contentModifier,
            emptyResId = R.string.empty_launches
        )
    }
}

@Composable
fun LaunchesList(
    launches: List<RocketLaunch>,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(launches) { launch ->
            LaunchCard(
                launch = launch,
                modifier = Modifier.fillMaxWidth(),
                navigateToDetails = navigateToDetails
            )
        }
    }
}

@Composable
fun LaunchCard(
    launch: RocketLaunch,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .clickable { navigateToDetails(launch.flightNumber) }
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(modifier = Modifier) {
                Text(
                    text = launch.missionName,
                    modifier = Modifier
                        .weight(1f),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Image(
                    painter = painterResource(
                        id = if (launch.launchSuccess == true) {
                            R.drawable.ic_launch_success
                        } else {
                            R.drawable.ic_launch_fail
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                )
            }
            Text(
                text = "${launch.rocket.name}, ${launch.rocket.type} - ${launch.launchYear}",
                modifier = Modifier,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = launch.details ?: stringResource(id = R.string.empty_details),
                modifier = Modifier
                    .defaultMinSize(minHeight = 64.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview("Launch card")
@Preview("Launch card (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LaunchCardPreview() {
    PlaygroundTheme {
        Surface {
            LaunchCard(
                launch = LAUNCH_1,
                modifier = Modifier,
                navigateToDetails = {}
            )
        }
    }
}

@Preview("Launches list")
@Preview("Launches list (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LaunchListPreview() {
    PlaygroundTheme {
        Surface {
            LaunchesList(
                launches = listOf(LAUNCH_1, LAUNCH_2, LAUNCH_3),
                modifier = Modifier,
                navigateToDetails = {}
            )
        }
    }
}

@Preview("LaunchesScreen")
@Preview("LaunchesScreen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    PlaygroundTheme {
        Surface {
            LaunchesScreen(
                uiState = LaunchesUiState(
                    Async.Success(listOf(LAUNCH_1, LAUNCH_2, LAUNCH_3))
                ),
                openDrawer = {},
                navigateToDetails = {}
            )
        }
    }
}
