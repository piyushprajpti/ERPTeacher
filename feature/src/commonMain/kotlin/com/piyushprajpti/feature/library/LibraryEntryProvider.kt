package com.piyushprajpti.feature.library

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.library.book_detail.LibraryBookDetailScreen
import com.piyushprajpti.feature.library.dashboard.LibraryDashboardScreen
import com.piyushprajpti.feature.library.due_and_fines.LibraryDueScreen
import com.piyushprajpti.feature.library.search.LibrarySearchScreen

fun libraryEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.LibraryDashboard -> NavEntry(key) {
            LibraryDashboardScreen(
                onBackClick = { backStack.removeLast() },
                onSearchClick = { backStack.add(NavRoutes.LibrarySearch) },
                onBookClick = { backStack.add(NavRoutes.LibraryBookDetail) },
                onDueClick = { backStack.add(NavRoutes.LibraryDue) }
            )
        }

        is NavRoutes.LibrarySearch -> NavEntry(key) {
            LibrarySearchScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        is NavRoutes.LibraryBookDetail -> NavEntry(key) {
            LibraryBookDetailScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        is NavRoutes.LibraryDue -> NavEntry(key) {
            LibraryDueScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}
