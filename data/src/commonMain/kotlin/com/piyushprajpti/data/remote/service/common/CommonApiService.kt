package com.piyushprajpti.data.remote.service.common

import com.piyushprajpti.core.constants.ApiConstants
import com.piyushprajpti.core.http.ApiResponseHelper
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.data.remote.request.common.ForgotPasswordRequest
import com.piyushprajpti.data.remote.request.common.ResetPasswordRequest
import com.piyushprajpti.data.remote.request.common.SupportRequest
import com.piyushprajpti.data.remote.request.common.VerifyOtpRequest
import com.piyushprajpti.data.remote.response.VerifyOtpResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class CommonApiService(private val httpClient: HttpClient) {
    suspend fun submitSupport(request: SupportRequest): Result<Unit> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.post(ApiConstants.Common.SUPPORT) {
                setBody(request)
            }
        }
    }

    suspend fun forgotPassword(request: ForgotPasswordRequest): Result<Unit> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.post(ApiConstants.Common.FORGOT_PASSWORD) {
                setBody(request)
            }
        }
    }

    suspend fun verifyOtp(request: VerifyOtpRequest): Result<VerifyOtpResponse> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.post(ApiConstants.Common.VERIFY_OTP) {
                setBody(request)
            }
        }
    }

    suspend fun resetPassword(request: ResetPasswordRequest): Result<Unit> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.post(ApiConstants.Common.RESET_PASSWORD) {
                setBody(request)
            }
        }
    }
}
