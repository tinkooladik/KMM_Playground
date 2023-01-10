package com.tinkooladik.kmmplayground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform