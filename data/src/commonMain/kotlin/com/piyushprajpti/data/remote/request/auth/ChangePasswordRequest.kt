package com.piyushprajpti.data.remote.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordRequest(
    val userId: String,
    val currentPassword: String,
    val newPassword: String
)
