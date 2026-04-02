package com.piyushprajpti.components.message_helper.model

data class Alert(
    val label: String = "Alert",
    val message: String = "Something went wrong. Please try again.",
    var cancelable: Boolean = true,
    var requireAction: Boolean = false,
    var positiveAction: () -> Unit = {},
    var negativeAction: () -> Unit = {},
    var neutralAction: () -> Unit = {}
)
