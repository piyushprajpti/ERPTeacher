package com.piyushprajpti.feature.common.support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Alert
import com.piyushprajpti.components.message_helper.model.Toast
import com.piyushprajpti.components.message_helper.model.ToastType
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.usecase.common.SubmitSupportUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SupportViewModel(
    private val submitSupportUseCase: SubmitSupportUseCase
) : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(SupportState())
    val state = _state.asStateFlow()

    fun onEvent(event: SupportEvent) {
        when (event) {
            is SupportEvent.OnNameChange -> _state.update { it.copy(name = event.name) }
            is SupportEvent.OnEmailChange -> _state.update { it.copy(email = event.email) }
            is SupportEvent.OnSubjectChange -> _state.update { it.copy(subject = event.subject) }
            is SupportEvent.OnDescriptionChange -> _state.update { it.copy(description = event.description) }
            is SupportEvent.OnSubmitClick -> submitSupport()
        }
    }

    private fun submitSupport() {
        val name = _state.value.name
        val email = _state.value.email
        val subject = _state.value.subject
        val description = _state.value.description

        if (name.isBlank() || email.isBlank() || subject.isBlank() || description.isBlank()) {
            messageHelper.setToast(Toast(type = ToastType.ERROR, text = "Please fill all fields"))
            return
        }

        viewModelScope.launch {
            loadingHelper.setLoading(true)
            val result = submitSupportUseCase(name, email, subject, description)
            loadingHelper.setLoading(false)

            when (result) {
                is Result.Success -> {
                    messageHelper.setAlert(
                        Alert(
                            label = "Success",
                            message = "Support request submitted successfully. We will get back to you soon.",
                            cancelable = true
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
