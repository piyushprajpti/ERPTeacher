package com.piyushprajpti.data.di

import com.piyushprajpti.data.local.db.LocalDatabase
import com.piyushprajpti.data.remote.service.auth.AuthApiService
import com.piyushprajpti.data.remote.service.common.CommonApiService
import com.piyushprajpti.data.repository.auth.AuthRepositoryImpl
import com.piyushprajpti.data.repository.common.CommonRepositoryImpl
import com.piyushprajpti.domain.repository.auth.AuthRepository
import com.piyushprajpti.domain.repository.common.CommonRepository
import org.koin.dsl.module

val dataModule = module {
    single { AuthApiService(get()) }
    single { CommonApiService(get()) }
    single { get<LocalDatabase>().tenantDao() }
    single { get<LocalDatabase>().userDao() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get(), get()) }
    single<CommonRepository> { CommonRepositoryImpl(get()) }
}
