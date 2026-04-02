package com.piyushprajpti.domain.usecase.common

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.repository.common.CommonRepository

class ForgotPasswordUseCase(private val repository: CommonRepository) {
    suspend operator fun invoke(email: String, tenantId: String): Result<Unit> {
        return repository.forgotPassword(email, tenantId)
    }
}