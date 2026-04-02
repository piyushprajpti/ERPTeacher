package com.piyushprajpti.erpteacher.di

import com.piyushprajpti.erpteacher.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appViewModelModule = module {
    viewModelOf(::AppViewModel)
}
