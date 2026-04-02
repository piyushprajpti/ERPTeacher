package com.piyushprajpti.data.repository.auth

import androidx.datastore.preferences.core.stringPreferencesKey
import co.touchlab.kermit.Logger
import com.piyushprajpti.core.constants.ACCESS_TOKEN
import com.piyushprajpti.core.constants.REFRESH_TOKEN
import com.piyushprajpti.core.datastore.PreferenceDataStore
import com.piyushprajpti.core.http.ApiException
import com.piyushprajpti.core.http.ErrorCode
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.data.local.dao.auth.TenantDao
import com.piyushprajpti.data.local.dao.auth.UserDao
import com.piyushprajpti.data.local.entity.auth.toDomain
import com.piyushprajpti.data.local.entity.auth.toEntity
import com.piyushprajpti.data.remote.request.auth.ChangePasswordRequest
import com.piyushprajpti.data.remote.request.auth.LoginRequest
import com.piyushprajpti.data.remote.response.auth.toDomain
import com.piyushprajpti.data.remote.response.auth.toEntity
import com.piyushprajpti.data.remote.service.auth.AuthApiService
import com.piyushprajpti.domain.model.auth.Tenant
import com.piyushprajpti.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val tenantDao: TenantDao,
    private val userDao: UserDao,
    private val preferenceDataStore: PreferenceDataStore
) : AuthRepository {
    override suspend fun getTenantList(): Result<List<Tenant>> {
        return when (val result = apiService.getTenants()) {
            is Result.Success -> {
                val tenants = result.data.data.tenants.map { it.toDomain() }
                Result.Success(tenants)
            }

            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun saveTenant(tenant: Tenant): Result<Boolean> {
        return try {
            tenantDao.upsertTenant(tenant.toEntity())
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(
                ApiException(
                    error = ErrorCode.UNKNOWN,
                    message = e.message ?: "Failed to save tenant"
                )
            )
        }
    }

    override fun getSavedTenant(): Flow<Tenant?> {
        return tenantDao.getSavedTenant().map { it?.toDomain() }
    }

    override suspend fun login(email: String, password: String): Result<Boolean> {
        val savedTenant = getSavedTenant().first()
            ?: return Result.Error(
                ApiException(
                    error = ErrorCode.NOT_FOUND,
                    message = "No tenant selected"
                )
            )

        val loginRequest = LoginRequest(
            email = email,
            password = password,
            tenantId = savedTenant.id
        )

        return when (val result = apiService.login(loginRequest)) {
            is Result.Success -> {
                try {
                    userDao.clearUser()

                    preferenceDataStore.setValue(
                        stringPreferencesKey(ACCESS_TOKEN),
                        result.data.data.accessToken
                    )
                    preferenceDataStore.setValue(
                        stringPreferencesKey(REFRESH_TOKEN),
                        result.data.data.refreshToken
                    )

                    Logger.e("TAG") { result.data.toString() }
                    userDao.insertUser(result.data.data.user.toEntity())

                    Result.Success(true)
                } catch (e: Exception) {
                    Result.Error(
                        ApiException(
                            error = ErrorCode.UNKNOWN,
                            message = e.message
                                ?: "Login successful, but failed to save user data locally."
                        )
                    )
                }
            }

            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun logout(): Result<Boolean> {
        return when (val result = apiService.logout()) {
            is Result.Success -> {
                try {
                    preferenceDataStore.clearAll()
                    userDao.clearUser()
                    tenantDao.clearTenants()
                    Result.Success(true)
                } catch (e: Exception) {
                    Result.Error(
                        ApiException(
                            error = ErrorCode.UNKNOWN,
                            message = e.message
                                ?: "Logout successful, but failed to clear local data."
                        )
                    )
                }
            }

            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun changePassword(
        currentPassword: String,
        newPassword: String
    ): Result<Boolean> {
        val user = userDao.getUser().first()
            ?: return Result.Error(
                ApiException(
                    error = ErrorCode.USER_NOT_FOUND,
                    message = "User not found. Please log in again."
                )
            )

        val request = ChangePasswordRequest(
            userId = user.id,
            currentPassword = currentPassword,
            newPassword = newPassword
        )

        return when (val result = apiService.changePassword(request)) {
            is Result.Success -> Result.Success(true)
            is Result.Error -> Result.Error(result.error)
        }
    }
}
