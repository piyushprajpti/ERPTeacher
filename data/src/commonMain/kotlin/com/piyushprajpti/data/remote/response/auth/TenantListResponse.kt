package com.piyushprajpti.data.remote.response.auth

import com.piyushprajpti.domain.model.auth.Tenant
import kotlinx.serialization.Serializable

@Serializable
data class TenantListResponse(
    val status: String,
    val results: Int,
    val data: TenantListData
)

@Serializable
data class TenantListData(
    val tenants: List<TenantDto>
)

@Serializable
data class TenantDto(
    val id: String,
    val name: String,
    val slug: String,
    val domain: String? = null,
    val logo: String? = null,
    val address: String? = null,
    val contactEmail: String,
    val contactPhone: String? = null,
    val createdAt: String,
    val updatedAt: String
)

fun TenantDto.toDomain() = Tenant(
    id = id,
    name = name,
    slug = slug,
    domain = domain,
    logo = logo,
    address = address,
    contactEmail = contactEmail,
    contactPhone = contactPhone,
    createdAt = createdAt,
    updatedAt = updatedAt
)
