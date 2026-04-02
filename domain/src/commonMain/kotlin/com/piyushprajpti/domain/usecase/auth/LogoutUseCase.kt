package com.piyushprajpti.domain.usecase.auth

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.repository.auth.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(): Result<Boolean> {
        return repository.logout()
    }
}