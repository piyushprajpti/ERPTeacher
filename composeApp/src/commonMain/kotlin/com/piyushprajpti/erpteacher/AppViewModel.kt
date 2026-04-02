package com.piyushprajpti.erpteacher

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.core.constants.ACCESS_TOKEN
import com.piyushprajpti.core.constants.APP_THEME
import com.piyushprajpti.core.constants.REFRESH_TOKEN
import com.piyushprajpti.core.datastore.PreferenceDataStore
import com.piyushprajpti.feature.settings.root.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class AppViewModel(
    private val preferenceDataStore: PreferenceDataStore
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _theme = MutableStateFlow(AppTheme.DEVICE_DEFAULT)
    val theme: StateFlow<AppTheme> = _theme.asStateFlow()

    init {
        checkAuth()
        observeTheme()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            combine(
                preferenceDataStore.getValue(stringPreferencesKey(ACCESS_TOKEN)),
                preferenceDataStore.getValue(stringPreferencesKey(REFRESH_TOKEN))
            ) { accessToken, refreshToken ->
                accessToken to refreshToken
            }.collect { (accessToken, refreshToken) ->
                if (accessToken == null || refreshToken == null) {
                    _authState.value = AuthState.Unauthenticated
                } else {
                    _authState.value = AuthState.Authenticated
                }
            }
        }
    }

    private fun observeTheme() {
        viewModelScope.launch {
            preferenceDataStore.getValue(stringPreferencesKey(APP_THEME))
                .collect { themeLabel ->
                    _theme.value = AppTheme.entries.find { it.label == themeLabel }
                        ?: AppTheme.DEVICE_DEFAULT
                }
        }
    }
}

sealed interface AuthState {
    data object Loading : AuthState
    data object Authenticated : AuthState
    data object Unauthenticated : AuthState
}
