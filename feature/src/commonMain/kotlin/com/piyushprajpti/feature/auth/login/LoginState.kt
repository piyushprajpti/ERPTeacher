package com.piyushprajpti.feature.auth.login

import com.piyushprajpti.domain.model.auth.Tenant

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val tenant: Tenant = Tenant()
)
