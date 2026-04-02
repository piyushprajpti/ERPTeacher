package com.piyushprajpti.data.remote.service.auth

import com.piyushprajpti.core.constants.ApiConstants
import com.piyushprajpti.core.http.ApiResponseHelper
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.data.remote.request.auth.ChangePasswordRequest
import com.piyushprajpti.data.remote.request.auth.LoginRequest
import com.piyushprajpti.data.remote.response.auth.LoginResponse
import com.piyushprajpti.data.remote.response.auth.TenantListResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthApiService(private val httpClient: HttpClient) {
    suspend fun getTenants(): Result<TenantListResponse> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.get(ApiConstants.Auth.TENANTS)
        }
    }

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.post(ApiConstants.Auth.LOGIN) {
                setBody(loginRequest)
            }
        }
    }

    suspend fun logout(): Result<Unit> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.post(ApiConstants.Auth.LOGOUT)
        }
    }

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): Result<Unit> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.post(ApiConstants.Auth.CHANGE_PASSWORD) {
                setBody(changePasswordRequest)
            }
        }
    }
}
