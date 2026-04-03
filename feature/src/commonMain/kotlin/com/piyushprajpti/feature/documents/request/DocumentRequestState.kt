package com.piyushprajpti.feature.documents.request

data class DocumentRequestState(
    val selectedDocument: String = "",
    val documents: List<String> = emptyList(),
    val purpose: String = "",
    val copies: Int = 1,
    val urgency: Urgency = Urgency.STANDARD,
    val deliveryMethod: DeliveryMethod = DeliveryMethod.DIGITAL,
    val supportingDocuments: List<String> = emptyList(),
    val recentRequests: List<RecentRequest> = emptyList()
)

enum class Urgency {
    STANDARD, URGENT
}

enum class DeliveryMethod {
    DIGITAL, PHYSICAL
}

data class RecentRequest(
    val id: String,
    val title: String,
    val date: String,
    val status: RequestStatus
)

enum class RequestStatus {
    PROCESSING, DELIVERED, REJECTED
}
