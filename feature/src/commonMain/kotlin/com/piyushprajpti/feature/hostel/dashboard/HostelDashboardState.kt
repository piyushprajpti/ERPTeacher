package com.piyushprajpti.feature.hostel.dashboard

import com.piyushprajpti.feature.hostel.dashboard.models.MessMenu
import com.piyushprajpti.feature.hostel.dashboard.models.RecentComplaint
import com.piyushprajpti.feature.hostel.dashboard.models.StayDetails
import com.piyushprajpti.feature.hostel.dashboard.models.WardenDetails

data class HostelDashboardState(
    val stayDetails: StayDetails? = null,
    val messMenus: List<MessMenu> = emptyList(),
    val recentComplaints: List<RecentComplaint> = emptyList(),
    val wardenDetails: WardenDetails? = null
)
