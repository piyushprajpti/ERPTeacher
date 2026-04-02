package com.piyushprajpti.feature.result

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes

fun resultEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.Result -> NavEntry(key) {
            ResultScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}