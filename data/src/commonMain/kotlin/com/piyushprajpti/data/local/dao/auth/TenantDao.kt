package com.piyushprajpti.data.local.dao.auth

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.piyushprajpti.data.local.entity.auth.TenantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantDao {

    @Query("DELETE FROM tenants")
    suspend fun clearTenants()

    @Upsert
    suspend fun insertTenant(tenant: TenantEntity)

    @Transaction
    suspend fun upsertTenant(tenant: TenantEntity) {
        clearTenants()
        insertTenant(tenant)
    }

    @Query("SELECT * FROM tenants LIMIT 1")
    fun getSavedTenant(): Flow<TenantEntity?>
}
