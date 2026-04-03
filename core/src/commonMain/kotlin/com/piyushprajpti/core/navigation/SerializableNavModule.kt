package com.piyushprajpti.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val serializableNavModule = SerializersModule {
    polymorphic(NavKey::class) {

        subclass(NavRoutes.Splash::class, NavRoutes.Splash.serializer())

        // auth
        subclass(NavRoutes.Tenant::class, NavRoutes.Tenant.serializer())
        subclass(NavRoutes.Login::class, NavRoutes.Login.serializer())

        // common
        subclass(NavRoutes.ResetPassword::class, NavRoutes.ResetPassword.serializer())
        subclass(NavRoutes.Support::class, NavRoutes.Support.serializer())

        // main tabs
        subclass(NavRoutes.Home::class, NavRoutes.Home.serializer())

        // attendance
        subclass(NavRoutes.AttendanceDashboard::class, NavRoutes.AttendanceDashboard.serializer())
        subclass(NavRoutes.AttendanceMark::class, NavRoutes.AttendanceMark.serializer())

        // schedule
        subclass(NavRoutes.Schedule::class, NavRoutes.Schedule.serializer())

        //settings
        subclass(NavRoutes.Settings::class, NavRoutes.Settings.serializer())
        subclass(NavRoutes.Profile::class, NavRoutes.Profile.serializer())
        subclass(NavRoutes.ChangePassword::class, NavRoutes.ChangePassword.serializer())

        // notifications
        subclass(NavRoutes.Notifications::class, NavRoutes.Notifications.serializer())

        // my leave
        subclass(NavRoutes.MyLeaveBalance::class, NavRoutes.MyLeaveBalance.serializer())
        subclass(NavRoutes.MyLeaveApply::class, NavRoutes.MyLeaveApply.serializer())

        // student leave
        subclass(NavRoutes.StudentLeaveDashboard::class,NavRoutes.StudentLeaveDashboard.serializer())

        // assignment
        subclass(NavRoutes.AssignmentList::class, NavRoutes.AssignmentList.serializer())
        subclass(NavRoutes.AssignmentSubmission::class, NavRoutes.AssignmentSubmission.serializer())
        subclass(NavRoutes.AssignmentCreate::class, NavRoutes.AssignmentCreate.serializer())

        // exam
        subclass(NavRoutes.ExamSchedule::class, NavRoutes.ExamSchedule.serializer())

        // notice
        subclass(NavRoutes.NoticeCreate::class, NavRoutes.NoticeCreate.serializer())

        // syllabus
        subclass(NavRoutes.SyllabusCoverage::class, NavRoutes.SyllabusCoverage.serializer())
        subclass(NavRoutes.SyllabusPlanner::class, NavRoutes.SyllabusPlanner.serializer())




        // fees
        subclass(NavRoutes.FeesSummary::class, NavRoutes.FeesSummary.serializer())
        subclass(NavRoutes.FeesPayment::class, NavRoutes.FeesPayment.serializer())
        subclass(NavRoutes.FeesHistory::class, NavRoutes.FeesHistory.serializer())

        // library
        subclass(NavRoutes.LibraryDashboard::class, NavRoutes.LibraryDashboard.serializer())
        subclass(NavRoutes.LibrarySearch::class, NavRoutes.LibrarySearch.serializer())
        subclass(NavRoutes.LibraryBookDetail::class, NavRoutes.LibraryBookDetail.serializer())
        subclass(NavRoutes.LibraryDue::class, NavRoutes.LibraryDue.serializer())

        // documents
        subclass(NavRoutes.DocumentList::class, NavRoutes.DocumentList.serializer())
        subclass(NavRoutes.DocumentRequest::class, NavRoutes.DocumentRequest.serializer())

        // marks
        subclass(NavRoutes.MarksOverview::class, NavRoutes.MarksOverview.serializer())
        subclass(NavRoutes.GradeCard::class, NavRoutes.GradeCard.serializer())

        // exam
        subclass(NavRoutes.ExamHallTicket::class, NavRoutes.ExamHallTicket.serializer())

        // hostel
        subclass(NavRoutes.HostelDashboard::class, NavRoutes.HostelDashboard.serializer())
        subclass(NavRoutes.HostelMess::class, NavRoutes.HostelMess.serializer())
        subclass(NavRoutes.HostelGatePass::class, NavRoutes.HostelGatePass.serializer())
        subclass(NavRoutes.HostelComplaint::class, NavRoutes.HostelComplaint.serializer())

        // events
        subclass(NavRoutes.EventDashboard::class, NavRoutes.EventDashboard.serializer())
        subclass(NavRoutes.EventDetail::class, NavRoutes.EventDetail.serializer())


        // transport
        subclass(NavRoutes.TransportDashboard::class, NavRoutes.TransportDashboard.serializer())
        subclass(NavRoutes.TransportTracking::class, NavRoutes.TransportTracking.serializer())
    }
}
