package com.piyushprajpti.feature.common.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Alert
import com.piyushprajpti.components.message_helper.model.Toast
import com.piyushprajpti.components.message_helper.model.ToastType
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.usecase.auth.GetSavedTenantUseCase
import com.piyushprajpti.domain.usecase.common.ForgotPasswordUseCase
import com.piyushprajpti.domain.usecase.common.ResetPasswordUseCase
import com.piyushprajpti.domain.usecase.common.VerifyOtpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val getSavedTenantUseCase: GetSavedTenantUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(ResetPasswordState())
    val state = _state.onStart {
        loadTenant()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ResetPasswordState()
    )

    private fun loadTenant() {
        viewModelScope.launch {
            getSavedTenantUseCase().collectLatest { tenant ->
                if (tenant != null) {
                    _state.update { it.copy(tenant = tenant) }
                }
            }
        }
    }

    fun onEvent(event: ResetPasswordEvent) {
        when (event) {
            is ResetPasswordEvent.OnEmailChange -> {
                _state.update { it.copy(email = event.email) }
            }

            is ResetPasswordEvent.OnOtpChange -> {
                _state.update { it.copy(otp = event.otp) }
            }

            is ResetPasswordEvent.OnPasswordChange -> {
                _state.update { it.copy(password = event.password) }
            }

            is ResetPasswordEvent.OnConfirmPasswordChange -> {
                _state.update { it.copy(confirmPassword = event.confirmPassword) }
            }

            is ResetPasswordEvent.OnActionClick -> {
                if (_state.value.resetToken.isNotBlank()) {
                    resetPassword(event.onSuccess)
                } else if (_state.value.isOtpSent) {
                    verifyOtp()
                } else {
                    forgotPassword()
                }
            }
        }
    }

    private fun forgotPassword() {
        val email = _state.value.email
        val tenantId = _state.value.tenant.id

        if (email.isBlank()) {
            messageHelper.setToast(Toast(type = ToastType.ERROR, text = "Please enter your email"))
            return
        }

        viewModelScope.launch {
            loadingHelper.setLoading(true)
            val result = forgotPasswordUseCase(email, tenantId)
            loadingHelper.setLoading(false)

            when (result) {
                is Result.Success -> {
                    _state.update { it.copy(isOtpSent = true) }
                    messageHelper.setToast(Toast(type = ToastType.SUCCESS, text = "OTP sent successfully to your email"))
                }

                is Result.Error -> {
                    messageHelper.setToast(Toast(type = ToastType.ERROR, text = result.error.message))
                }
            }
        }
    }

    private fun verifyOtp() {
        val email = _state.value.email
        val tenantId = _state.value.tenant.id
        val otp = _state.value.otp

        if (otp.length != 6) {
            messageHelper.setToast(Toast(type = ToastType.ERROR, text = "Please enter 6-digit OTP"))
            return
        }

        viewModelScope.launch {
            loadingHelper.setLoading(true)
            val result = verifyOtpUseCase(email, tenantId, otp)
            loadingHelper.setLoading(false)

            when (result) {
                is Result.Success -> {
                    _state.update { it.copy(resetToken = result.data) }
                }

                is Result.Error -> {
                    messageHelper.setToast(Toast(type = ToastType.ERROR, text = result.error.message))
                }
            }
        }
    }

    private fun resetPassword(onSuccess: () -> Unit) {
        val resetToken = _state.value.resetToken
        val password = _state.value.password
        val confirmPassword = _state.value.confirmPassword

        if (password.isBlank()) {
            messageHelper.setToast(Toast(type = ToastType.ERROR, text = "Please enter password"))
            return
        }

        if (password != confirmPassword) {
            messageHelper.setToast(Toast(type = ToastType.ERROR, text = "Passwords do not match"))
            return
        }

        viewModelScope.launch {
            loadingHelper.setLoading(true)
            val result = resetPasswordUseCase(resetToken, password)
            loadingHelper.setLoading(false)

            when (result) {
                is Result.Success -> {
                    messageHelper.setAlert(
                        Alert(
                            label = "Success",
                            message = "Password reset successfully",
                            cancelable = false,
                            neutralAction = onSuccess
                        )
                    )
                }

                is Result.Error -> {
                    messageHelper.setToast(Toast(type = ToastType.ERROR, text = result.error.message))
                }
            }
        }
    }
}
