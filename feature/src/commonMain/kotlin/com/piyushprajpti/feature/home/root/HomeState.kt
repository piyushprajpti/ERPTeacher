package com.piyushprajpti.feature.home.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.HomeWork
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.School
import androidx.compose.ui.graphics.vector.ImageVector
import com.piyushprajpti.core.navigation.NavRoutes

data class HomeState(
    val userName: String = "Arjun Mehta",
    val profilePictureUrl: String? = null,
    val currentDate: String = "Monday, 23rd October 2023",
    val attendanceAlert: AttendanceAlert? = AttendanceAlert(
        message = "Your OS attendance is 68%. Minimum 75% required.",
        percentage = 68
    ),
    val nextClass: NextClass? = NextClass(
        subject = "Data Structures",
        professor = "Prof. Sharma",
        startTime = "10:30 AM",
        room = "Room 402",
        startsInMinutes = 15
    ),
    val stats: HomeStats = HomeStats(
        attendancePercentage = 86,
        feesDue = 500,
        booksCount = 5
    ),
    val todaySchedule: List<ScheduleItem> = listOf(
        ScheduleItem("12:00 PM", "DBMS Theory", "Hall 301"),
        ScheduleItem("02:00 PM", "Operating Systems", "Lab 1"),
        ScheduleItem("04:00 PM", "Software Engineering Lab", "Lab 4")
    ),
    val isLoading: Boolean = false
)

data class AttendanceAlert(
    val message: String,
    val percentage: Int
)

data class NextClass(
    val subject: String,
    val professor: String,
    val startTime: String,
    val room: String,
    val startsInMinutes: Int
)

data class HomeStats(
    val attendancePercentage: Int,
    val feesDue: Int,
    val booksCount: Int
)

enum class QuickAction(
    val title: String,
    val icon: ImageVector,
    val route: NavRoutes
) {
    Leave("Leave", Icons.AutoMirrored.Outlined.ExitToApp, NavRoutes.LeaveHistory),
    Fees("Fees", Icons.Outlined.Payments, NavRoutes.FeesSummary),
    Library("Library", Icons.AutoMirrored.Outlined.LibraryBooks, NavRoutes.LibraryDashboard),
    Documents("Documents", Icons.Outlined.Description, NavRoutes.DocumentList),
    Marks("Marks", Icons.Outlined.Grade, NavRoutes.MarksOverview),
    Exam("Exam", Icons.Outlined.HistoryEdu, NavRoutes.ExamSchedule),
    Hostel("Hostel", Icons.Outlined.HomeWork, NavRoutes.HostelDashboard),
    Events("Events", Icons.Outlined.Event, NavRoutes.EventDashboard),
    Assignment("Assignment", Icons.AutoMirrored.Outlined.Assignment, NavRoutes.AssignmentDashboard),
    Transport("Transport", Icons.Outlined.School, NavRoutes.TransportDashboard)
}

data class ScheduleItem(
    val time: String,
    val subject: String,
    val location: String
)
