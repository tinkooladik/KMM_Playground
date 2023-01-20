package com.tinkooladik.kmmplayground.android.ui.common

import com.tinkooladik.kmmplayground.android.R
import java.net.SocketTimeoutException

sealed class UiError(val resId: Int) {

    object NoConnection : UiError(R.string.error_no_connection)
    object UnknownError : UiError(R.string.error_unknown)
}

fun Throwable.toUiError(): UiError =
    when (this) {
        is SocketTimeoutException -> UiError.NoConnection
        else -> UiError.UnknownError
    }
