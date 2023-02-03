package com.tinkooladik.kmmplayground

import com.tinkooladik.kmmplayground.cache.Database
import com.tinkooladik.kmmplayground.cache.DatabaseDriverFactory
import com.tinkooladik.kmmplayground.entity.RocketLaunch
import com.tinkooladik.kmmplayground.network.PlaygroundApi
import io.ktor.client.call.*
import io.ktor.client.request.*

class PlaygroundRepository(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = PlaygroundApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }

    @Throws(Exception::class)
    fun getLaunch(flightNumber: Int): RocketLaunch {
        return database.getLaunch(flightNumber)
    }
}