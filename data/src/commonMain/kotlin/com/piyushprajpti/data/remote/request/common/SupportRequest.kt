package com.piyushprajpti.data.remote.request.common

import kotlinx.serialization.Serializable

@Serializable
data class SupportRequest(
    val name: String,
    val email: String,
    val subject: String,
    val description: String
)
