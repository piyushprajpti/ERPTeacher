package com.piyushprajpti.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoutes : NavKey {
    @Serializable
    data object Splash : NavRoutes, NavKey

    // auth
    @Serializable
    data object Tenant : NavRoutes, NavKey

    @Serializable
    data object Login : NavRoutes, NavKey

    // common
    @Serializable
    data object ResetPassword : NavRoutes, NavKey

    @Serializable
    data object Support : NavRoutes, NavKey

    // main
    @Serializable
    data object Home : NavRoutes, NavKey

    @Serializable
    data object Attendance : NavRoutes, NavKey

    @Serializable
    data object Schedule : NavRoutes, NavKey

    @Serializable
    data object Settings : NavRoutes, NavKey

    // settings
    @Serializable
    data object Profile : NavRoutes, NavKey

    @Serializable
    data object ChangePassword : NavRoutes, NavKey

    // notifications
    @Serializable
    data object Notifications : NavRoutes, NavKey

    // leave
    @Serializable
    data object LeaveHistory : NavRoutes, NavKey

    @Serializable
    data object ApplyLeave : NavRoutes, NavKey

    // fees
    @Serializable
    data object FeesSummary : NavRoutes, NavKey

    @Serializable
    data object FeesPayment : NavRoutes, NavKey

    @Serializable
    data object FeesHistory : NavRoutes, NavKey

    // library
    @Serializable
    data object LibraryDashboard : NavRoutes, NavKey

    @Serializable
    data object LibrarySearch : NavRoutes, NavKey

    @Serializable
    data object LibraryBookDetail : NavRoutes, NavKey

    @Serializable
    data object LibraryDue : NavRoutes, NavKey


    // documents
    @Serializable
    data object DocumentList : NavRoutes, NavKey
    @Serializable
    data object DocumentRequest : NavRoutes, NavKey

    // marks
    @Serializable
    data object MarksOverview : NavRoutes, NavKey
    @Serializable
    data object GradeCard : NavRoutes, NavKey

    // exam
    @Serializable
    data object ExamSchedule : NavRoutes, NavKey
    @Serializable
    data object ExamHallTicket : NavRoutes, NavKey

    // hostel
    @Serializable
    data object HostelDashboard : NavRoutes, NavKey
    @Serializable
    data object HostelMess : NavRoutes, NavKey
    @Serializable
    data object HostelGatePass : NavRoutes, NavKey
    @Serializable
    data object HostelComplaint : NavRoutes, NavKey

    // events
    @Serializable
    data object EventDashboard : NavRoutes, NavKey
    @Serializable
    data object EventDetail : NavRoutes, NavKey

    // assignment
    @Serializable
    data object AssignmentDashboard : NavRoutes, NavKey
    @Serializable
    data object AssignmentSubmit : NavRoutes, NavKey

    // transport
    @Serializable
    data object TransportDashboard : NavRoutes, NavKey
    @Serializable
    data object TransportTracking : NavRoutes, NavKey
}
