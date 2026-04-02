package com.piyushprajpti.feature.auth

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.auth.login.LoginScreen
import com.piyushprajpti.feature.auth.tenant.TenantScreen
import com.piyushprajpti.feature.common.support.SupportScreen

fun authEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.Tenant -> NavEntry(key) {
            TenantScreen(
                onContinueClick = { backStack.add(NavRoutes.Login) }
            )
        }

        is NavRoutes.Login -> NavEntry(key) {
            LoginScreen(
                onLoginClick = {},
                onForgotPasswordClick = { backStack.add(NavRoutes.ResetPassword) },
                onContactSupportClick = { backStack.add(NavRoutes.Support) }
            )
        }

        is NavRoutes.Support -> NavEntry(key) {
            SupportScreen(
                onBackClick = { backStack.removeLast() },
            )
        }

        else -> null
    }
}
