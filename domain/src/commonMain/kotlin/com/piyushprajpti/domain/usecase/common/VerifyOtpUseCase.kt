package com.piyushprajpti.domain.usecase.common

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.repository.common.CommonRepository

class VerifyOtpUseCase(private val repository: CommonRepository) {
    suspend operator fun invoke(email: String, tenantId: String, otp: String): Result<String> {
        return repository.verifyOtp(email, tenantId, otp)
    }
}