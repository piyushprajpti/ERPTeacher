package com.piyushprajpti.domain.model.auth

data class User(
    val id: String = "",
    val tenantId: String = "",
    val email: String = "",
    val name: String = "",
    val avatar: String? = null,
    val phone: String? = null,
    val dob: String? = null,
    val gender: String? = null,
    val address: String? = null,
    val createdAt: String = "",
    val updatedAt: String = ""
)
