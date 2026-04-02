package com.piyushprajpti.domain.usecase.auth

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.model.auth.Tenant
import com.piyushprajpti.domain.repository.auth.AuthRepository

class SaveTenantUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(tenant: Tenant): Result<Boolean> {
        return repository.saveTenant(tenant)
    }
}
