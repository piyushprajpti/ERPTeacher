package com.piyushprajpti.feature.library.due_and_fines

sealed interface LibraryDueEvent {
    data object PayFine : LibraryDueEvent
    data object DownloadCertificate : LibraryDueEvent
}
