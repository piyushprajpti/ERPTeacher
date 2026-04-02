package com.piyushprajpti.feature.settings

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.common.support.SupportScreen
import com.piyushprajpti.feature.settings.change_password.ChangePasswordScreen
import com.piyushprajpti.feature.settings.profile.ProfileScreen
import com.piyushprajpti.feature.settings.root.SettingsScreen

fun settingsEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.Settings -> NavEntry(key) {
            SettingsScreen(
                onProfileClick = { backStack.add(NavRoutes.Profile) },
                onChangePasswordClick = { backStack.add(NavRoutes.ChangePassword) },
                onSupportClick = { backStack.add(NavRoutes.Support) }
            )
        }

        is NavRoutes.Profile -> NavEntry(key) {
            ProfileScreen(
                onBackClick = { backStack.removeLast() },
            )
        }

        is NavRoutes.ChangePassword -> NavEntry(key) {
            ChangePasswordScreen(
                onBackClick = { backStack.removeLast() },
                onForgotPasswordClick = { backStack.add(NavRoutes.ResetPassword) },
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