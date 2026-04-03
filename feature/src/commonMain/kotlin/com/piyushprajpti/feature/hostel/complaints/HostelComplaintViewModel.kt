package com.piyushprajpti.feature.hostel.complaints

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Toast
import com.piyushprajpti.components.message_helper.model.ToastType
import com.piyushprajpti.feature.hostel.complaints.models.Complaint
import com.piyushprajpti.feature.hostel.complaints.models.ComplaintStatus
import com.piyushprajpti.feature.hostel.complaints.models.Guideline
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HostelComplaintViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(HostelComplaintState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HostelComplaintState()
    )

    private fun loadData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    recentComplaints = listOf(
                        Complaint(
                            id = "1",
                            title = "Leaking Tap",
                            category = "Plumbing",
                            date = "Oct 12, 2023",
                            status = ComplaintStatus.ASSIGNED,
                            statusText = "IN PROGRESS",
                            progress = 0.5f
                        ),
                        Complaint(
                            id = "2",
                            title = "Fan not working",
                            category = "Electrical",
                            date = "Sept 28, 2023",
                            status = ComplaintStatus.RESOLVED,
                            statusText = "RESOLVED",
                            progress = 1f,
                            rating = 4
                        )
                    ),
                    historyComplaints = listOf(
                        Complaint(
                            id = "3",
                            title = "AC Remote missing",
                            category = "Electrical",
                            date = "Aug 15, 2023",
                            status = ComplaintStatus.RESOLVED,
                            statusText = "RESOLVED",
                            progress = 1f,
                            rating = 5
                        ),
                        Complaint(
                            id = "4",
                            title = "Door lock broken",
                            category = "Carpentry",
                            date = "July 10, 2023",
                            status = ComplaintStatus.RESOLVED,
                            statusText = "RESOLVED",
                            progress = 1f,
                            rating = 3
                        )
                    ),
                    guidelines = listOf(
                        Guideline(
                            "Emergency Contacts",
                            "For immediate issues like fire or short circuit, call the hostel emergency line at +91 00000 00000."
                        ),
                        Guideline(
                            "Maintenance Hours",
                            "Routine maintenance complaints are addressed between 9:00 AM and 6:00 PM."
                        ),
                        Guideline(
                            "Prohibited Changes",
                            "Students are not allowed to make any permanent changes to the room fixtures."
                        ),
                        Guideline(
                            "Response Time",
                            "Expect a response within 24-48 hours for non-emergency complaints."
                        )
                    )
                )
            }
        }
    }

    fun onEvent(event: HostelComplaintEvent) {
        when (event) {
            is HostelComplaintEvent.OnTabChange -> _state.update { it.copy(selectedTab = event.index) }
            is HostelComplaintEvent.OnCategoryChange -> _state.update { it.copy(selectedCategory = event.category) }
            is HostelComplaintEvent.OnDescriptionChange -> _state.update { it.copy(description = event.description) }
            HostelComplaintEvent.OnUploadPhotoClick -> {
                // Photo upload logic
            }
            HostelComplaintEvent.OnSubmitComplaint -> {
                if (_state.value.description.isBlank()) {
                    messageHelper.setToast(Toast(type = ToastType.WARNING, text = "Please enter description"))
                    return
                }
                messageHelper.setToast(Toast(type = ToastType.SUCCESS, text = "Complaint submitted successfully"))
                _state.update { it.copy(description = "") }
            }
            is HostelComplaintEvent.OnRateComplaint -> {
                // Handle rating
            }
        }
    }
}
