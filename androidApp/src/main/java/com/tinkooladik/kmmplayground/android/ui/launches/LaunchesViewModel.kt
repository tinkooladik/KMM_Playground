package com.tinkooladik.kmmplayground.android.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkooladik.kmmplayground.PlaygroundRepository
import com.tinkooladik.kmmplayground.android.ui.common.Async
import com.tinkooladik.kmmplayground.android.ui.common.toUiError
import com.tinkooladik.kmmplayground.entity.RocketLaunch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LaunchesUiState(
    val launches: Async<List<RocketLaunch>> = Async.Loading
)

class LaunchesViewModel(
    private val repository: PlaygroundRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LaunchesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchLaunches(false)
    }

    private fun fetchLaunches(forceRefresh: Boolean) {
        viewModelScope.launch {
            kotlin.runCatching { repository.getLaunches(forceRefresh) }
                .onSuccess { launches ->
                    _uiState.update {
                        it.copy(
                            launches = when {
                                launches.isEmpty() -> Async.EmptyData
                                else -> Async.Success(launches)
                            }
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(launches = Async.Fail(error.toUiError()))
                    }
                }
        }
    }
}