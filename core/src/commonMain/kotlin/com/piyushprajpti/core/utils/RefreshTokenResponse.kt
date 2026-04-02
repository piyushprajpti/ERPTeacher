package com.piyushprajpti.core.utils

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val status: String,
    val data: RefreshTokenData
)

@Serializable
data class RefreshTokenData(
    val accessToken: String,
    val refreshToken: String
)