package com.piyushprajpti.feature.documents.list

data class DocumentListState(
    val studentInfo: StudentInfo = StudentInfo(),
    val documents: List<Document> = emptyList()
)

data class StudentInfo(
    val name: String = "",
    val enrollment: String = "",
    val course: String = "",
    val year: String = "",
    val status: String = "ACTIVE STUDENT STATUS",
    val imageUrl: String = ""
)

data class Document(
    val id: String,
    val title: String,
    val status: DocumentStatus,
    val type: DocumentType
)

sealed interface DocumentStatus {
    data object Available : DocumentStatus
    data object RequestRequired : DocumentStatus
    data object UnderReview : DocumentStatus
}

enum class DocumentType {
    ADMISSION_LETTER,
    MARKSHEET,
    CERTIFICATE,
    FEE_RECEIPT,
    CLEARANCE
}
