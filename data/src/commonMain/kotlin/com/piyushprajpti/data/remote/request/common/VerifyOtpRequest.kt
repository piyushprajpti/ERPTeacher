package com.piyushprajpti.data.remote.request.common

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequest(
    val email: String,
    val tenantId: String,
    val otp: String
)
