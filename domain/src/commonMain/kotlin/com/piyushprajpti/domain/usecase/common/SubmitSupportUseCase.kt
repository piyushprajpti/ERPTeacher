package com.piyushprajpti.domain.usecase.common

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.repository.common.CommonRepository

class SubmitSupportUseCase(private val repository: CommonRepository) {
    suspend operator fun invoke(name: String, email: String, subject: String, description: String): Result<Unit> {
        return repository.submitSupport(name, email, subject, description)
    }
}
