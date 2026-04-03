package com.piyushprajpti.feature.documents.request

sealed interface DocumentRequestEvent {
    data class OnDocumentSelect(val document: String) : DocumentRequestEvent
    data class OnPurposeChange(val purpose: String) : DocumentRequestEvent
    data object OnIncrementCopies : DocumentRequestEvent
    data object OnDecrementCopies : DocumentRequestEvent
    data class OnUrgencyChange(val urgency: Urgency) : DocumentRequestEvent
    data class OnDeliveryMethodChange(val method: DeliveryMethod) : DocumentRequestEvent
    data object OnUploadClick : DocumentRequestEvent
    data object OnSubmitRequest : DocumentRequestEvent
    data object OnViewAllRequests : DocumentRequestEvent
    data class OnRecentRequestClick(val id: String) : DocumentRequestEvent
}
