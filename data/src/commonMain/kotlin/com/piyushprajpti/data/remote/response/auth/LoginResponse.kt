package com.piyushprajpti.data.remote.response.auth

import com.piyushprajpti.data.local.entity.auth.UserEntity
import com.piyushprajpti.domain.model.auth.User
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val status: String,
    val data: LoginData
)

@Serializable
data class LoginData(
    val user: UserData,
    val accessToken: String,
    val refreshToken: String
)

@Serializable
data class UserData(
    val id: String,
    val tenantId: String,
    val email: String,
    val name: String,
    val avatar: String? = null,
    val phone: String? = null,
    val dob: String? = null,
    val gender: String? = null,
    val address: String? = null,
    val createdAt: String,
    val updatedAt: String,
    val roles: List<String> = emptyList()
)

fun UserData.toEntity() = UserEntity(
    id = id,
    tenantId = tenantId,
    email = email,
    name = name,
    avatar = avatar,
    phone = phone,
    dob = dob,
    gender = gender,
    address = address,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun UserData.toDomain() = User(
    id = id,
    tenantId = tenantId,
    email = email,
    name = name,
    avatar = avatar,
    phone = phone,
    dob = dob,
    gender = gender,
    address = address,
    createdAt = createdAt,
    updatedAt = updatedAt
)
