package com.piyushprajpti.feature.hostel

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.hostel.complaints.HostelComplaintScreen
import com.piyushprajpti.feature.hostel.dashboard.HostelDashboardScreen
import com.piyushprajpti.feature.hostel.gate_pass.HostelGatePassScreen
import com.piyushprajpti.feature.hostel.mess.HostelMessScreen

fun hostelEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.HostelDashboard -> NavEntry(key) {
            HostelDashboardScreen(
                onBackClick = { backStack.removeLast() },
                onComplaintClick = { backStack.add(NavRoutes.HostelComplaint) },
                onGuestPassClick = { backStack.add(NavRoutes.HostelGatePass) },
                onMessClick = {backStack.add(NavRoutes.HostelMess) },
            )
        }

        is NavRoutes.HostelComplaint -> NavEntry(key) {
            HostelComplaintScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        is NavRoutes.HostelGatePass -> NavEntry(key) {
            HostelGatePassScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        is NavRoutes.HostelMess -> NavEntry(key) {
            HostelMessScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}