package com.piyushprajpti.domain.repository.common

import com.piyushprajpti.core.http.Result

interface CommonRepository {
    suspend fun submitSupport(name: String, email: String, subject: String, description: String): Result<Unit>
    suspend fun forgotPassword(email: String, tenantId: String): Result<Unit>
    suspend fun verifyOtp(email: String, tenantId: String, otp: String): Result<String>
    suspend fun resetPassword(token: String, newPassword: String): Result<Unit>
}
