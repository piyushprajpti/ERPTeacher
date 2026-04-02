package com.piyushprajpti.feature.my_leave

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.leave.leave_apply.MyLeaveApplyScreen
import com.piyushprajpti.feature.my_leave.balance.MyLeaveBalanceScreen

fun myLeaveEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.MyLeaveBalance -> NavEntry(key) {
            MyLeaveBalanceScreen(
                onBackClick = { backStack.removeLast() },
                onApplyClick = { backStack.add(NavRoutes.MyLeaveApply) }
            )
        }

        is NavRoutes.MyLeaveApply -> NavEntry(key) {
            MyLeaveApplyScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}