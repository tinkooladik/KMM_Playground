package com.tinkooladik.kmmplayground.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkooladik.kmmplayground.GreetingService
import com.tinkooladik.kmmplayground.android.ui.common.Async
import com.tinkooladik.kmmplayground.android.ui.common.toUiError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val greeting: Async<String>
)

class HomeViewModel(
    private val greetingService: GreetingService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(Async.Loading))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                greetingService.greeting()
            }.onSuccess { greeting ->
                _uiState.update {
                    it.copy(
                        greeting = Async.Success(greeting)
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        greeting = Async.Fail(error.toUiError())
                    )
                }
            }
        }
    }
}
