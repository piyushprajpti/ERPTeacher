package com.piyushprajpti.feature.settings.change_password

sealed interface ChangePasswordEvent {
    data class OnCurrentPasswordChange(val value: String) : ChangePasswordEvent
    data class OnNewPasswordChange(val value: String) : ChangePasswordEvent
    data class OnConfirmPasswordChange(val value: String) : ChangePasswordEvent
    data class OnChangePasswordClick(val onSuccess: () -> Unit) : ChangePasswordEvent
    data object OnForgotPasswordClick : ChangePasswordEvent
    data object OnBackClick : ChangePasswordEvent
}
