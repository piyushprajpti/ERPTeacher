package com.piyushprajpti.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Alert
import com.piyushprajpti.components.message_helper.model.Toast
import com.piyushprajpti.components.message_helper.model.ToastType
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.usecase.auth.GetSavedTenantUseCase
import com.piyushprajpti.domain.usecase.auth.LoginUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getSavedTenantUseCase: GetSavedTenantUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(LoginState())
    val state = _state.onStart {
        loadTenant()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LoginState()
    )

    private fun loadTenant() {
        viewModelScope.launch {
            loadingHelper.setLoading(true)

            // Timeout logic
            val timeoutJob = launch {
                delay(5000)
                if (_state.value.tenant.id.isBlank()) {
                    loadingHelper.setLoading(false)
                    messageHelper.setAlert(
                        Alert(
                            message = "Tenant not found or taking too long to load."
                        )
                    )
                }
            }

            getSavedTenantUseCase().collectLatest { tenant ->
                if (tenant != null) {
                    _state.update { it.copy(tenant = tenant) }
                    loadingHelper.setLoading(false)
                    timeoutJob.cancel()
                }
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> {
                _state.update { it.copy(email = event.email) }
            }

            is LoginEvent.OnPasswordChange -> {
                _state.update { it.copy(password = event.password) }
            }

            is LoginEvent.OnLoginClick -> login(event.onSuccess)
        }
    }

    private fun login(onSuccess: () -> Unit) {
        val email = state.value.email
        val password = state.value.password

        if (email.isBlank() || password.isBlank()) {
            messageHelper.setToast(
                Toast(
                    type = ToastType.ERROR,
                    text = "Please enter both email and password"
                )
            )
            return
        }

        viewModelScope.launch {
            loadingHelper.setLoading(true)
            val result = loginUseCase(email, password)
            loadingHelper.setLoading(false)

            when (result) {
                is Result.Success -> {
                    onSuccess()
                }

                is Result.Error -> {
                    messageHelper.setToast(
                        Toast(
                            type = ToastType.ERROR,
                            text = result.error.message
                        )
                    )
                }
            }
        }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }
}
