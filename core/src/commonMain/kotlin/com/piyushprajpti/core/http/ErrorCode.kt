package com.piyushprajpti.core.http

enum class ErrorCode(val code: String) {
    UNKNOWN("unknown"),
    NO_TRANSFORMATION_OR_API_NOT_FOUND("nt-nf"),
    AUTH_ERROR("authError"),
    MISSING_PAYLOAD_ERROR("missingPayloadError"),
    BAD_PAYLOAD_ERROR("badPayloadError"),
    NOT_FOUND("notFoundError"),
    RECORD_NOT_FOUND_IN_DATABASE("db-0007"),
    DEVICE_LIMIT_REACHED("login-limit"),
    USER_NOT_FOUND("user-not-found"),
    NO_INTERNET("no_internet");

    companion object {
        fun fromValue(value: String): ErrorCode =
            entries.firstOrNull { it.code == value } ?: UNKNOWN
    }
}