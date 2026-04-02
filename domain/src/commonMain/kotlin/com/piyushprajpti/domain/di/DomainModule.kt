package com.piyushprajpti.domain.di

import com.piyushprajpti.domain.usecase.auth.ChangePasswordUseCase
import com.piyushprajpti.domain.usecase.auth.GetSavedTenantUseCase
import com.piyushprajpti.domain.usecase.auth.GetTenantListUseCase
import com.piyushprajpti.domain.usecase.auth.LoginUseCase
import com.piyushprajpti.domain.usecase.auth.LogoutUseCase
import com.piyushprajpti.domain.usecase.auth.SaveTenantUseCase
import com.piyushprajpti.domain.usecase.common.ForgotPasswordUseCase
import com.piyushprajpti.domain.usecase.common.ResetPasswordUseCase
import com.piyushprajpti.domain.usecase.common.SubmitSupportUseCase
import com.piyushprajpti.domain.usecase.common.VerifyOtpUseCase
import org.koin.dsl.module

val domainModule = module {
    // auth use cases
    factory { GetTenantListUseCase(get()) }
    factory { SaveTenantUseCase(get()) }
    factory { GetSavedTenantUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { ForgotPasswordUseCase(get()) }
    factory { VerifyOtpUseCase(get()) }
    factory { ResetPasswordUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { ChangePasswordUseCase(get()) }

    // common use cases
    factory { SubmitSupportUseCase(get()) }
}
