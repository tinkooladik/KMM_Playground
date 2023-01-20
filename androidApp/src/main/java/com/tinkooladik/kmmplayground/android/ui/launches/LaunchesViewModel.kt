package com.tinkooladik.kmmplayground.android.ui.launches

import com.tinkooladik.kmmplayground.android.ui.common.Async
import comtinkooladikkmmplaygroundsharedcache.Launch

data class LaunchesUiState(
    val launches: Async<List<Launch>> = Async.Loading
)

class LaunchesViewModel {

}