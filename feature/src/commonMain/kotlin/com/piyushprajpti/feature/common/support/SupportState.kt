package com.piyushprajpti.feature.common.support

data class SupportState(
    val name: String = "",
    val email: String = "",
    val subject: String = "",
    val description: String = "",
    val isLoading: Boolean = false
)
