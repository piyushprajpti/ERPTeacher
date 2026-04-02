package com.piyushprajpti.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpResponse(
    val status: String,
    val data: VerifyOtpData
)

@Serializable
data class VerifyOtpData(
    val message: String,
    val resetToken: String
)
