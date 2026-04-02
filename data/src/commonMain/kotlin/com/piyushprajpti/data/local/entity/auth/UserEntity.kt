package com.piyushprajpti.data.local.entity.auth

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piyushprajpti.domain.model.auth.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val tenantId: String,
    val email: String,
    val name: String,
    val avatar: String?,
    val phone: String?,
    val dob: String?,
    val gender: String?,
    val address: String?,
    val createdAt: String,
    val updatedAt: String
)

fun UserEntity.toDomain() = User(
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

fun User.toEntity() = UserEntity(
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
