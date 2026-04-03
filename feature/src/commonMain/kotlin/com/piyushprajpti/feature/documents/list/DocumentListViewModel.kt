package com.piyushprajpti.feature.documents.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DocumentListViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(DocumentListState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DocumentListState()
    )

    private fun loadData() {
        _state.update {
            it.copy(
                studentInfo = StudentInfo(
                    name = "Arjun Mehta",
                    enrollment = "2024-CS-042",
                    course = "B.Tech CS",
                    year = "Year 2",
                    status = "ACTIVE STUDENT STATUS",
                    imageUrl = "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-download-dummy-man.png"
                ),
                documents = listOf(
                    Document(
                        id = "1",
                        title = "Admission Letter",
                        status = DocumentStatus.Available,
                        type = DocumentType.ADMISSION_LETTER
                    ),
                    Document(
                        id = "2",
                        title = "Semester 3 Marksheet",
                        status = DocumentStatus.Available,
                        type = DocumentType.MARKSHEET
                    ),
                    Document(
                        id = "3",
                        title = "Bonafide Certificate",
                        status = DocumentStatus.RequestRequired,
                        type = DocumentType.CERTIFICATE
                    ),
                    Document(
                        id = "4",
                        title = "Fee Receipt - Sem 4",
                        status = DocumentStatus.Available,
                        type = DocumentType.FEE_RECEIPT
                    ),
                    Document(
                        id = "5",
                        title = "Library Clearance",
                        status = DocumentStatus.UnderReview,
                        type = DocumentType.CLEARANCE
                    ),
                    Document(
                        id = "6",
                        title = "Hostel Allotment",
                        status = DocumentStatus.Available,
                        type = DocumentType.CERTIFICATE
                    )
                )
            )
        }
    }

    fun onEvent(event: DocumentListEvent) {
        when (event) {
            is DocumentListEvent.DownloadDocument -> {
                messageHelper.showMessage("Downloading document...")
            }
            is DocumentListEvent.ShareDocument -> {
                messageHelper.showMessage("Sharing document...")
            }
            is DocumentListEvent.RequestDocument -> {
                messageHelper.showMessage("Document requested successfully")
            }
            DocumentListEvent.ShowScanner -> {
                messageHelper.showMessage("Opening scanner...")
            }
            DocumentListEvent.AddDocument -> {
                messageHelper.showMessage("Adding new document...")
            }
        }
    }
}
