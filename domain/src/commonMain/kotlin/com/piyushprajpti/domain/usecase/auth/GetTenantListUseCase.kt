package com.piyushprajpti.domain.usecase.auth

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.model.auth.Tenant
import com.piyushprajpti.domain.repository.auth.AuthRepository

class GetTenantListUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(): Result<List<Tenant>> = repository.getTenantList()
}
