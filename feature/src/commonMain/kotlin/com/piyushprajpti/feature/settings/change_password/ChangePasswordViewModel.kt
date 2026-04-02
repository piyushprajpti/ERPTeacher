package com.piyushprajpti.feature.settings.change_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.usecase.auth.ChangePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(ChangePasswordState())
    val state = _state.onStart {
        // load initial data here
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ChangePasswordState()
    )

    fun onEvent(event: ChangePasswordEvent) {
        when (event) {
            is ChangePasswordEvent.OnCurrentPasswordChange -> {
                _state.update { it.copy(currentPassword = event.value) }
            }

            is ChangePasswordEvent.OnNewPasswordChange -> {
                _state.update { it.copy(newPassword = event.value) }
            }

            is ChangePasswordEvent.OnConfirmPasswordChange -> {
                _state.update { it.copy(confirmPassword = event.value) }
            }

            is ChangePasswordEvent.OnChangePasswordClick -> {
                changePassword(event.onSuccess)
            }

            else -> {}
        }
    }

    private fun changePassword(onSuccess: () -> Unit) {
        val currentState = _state.value

        if (currentState.currentPassword.isBlank()) {
            messageHelper.showError("Current password is required")
            return
        }

        if (currentState.newPassword.isBlank()) {
            messageHelper.showError("New password is required")
            return
        }

        if (currentState.newPassword != currentState.confirmPassword) {
            messageHelper.showError("Passwords do not match")
            return
        }

        viewModelScope.launch {
            setLoading(true)
            when (val result = changePasswordUseCase(
                currentPassword = currentState.currentPassword,
                newPassword = currentState.newPassword
            )) {
                is Result.Success -> {
                    messageHelper.showMessage("Password changed successfully")
                    _state.update {
                        it.copy(
                            currentPassword = "",
                            newPassword = "",
                            confirmPassword = ""
                        )
                    }
                }

                is Result.Error -> {
                    messageHelper.showError(result.error.message)
                }
            }
            setLoading(false)
        }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
        if (value) loadingHelper.showLoading() else loadingHelper.hideLoading()
    }
}
