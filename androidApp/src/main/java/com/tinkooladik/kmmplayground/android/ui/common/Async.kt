package com.tinkooladik.kmmplayground.android.ui.common

sealed interface Async<out V> {
    object Loading : Async<Nothing>
    object EmptyData : Async<Nothing>

    data class Success<out V>(val value: V) : Async<V>
    data class Fail<out V>(val error: UiError) : Async<V>
}
