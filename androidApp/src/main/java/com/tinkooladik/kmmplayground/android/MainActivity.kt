package com.tinkooladik.kmmplayground.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tinkooladik.kmmplayground.android.ui.PlaygroundApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val diContainer = (application as PlaygroundApplication).diContainer
        setContent {
            PlaygroundApp(diContainer)
        }
    }
}
