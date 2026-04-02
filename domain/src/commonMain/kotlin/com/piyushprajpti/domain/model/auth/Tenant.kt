package com.piyushprajpti.domain.model.auth

data class Tenant(
    val id: String = "",
    val name: String = "",
    val slug: String = "",
    val domain: String? = null,
    val logo: String? = null,
    val address: String? = null,
    val contactEmail: String = "",
    val contactPhone: String? = "7014986896",
    val createdAt: String = "",
    val updatedAt: String = ""
)