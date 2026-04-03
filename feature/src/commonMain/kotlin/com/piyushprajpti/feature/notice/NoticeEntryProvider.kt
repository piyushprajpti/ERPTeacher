package com.piyushprajpti.feature.notice

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.notice.create.NoticeCreateScreen

fun noticeEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.NoticeCreate -> NavEntry(key) {
            NoticeCreateScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}