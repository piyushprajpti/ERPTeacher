package com.piyushprajpti.data.remote.request.common

import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordRequest(
    val email: String,
    val tenantId: String
)
