package com.piyushprajpti.data.remote.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
    val tenantId: String
)