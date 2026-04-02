package com.piyushprajpti.feature.attendance.mark

data class AttendanceMarkState(
    val isLoading: Boolean = false,
    val sessionActiveTime: String = "",
    val students: List<StudentAttendance> = emptyList(),
    val totalStudents: Int = 0,
    val presentCount: Int = 0,
    val lateCount: Int = 0,
    val absentCount: Int = 0
)

data class StudentAttendance(
    val id: String,
    val name: String,
    val rollNo: String,
    val avatarUrl: String? = null,
    val status: AttendanceStatus = AttendanceStatus.UNMARKED
)

enum class AttendanceStatus {
    PRESENT, LATE, ABSENT, UNMARKED
}