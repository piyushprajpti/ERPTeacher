package com.piyushprajpti.components.loading_helper

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class LoadingHelper {
    private var _state = mutableStateOf(false)
    val state: State<Boolean> = _state

    fun showLoading() {
        _state.value = true
    }

    fun hideLoading() {
        _state.value = false
    }

    fun setLoading(loading: Boolean) {
        _state.value = loading
    }
}