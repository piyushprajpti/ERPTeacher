package com.piyushprajpti.di

import com.piyushprajpti.feature.assignment.create.AssignmentCreateViewModel
import com.piyushprajpti.feature.assignment.list.AssignmentListViewModel
import com.piyushprajpti.feature.assignment.submission.AssignmentSubmissionViewModel
import com.piyushprajpti.feature.attendance.dashboard.AttendanceDashboardViewModel
import com.piyushprajpti.feature.attendance.mark.AttendanceMarkViewModel
import com.piyushprajpti.feature.auth.login.LoginViewModel
import com.piyushprajpti.feature.auth.tenant.TenantViewModel
import com.piyushprajpti.feature.common.reset_password.ResetPasswordViewModel
import com.piyushprajpti.feature.common.support.SupportViewModel
import com.piyushprajpti.feature.home.notifications.NotificationViewModel
import com.piyushprajpti.feature.home.root.HomeViewModel
import com.piyushprajpti.feature.leave.leave_apply.MyLeaveApplyViewModel
import com.piyushprajpti.feature.my_leave.balance.MyLeaveBalanceViewModel
import com.piyushprajpti.feature.schedule.ScheduleViewModel
import com.piyushprajpti.feature.settings.change_password.ChangePasswordViewModel
import com.piyushprajpti.feature.settings.profile.ProfileViewModel
import com.piyushprajpti.feature.settings.root.SettingsViewModel
import com.piyushprajpti.feature.student_leave.dashboard.StudentLeaveDashboardViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
    // auth
    viewModelOf(::TenantViewModel)
    viewModelOf(::LoginViewModel)

    // common
    viewModelOf(::ResetPasswordViewModel)
    viewModelOf(::SupportViewModel)

    // settings
    viewModelOf(::SettingsViewModel)
    viewModelOf(::ChangePasswordViewModel)
    viewModelOf(::ProfileViewModel)

    // home
    viewModelOf(::HomeViewModel)
    viewModelOf(::NotificationViewModel)

    // attendance
    viewModelOf(::AttendanceDashboardViewModel)
    viewModelOf(::AttendanceMarkViewModel)

    // schedule
    viewModelOf(::ScheduleViewModel)

    // assignment
    viewModelOf(::AssignmentListViewModel)
    viewModelOf(::AssignmentSubmissionViewModel)
    viewModelOf(::AssignmentCreateViewModel)

    // student leave
    viewModelOf(::StudentLeaveDashboardViewModel)

    // my leave
    viewModelOf(::MyLeaveBalanceViewModel)
    viewModelOf(::MyLeaveApplyViewModel)

}