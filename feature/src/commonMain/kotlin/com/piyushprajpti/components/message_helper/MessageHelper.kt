package com.piyushprajpti.components.message_helper

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.piyushprajpti.components.message_helper.model.Alert
import com.piyushprajpti.components.message_helper.model.Toast
import com.piyushprajpti.components.message_helper.model.ToastType

class MessageHelper {

    private var _state = mutableStateOf(MessageState())
    val state: State<MessageState> = _state

    fun setAlert(alert: Alert?) {
        _state.value = state.value.copy(
            alert = alert
        )
    }

    fun setToast(toast: Toast?) {
        _state.value = state.value.copy(
            toast = toast
        )
    }

    fun showMessage(message: String) {
        setToast(Toast(type = ToastType.SUCCESS, text = message))
    }

    fun showError(message: String) {
        setToast(Toast(type = ToastType.ERROR, text = message))
    }
}
