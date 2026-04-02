package com.piyushprajpti.core.http

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val error: ApiException) : Result<Nothing>

}

data class ApiException(
    val error: ErrorCode,
    override val message: String,
) : Exception(message)
