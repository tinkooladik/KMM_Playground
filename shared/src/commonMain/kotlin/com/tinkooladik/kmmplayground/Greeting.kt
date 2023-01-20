package com.tinkooladik.kmmplayground

import com.tinkooladik.kmmplayground.entity.RocketLaunch
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class GreetingService(private val repository: PlaygroundRepository) {
    private val platform: Platform = getPlatform()

    @Throws(Exception::class)
    suspend fun greeting(): String {
        val rockets: List<RocketLaunch> = repository.getLaunches(true)
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        return "Guess what it is! > ${platform.name.reversed()}!" +
                "\nThere are only ${daysUntilNewYear()} left until New Year! 🎆" +
                "\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC} 🚀"
    }
}