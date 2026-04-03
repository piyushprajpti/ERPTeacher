package com.piyushprajpti.feature.hostel.complaints

import com.piyushprajpti.feature.hostel.complaints.models.Complaint
import com.piyushprajpti.feature.hostel.complaints.models.Guideline

data class HostelComplaintState(
    val selectedTab: Int = 0,
    val activeCount: Int = 2,
    val resolvedCount: Int = 14,
    val recentComplaints: List<Complaint> = emptyList(),
    val historyComplaints: List<Complaint> = emptyList(),
    val guidelines: List<Guideline> = emptyList(),
    val selectedCategory: String = "Electrical",
    val description: String = "",
    val categories: List<String> = listOf("Electrical", "Plumbing", "Carpentry", "Internet", "Housekeeping", "Other")
)
