package com.piyushprajpti.erpteacher

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.piyushprajpti.components.FloatingBottomBar
import com.piyushprajpti.components.LocalBottomBarPadding
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.core.navigation.serializableNavModule
import com.piyushprajpti.feature.assignment.assignmentEntryProvider
import com.piyushprajpti.feature.attendance.attendanceEntryProvider
import com.piyushprajpti.feature.auth.authEntryProvider
import com.piyushprajpti.feature.common.commonEntryProvider
import com.piyushprajpti.feature.home.homeEntryProvider
import com.piyushprajpti.feature.schedule.scheduleEntryProvider
import com.piyushprajpti.feature.settings.settingsEntryProvider

@Composable
fun Navigation(authState: AuthState) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = serializableNavModule
        },
        NavRoutes.Splash
    )

    val currentRoute by remember { derivedStateOf { backStack.lastOrNull() } }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                val last = backStack.lastOrNull()
                if (last == NavRoutes.Splash || last == NavRoutes.Login || last == NavRoutes.Tenant) {
                    backStack.clear()
                    backStack.add(NavRoutes.Home)
                }
            }

            is AuthState.Unauthenticated -> {
                backStack.clear()
                backStack.add(NavRoutes.Tenant)
            }

            AuthState.Loading -> {}
        }
    }

    val providers = remember {
        listOf(
            ::authEntryProvider,
            ::commonEntryProvider,
            ::settingsEntryProvider,
            ::homeEntryProvider,
            ::attendanceEntryProvider,
            ::scheduleEntryProvider,
            ::assignmentEntryProvider,

            )
    }

    val bottomBarRoutes = remember {
        setOf(NavRoutes.Home, NavRoutes.AttendanceDashboard, NavRoutes.Schedule, NavRoutes.Settings)
    }
    val showBottomBar = currentRoute in bottomBarRoutes

    val saveableDecorator = rememberSaveableStateHolderNavEntryDecorator<NavKey>()
    val viewModelDecorator = rememberViewModelStoreNavEntryDecorator<NavKey>()
    val entryDecorators = remember(saveableDecorator, viewModelDecorator) {
        listOf(saveableDecorator, viewModelDecorator)
    }

    val savedTabStacks = remember { mutableMapOf<NavKey, List<NavKey>>() }
    var bottomBarHeight by remember { mutableStateOf(0.dp) }

    Box(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(
            LocalBottomBarPadding provides if (showBottomBar) bottomBarHeight else 0.dp
        ) {
            NavDisplay(
                backStack = backStack,
                entryDecorators = entryDecorators,
                entryProvider = { key ->
                    when (key) {
                        is NavRoutes.Splash -> NavEntry(key) {
                            SplashScreen()
                        }

                        else -> {
                            providers.firstNotNullOfOrNull { provider ->
                                provider(key, backStack)
                            } ?: error("Unknown route: $key")
                        }
                    }
                }
            )
        }

        val currentNavRoute = currentRoute as? NavRoutes
        if (showBottomBar && currentNavRoute != null) {
            FloatingBottomBar(
                currentRoute = currentNavRoute,
                onHeightChanged = { bottomBarHeight = it },
                onTabClick = { tab ->
                    if (currentRoute != tab.route) {
                        val tabRootIndex = backStack.indexOfFirst { it in bottomBarRoutes }
                        if (tabRootIndex >= 0) {
                            val currentTabRoot = backStack[tabRootIndex]
                            savedTabStacks[currentTabRoot] = backStack.drop(tabRootIndex).toList()
                            while (backStack.size > tabRootIndex) {
                                backStack.removeAt(backStack.lastIndex)
                            }
                        }
                        val restoredStack = savedTabStacks[tab.route] ?: listOf(tab.route)
                        restoredStack.forEach { backStack.add(it) }
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
