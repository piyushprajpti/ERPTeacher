package com.piyushprajpti.feature.auth.tenant

import com.piyushprajpti.domain.model.auth.Tenant

data class TenantState(
    val isLoading: Boolean = false,
    val originalTenantList: List<Tenant> = emptyList(),
    val filteredTenantList: List<Tenant> = emptyList(),
    val selectedTenant: Tenant? = null,
    val error: String? = null,
    val searchQuery: String = ""
)