package com.tinkooladik.kmmplayground.android.ui.launches.details

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

data class LaunchDetailsUiState(
    val launch: Async<RocketLaunch> = Async.Loading
)

class LaunchDetailsViewModel(
    private val repository: PlaygroundRepository,
    private val flightNumber: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(LaunchDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            kotlin.runCatching { repository.getLaunch(flightNumber) }
                .onSuccess { launch ->
                    _uiState.update {
                        it.copy(
                            launch = Async.Success(launch)
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(launch = Async.Fail(error.toUiError()))
                    }
                }
        }
    }
}