package com.piyushprajpti.data.local.entity.auth

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piyushprajpti.domain.model.auth.Tenant

@Entity(tableName = "tenants")
data class TenantEntity(
    @PrimaryKey val id: String,
    val name: String,
    val slug: String,
    val domain: String?,
    val logo: String?,
    val address: String?,
    val contactEmail: String,
    val contactPhone: String?,
    val createdAt: String,
    val updatedAt: String,
    val isSelected: Boolean = false
)

fun TenantEntity.toDomain() = Tenant(
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

fun Tenant.toEntity() = TenantEntity(
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
