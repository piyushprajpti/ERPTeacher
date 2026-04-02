package com.piyushprajpti.feature.settings.root

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Alert
import com.piyushprajpti.core.constants.APP_THEME
import com.piyushprajpti.core.datastore.PreferenceDataStore
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.core.utils.getAppVersion
import com.piyushprajpti.domain.usecase.auth.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val preferenceDataStore: PreferenceDataStore
) : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.onStart {
        loadInitialData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SettingsState()
    )

    private fun loadInitialData() {
        viewModelScope.launch {
            preferenceDataStore.getValue(stringPreferencesKey(APP_THEME))
                .collect { themeLabel ->
                    val theme = AppTheme.entries.find { it.label == themeLabel }
                        ?: AppTheme.DEVICE_DEFAULT

                    _state.update {
                        it.copy(
                            currentTheme = theme,
                            appVersion = getAppVersion()
                        )
                    }
                }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangeTheme -> onThemeChange(event.theme)

            is SettingsEvent.ToggleAllNotifications -> {
                _state.update { it.copy(allNotifications = event.enabled) }
            }

            is SettingsEvent.ToggleAttendanceAlerts -> {
                _state.update { it.copy(attendanceAlerts = event.enabled) }
            }

            is SettingsEvent.ToggleClassAlerts -> {
                _state.update { it.copy(classAlerts = event.enabled) }
            }

            is SettingsEvent.ToggleAnnouncements -> {
                _state.update { it.copy(announcements = event.enabled) }
            }

            SettingsEvent.OnLogoutClick -> {
                messageHelper.setAlert(
                    Alert(
                        label = "Logout",
                        message = "Are you sure want to logout?",
                        requireAction = true,
                        positiveAction = { logout() }
                    )
                )
            }
        }
    }

    private fun onThemeChange(theme: AppTheme) {
        viewModelScope.launch {
            preferenceDataStore.setValue(stringPreferencesKey(APP_THEME), theme.label)
            _state.update { it.copy(currentTheme = theme) }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            loadingHelper.showLoading()
            when (val result = logoutUseCase.invoke()) {
                is Result.Success -> {
                    messageHelper.showMessage("Logged out successfully")
                }

                is Result.Error -> {
                    messageHelper.showError(result.error.message)
                }
            }
            loadingHelper.hideLoading()
        }
    }
}
