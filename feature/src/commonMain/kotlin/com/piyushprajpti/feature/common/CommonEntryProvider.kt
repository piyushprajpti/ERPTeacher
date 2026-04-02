package com.piyushprajpti.feature.common

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.common.reset_password.ResetPasswordScreen
import com.piyushprajpti.feature.common.support.SupportScreen

fun commonEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.ResetPassword -> NavEntry(key) {
            ResetPasswordScreen(
                onBackClick = { backStack.removeLast() },
                onSuccess = { backStack.removeLast() }
            )
        }

        is NavRoutes.Support -> NavEntry(key) {
            SupportScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}
