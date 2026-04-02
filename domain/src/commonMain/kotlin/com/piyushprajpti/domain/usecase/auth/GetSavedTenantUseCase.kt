package com.piyushprajpti.domain.usecase.auth

import com.piyushprajpti.domain.model.auth.Tenant
import com.piyushprajpti.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetSavedTenantUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Flow<Tenant?> {
        return repository.getSavedTenant()
    }
}
