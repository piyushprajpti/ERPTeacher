package com.piyushprajpti.feature.hostel.complaints

sealed interface HostelComplaintEvent {
    data class OnTabChange(val index: Int) : HostelComplaintEvent
    data class OnCategoryChange(val category: String) : HostelComplaintEvent
    data class OnDescriptionChange(val description: String) : HostelComplaintEvent
    data object OnUploadPhotoClick : HostelComplaintEvent
    data object OnSubmitComplaint : HostelComplaintEvent
    data class OnRateComplaint(val id: String, val rating: Int) : HostelComplaintEvent
}
