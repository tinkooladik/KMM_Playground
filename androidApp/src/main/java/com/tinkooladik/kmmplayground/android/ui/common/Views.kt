package com.tinkooladik.kmmplayground.android.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tinkooladik.kmmplayground.android.R
import com.tinkooladik.kmmplayground.android.ui.theme.PlaygroundTheme

@Composable
fun ErrorView(
    error: UiError,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Filled.Warning, null)
        Text(
            text = stringResource(id = error.resId),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview("ErrorView")
@Preview("ErrorView (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorViewPreview() {
    PlaygroundTheme {
        Surface {
            ErrorView(UiError.NoConnection)
        }
    }
}

@Composable
fun EmptyResult(
    textResId: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = textResId),
            textAlign = TextAlign.Center
        )
    }
}

@Preview("EmptyResult")
@Preview("EmptyResult (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmptyResultPreview() {
    PlaygroundTheme {
        Surface {
            EmptyResult(R.string.empty_launches)
        }
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Text(
            text = stringResource(id = R.string.loading),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview("Loading")
@Preview("Loading (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoadingViewPreview() {
    PlaygroundTheme {
        Surface {
            LoadingView()
        }
    }
}

@Composable
fun <V> UniversalAsync(
    async: Async<V>,
    onSuccess: @Composable (V) -> Unit,
    modifier: Modifier = Modifier,
    emptyResId: Int = 0
) {
    with(async) {
        when (this) {
            is Async.Success -> {
                onSuccess(value)
            }
            Async.EmptyData -> EmptyResult(
                textResId = emptyResId,
                modifier = modifier
            )
            is Async.Fail -> ErrorView(
                error = error,
                modifier = modifier
            )
            Async.Loading -> LoadingView(modifier = modifier)
        }
    }
}
