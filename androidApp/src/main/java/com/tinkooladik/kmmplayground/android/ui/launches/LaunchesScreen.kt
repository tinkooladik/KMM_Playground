package com.tinkooladik.kmmplayground.android.ui.launches

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tinkooladik.kmmplayground.android.R
import com.tinkooladik.kmmplayground.android.ui.AppTopBar
import com.tinkooladik.kmmplayground.android.ui.common.UniversalAsync
import com.tinkooladik.kmmplayground.android.ui.theme.PlaygroundTheme
import com.tinkooladik.kmmplayground.entity.Links
import com.tinkooladik.kmmplayground.entity.Rocket
import com.tinkooladik.kmmplayground.entity.RocketLaunch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchesScreen(
    uiState: LaunchesUiState,
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


        UniversalAsync(
            async = uiState.launches,
            onSuccess = { launches ->
                LaunchesList(
                    launches = launches,
                    modifier = contentModifier
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
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(launches) { launch ->
            LaunchCard(
                launch = launch,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LaunchCard(
    launch: RocketLaunch,
    modifier: Modifier = Modifier
) {
    OutlinedCard(modifier = modifier.padding(vertical = 8.dp)) {
        Text(
            text = launch.missionName,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview("Launch card")
@Preview("Launch card (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LaunchCardPreview() {
    PlaygroundTheme {
        Surface {
            LaunchCard(
                launch = previewLaunch,
                modifier = Modifier
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
                launches = listOf(previewLaunch, previewLaunch, previewLaunch),
                modifier = Modifier
            )
        }
    }
}

private val previewLaunch = RocketLaunch(
    flightNumber = 0,
    missionName = "Mission Name",
    launchYear = 2023,
    launchDateUTC = "",
    rocket = Rocket(
        id = "id",
        name = "Rocket Name",
        type = "Rocket Type"
    ),
    details = "Details",
    launchSuccess = true,
    links = Links(
        missionPatchUrl = null,
        articleUrl = null
    )
)