package com.piyushprajpti.feature.documents.list

sealed interface DocumentListEvent {
    data class DownloadDocument(val id: String) : DocumentListEvent
    data class ShareDocument(val id: String) : DocumentListEvent
    data class RequestDocument(val id: String) : DocumentListEvent
    data object ShowScanner : DocumentListEvent
    data object AddDocument : DocumentListEvent
}
