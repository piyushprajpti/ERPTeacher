package com.piyushprajpti.domain.usecase.auth

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.repository.auth.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Boolean> {
        return repository.login(email, password)
    }
}
