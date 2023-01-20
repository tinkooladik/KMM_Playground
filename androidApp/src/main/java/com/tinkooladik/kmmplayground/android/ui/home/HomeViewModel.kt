package com.tinkooladik.kmmplayground.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkooladik.kmmplayground.GreetingService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean,
    val greeting: String? = null,
    val errorMessage: String? = null
)

class HomeViewModel(
    private val greetingService: GreetingService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                greetingService.greeting()
            }.onSuccess { greeting ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        greeting = greeting
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }
        }
    }
}