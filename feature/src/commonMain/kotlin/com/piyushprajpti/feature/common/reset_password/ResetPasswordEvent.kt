package com.piyushprajpti.feature.common.reset_password

sealed class ResetPasswordEvent {
    data class OnEmailChange(val email: String) : ResetPasswordEvent()
    data class OnOtpChange(val otp: String) : ResetPasswordEvent()
    data class OnPasswordChange(val password: String) : ResetPasswordEvent()
    data class OnConfirmPasswordChange(val confirmPassword: String) : ResetPasswordEvent()
    data class OnActionClick(val onSuccess: () -> Unit) : ResetPasswordEvent()
}
