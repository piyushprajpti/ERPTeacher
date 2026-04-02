package com.piyushprajpti.domain.repository.auth

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.model.auth.Tenant
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getTenantList(): Result<List<Tenant>>
    suspend fun saveTenant(tenant: Tenant): Result<Boolean>
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun logout(): Result<Boolean>
    fun getSavedTenant(): Flow<Tenant?>
    suspend fun changePassword(currentPassword: String, newPassword: String): Result<Boolean>
}
