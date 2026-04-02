package com.piyushprajpti.components.message_helper.model

enum class ToastType {
    SUCCESS, WARNING, ERROR, INFO
}

data class Toast(
    val type: ToastType = ToastType.INFO,
    val text: String = ""
)
