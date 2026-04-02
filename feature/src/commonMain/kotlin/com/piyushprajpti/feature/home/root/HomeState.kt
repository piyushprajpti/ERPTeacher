package com.piyushprajpti.feature.home.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.HistoryEdu
import com.piyushprajpti.core.navigation.NavRoutes

data class HomeState(
    val userName: String = "Arjun Mehta",
    val department: String = "CS Dept",
    val profilePictureUrl: String = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
    val currentDate: String = "Monday, Oct 23",
    val pendingTasks: List<PendingTask> = emptyList(),
    val todaySchedule: List<TeacherScheduleItem> = emptyList(),
    val classesSummary: ClassesSummary = ClassesSummary(),
    val attendanceAlert: AttendanceAlert? = null,
    val nextClass: NextClass? = null,
    val isLoading: Boolean = false
)

data class AttendanceAlert(
    val message: String
)

data class NextClass(
    val startsInMinutes: Int,
    val subject: String,
    val professor: String,
    val startTime: String,
    val room: String
)

data class PendingTask(
    val title: String,
    val count: Int,
    val taskType: PendingTaskType
)

enum class PendingTaskType {
    UNGRADED_ASSIGNMENTS,
    LEAVE_APPLICATIONS
}

data class TeacherScheduleItem(
    val subject: String,
    val section: String,
    val time: String,
    val room: String,
    val studentAvatars: List<String> = emptyList(),
    val totalStudents: Int = 0,
    val isAttendanceMarked: Boolean = false
)

data class ClassesSummary(
    val activeClasses: Int = 0,
    val subjects: Int = 0,
    val totalStudents: Int = 0
)

enum class QuickAction(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: NavRoutes
) {
    Assignment("Assignment", Icons.AutoMirrored.Outlined.Assignment, NavRoutes.AssignmentDashboard),
    MyLeaves("My Leaves", Icons.AutoMirrored.Outlined.ExitToApp, NavRoutes.MyLeaveBalance),
    StudentLeaves("Student Leaves", Icons.AutoMirrored.Outlined.ExitToApp, NavRoutes.StudentLeaveDashboard),
    Exam("Exam", Icons.Outlined.HistoryEdu, NavRoutes.ExamSchedule),
    Result("Result", Icons.Outlined.Grade, NavRoutes.MarksOverview),
    Syllabus("Syllabus", Icons.Outlined.Description, NavRoutes.DocumentList),
    Event("Event", Icons.Outlined.Event, NavRoutes.EventDashboard)
}
