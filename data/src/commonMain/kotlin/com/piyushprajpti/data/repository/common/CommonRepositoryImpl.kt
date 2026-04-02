package com.piyushprajpti.data.repository.common

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.data.remote.request.common.ForgotPasswordRequest
import com.piyushprajpti.data.remote.request.common.ResetPasswordRequest
import com.piyushprajpti.data.remote.request.common.SupportRequest
import com.piyushprajpti.data.remote.request.common.VerifyOtpRequest
import com.piyushprajpti.data.remote.service.common.CommonApiService
import com.piyushprajpti.domain.repository.common.CommonRepository

class CommonRepositoryImpl(
    private val apiService: CommonApiService
) : CommonRepository {
    override suspend fun submitSupport(
        name: String,
        email: String,
        subject: String,
        description: String
    ): Result<Unit> {
        return apiService.submitSupport(SupportRequest(name, email, subject, description))
    }

    override suspend fun forgotPassword(email: String, tenantId: String): Result<Unit> {
        return apiService.forgotPassword(ForgotPasswordRequest(email, tenantId))
    }

    override suspend fun verifyOtp(email: String, tenantId: String, otp: String): Result<String> {
        return when (val result = apiService.verifyOtp(VerifyOtpRequest(email, tenantId, otp))) {
            is Result.Success -> Result.Success(result.data.data.resetToken)
            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun resetPassword(token: String, newPassword: String): Result<Unit> {
        return apiService.resetPassword(ResetPasswordRequest(token, newPassword))
    }
}
