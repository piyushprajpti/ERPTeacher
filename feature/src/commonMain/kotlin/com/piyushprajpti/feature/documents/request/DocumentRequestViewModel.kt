package com.piyushprajpti.feature.documents.request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DocumentRequestViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(DocumentRequestState())
    val state = _state.onStart {
        loadInitialData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DocumentRequestState()
    )

    private fun loadInitialData() {
        _state.update {
            it.copy(
                documents = listOf(
                    "Bonafide Certificate",
                    "Marksheet (Sem 1)",
                    "Marksheet (Sem 2)",
                    "Transfer Certificate",
                    "Character Certificate",
                    "Fee Receipt"
                ),
                recentRequests = listOf(
                    RecentRequest(
                        id = "1",
                        title = "Bonafide Certificate",
                        date = "Oct 10, 2023",
                        status = RequestStatus.PROCESSING
                    ),
                    RecentRequest(
                        id = "2",
                        title = "Sem 2 Marksheet",
                        date = "Aug 15, 2023",
                        status = RequestStatus.DELIVERED
                    ),
                    RecentRequest(
                        id = "3",
                        title = "Transfer Certificate",
                        date = "Jul 20, 2023",
                        status = RequestStatus.REJECTED
                    )
                )
            )
        }
    }

    fun onEvent(event: DocumentRequestEvent) {
        when (event) {
            is DocumentRequestEvent.OnDocumentSelect -> {
                _state.update { it.copy(selectedDocument = event.document) }
            }
            is DocumentRequestEvent.OnPurposeChange -> {
                _state.update { it.copy(purpose = event.purpose) }
            }
            DocumentRequestEvent.OnIncrementCopies -> {
                _state.update { it.copy(copies = it.copies + 1) }
            }
            DocumentRequestEvent.OnDecrementCopies -> {
                if (_state.value.copies > 1) {
                    _state.update { it.copy(copies = it.copies - 1) }
                }
            }
            is DocumentRequestEvent.OnUrgencyChange -> {
                _state.update { it.copy(urgency = event.urgency) }
            }
            is DocumentRequestEvent.OnDeliveryMethodChange -> {
                _state.update { it.copy(deliveryMethod = event.method) }
            }
            DocumentRequestEvent.OnUploadClick -> {
                // Handle upload logic
                messageHelper.showMessage("Upload feature coming soon")
            }
            DocumentRequestEvent.OnSubmitRequest -> {
                if (validateRequest()) {
                    messageHelper.showMessage("Request submitted successfully")
                }
            }
            DocumentRequestEvent.OnViewAllRequests -> {
                // Navigate to all requests
            }
            is DocumentRequestEvent.OnRecentRequestClick -> {
                // Navigate to request details
            }
        }
    }

    private fun validateRequest(): Boolean {
        val currentState = _state.value
        if (currentState.selectedDocument.isEmpty()) {
            messageHelper.showError("Please select a document type")
            return false
        }
        if (currentState.purpose.isEmpty()) {
            messageHelper.showError("Please enter the purpose of request")
            return false
        }
        return true
    }
}
