package com.piyushprajpti.domain.usecase.auth

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.repository.auth.AuthRepository

class ChangePasswordUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(currentPassword: String, newPassword: String): Result<Boolean> {
        return repository.changePassword(currentPassword, newPassword)
    }
}
