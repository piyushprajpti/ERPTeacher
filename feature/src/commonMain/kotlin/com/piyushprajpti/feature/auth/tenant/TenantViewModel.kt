package com.piyushprajpti.feature.auth.tenant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Alert
import com.piyushprajpti.components.message_helper.model.Toast
import com.piyushprajpti.components.message_helper.model.ToastType
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.model.auth.Tenant
import com.piyushprajpti.domain.usecase.auth.GetTenantListUseCase
import com.piyushprajpti.domain.usecase.auth.SaveTenantUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TenantViewModel(
    private val getTenantListUseCase: GetTenantListUseCase,
    private val saveTenantUseCase: SaveTenantUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(TenantState())
    val state = _state.onStart {
        fetchTenantList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TenantState()
    )
    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private fun fetchTenantList() {
        viewModelScope.launch {
            loadingHelper.setLoading(true)
            when (val result = getTenantListUseCase()) {
                is Result.Success -> {
                    val tenantList = result.data.sortedBy { t -> t.name }
                    _state.update {
                        it.copy(
                            originalTenantList = tenantList,
                            filteredTenantList = tenantList
                        )
                    }
                }

                is Result.Error -> {
                    messageHelper.setAlert(
                        Alert(
                            label = "Error",
                            message = result.error.message,
                            cancelable = false,
                            neutralAction = { fetchTenantList() }
                        )
                    )
                }
            }
            loadingHelper.setLoading(false)
        }
    }

    fun onEvent(event: TenantEvent) {
        when (event) {
            is TenantEvent.OnContinueClick -> onContinueClick(event.onSuccess)
            is TenantEvent.OnTenantChange -> onTenantChange(event.data)
            is TenantEvent.OnSearchQueryChange -> onSearchQueryChange(event.query)

        }
    }

    private fun onContinueClick(onSuccess: () -> Unit) {
        val selectedTenant = state.value.selectedTenant ?: return
        viewModelScope.launch {
            setLoading(true)
            val result = saveTenantUseCase(selectedTenant)
            if (result is Result.Success) onSuccess()
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
            setLoading(false)
        }
    }

    private fun onTenantChange(data: Tenant) {
        _state.update {
            if (it.selectedTenant?.id == data.id) {
                it.copy(
                    selectedTenant = null,
                    searchQuery = ""
                )
            } else {
                it.copy(
                    selectedTenant = data,
                    searchQuery = data.name
                )
            }
        }
    }

    private fun onSearchQueryChange(query: String) {
        _state.update {
            val filtered = if (query.isBlank()) {
                it.originalTenantList
            } else {
                it.originalTenantList.filter { tenant ->
                    tenant.name.contains(query, ignoreCase = true)
                }
            }
            it.copy(
                searchQuery = query,
                filteredTenantList = filtered
            )
        }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }

    }
}
