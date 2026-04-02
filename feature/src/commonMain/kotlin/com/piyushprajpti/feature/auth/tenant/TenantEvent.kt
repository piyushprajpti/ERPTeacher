package com.piyushprajpti.feature.auth.tenant

import com.piyushprajpti.domain.model.auth.Tenant

sealed interface TenantEvent {
    data class OnTenantChange(val data: Tenant): TenantEvent
    data class OnSearchQueryChange(val query: String): TenantEvent
    data class OnContinueClick(val onSuccess: () -> Unit): TenantEvent
}