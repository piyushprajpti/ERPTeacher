package com.piyushprajpti.erpteacher

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform