package com.piyushprajpti.feature.documents

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.documents.list.DocumentListScreen
import com.piyushprajpti.feature.documents.request.DocumentRequestScreen

fun documentEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.DocumentList -> NavEntry(key) {
            DocumentListScreen(
                onBackClick = { backStack.removeLast() },
                onRequestDocumentClick = { backStack.add(NavRoutes.DocumentRequest) }
            )
        }

        is NavRoutes.DocumentRequest -> NavEntry(key) {
            DocumentRequestScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}