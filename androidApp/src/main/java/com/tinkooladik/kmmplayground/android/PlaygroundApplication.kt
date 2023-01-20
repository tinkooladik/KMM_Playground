package com.tinkooladik.kmmplayground.android

import android.app.Application
import com.tinkooladik.kmmplayground.android.di.DiContainer
import com.tinkooladik.kmmplayground.android.di.DiContainerImpl

class PlaygroundApplication : Application() {

    lateinit var diContainer: DiContainer

    override fun onCreate() {
        super.onCreate()
        diContainer = DiContainerImpl(this)
    }
}