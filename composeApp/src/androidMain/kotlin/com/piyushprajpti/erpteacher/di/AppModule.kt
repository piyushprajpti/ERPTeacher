package com.piyushprajpti.erpteacher.di

import com.piyushprajpti.core.module.coreModule
import com.piyushprajpti.data.di.dataModule
import com.piyushprajpti.di.featureModule
import com.piyushprajpti.domain.di.domainModule

val appModule = listOf(
    platformModule,
    coreModule,
    dataModule,
    domainModule,
    appViewModelModule,
    featureModule
)
