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

    // attendance
    @Serializable
    data object AttendanceDashboard : NavRoutes, NavKey

    @Serializable
    data object AttendanceMark : NavRoutes, NavKey

    // schedule
    @Serializable
    data object Schedule : NavRoutes, NavKey


    // settings
    @Serializable
    data object Settings : NavRoutes, NavKey

    @Serializable
    data object Profile : NavRoutes, NavKey

    @Serializable
    data object ChangePassword : NavRoutes, NavKey

    // notifications
    @Serializable
    data object Notifications : NavRoutes, NavKey

    // my leave
    @Serializable
    data object MyLeaveBalance : NavRoutes, NavKey

    @Serializable
    data object MyLeaveApply : NavRoutes, NavKey

    // student leaves
    @Serializable
    data object StudentLeaveDashboard : NavRoutes, NavKey

    // assignment
    @Serializable
    data object AssignmentList : NavRoutes, NavKey

    @Serializable
    data object AssignmentSubmission : NavRoutes, NavKey

    @Serializable
    data object AssignmentCreate : NavRoutes, NavKey

    // exam
    @Serializable
    data object ExamSchedule : NavRoutes, NavKey

    // result
    @Serializable
    data object Result : NavRoutes, NavKey

    // notice
    @Serializable
    data object NoticeCreate : NavRoutes, NavKey

    // syllabus
    @Serializable
    data object SyllabusCoverage : NavRoutes, NavKey

    @Serializable
    data object SyllabusPlanner : NavRoutes, NavKey





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


    // transport
    @Serializable
    data object TransportDashboard : NavRoutes, NavKey

    @Serializable
    data object TransportTracking : NavRoutes, NavKey
}
