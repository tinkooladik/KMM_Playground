package com.tinkooladik.kmmplayground.android.di

import android.content.Context
import com.tinkooladik.kmmplayground.GreetingService
import com.tinkooladik.kmmplayground.PlaygroundRepository
import com.tinkooladik.kmmplayground.cache.DatabaseDriverFactory

interface DiContainer {
    val repository: PlaygroundRepository
    val greetingService: GreetingService
}

class DiContainerImpl(appContext: Context) : DiContainer {
    override val repository: PlaygroundRepository by lazy {
        PlaygroundRepository(DatabaseDriverFactory(appContext))
    }
    override val greetingService: GreetingService by lazy {
        GreetingService(repository)
    }
}