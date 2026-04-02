package com.piyushprajpti.domain.usecase.common

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.repository.common.CommonRepository

class ResetPasswordUseCase(private val repository: CommonRepository) {
    suspend operator fun invoke(token: String, newPassword: String): Result<Unit> {
        return repository.resetPassword(token, newPassword)
    }
}