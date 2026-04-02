package com.piyushprajpti.feature.common.reset_password

import com.piyushprajpti.domain.model.auth.Tenant

data class ResetPasswordState(
    val email: String = "",
    val otp: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isOtpSent: Boolean = false,
    val resetToken: String = "",
    val tenant: Tenant = Tenant()
)
