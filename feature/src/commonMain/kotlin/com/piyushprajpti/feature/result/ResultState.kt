package com.piyushprajpti.feature.result

data class ResultState(
    val selectedSubject: String = "Data Structures",
    val subjects: List<String> = listOf("Data Structures", "Algorithms", "Operating Systems", "Database Management", "Computer Networks", "Software Engineering"),
    val selectedSemester: String = "Sem 4",
    val semesters: List<String> = listOf("Sem 1", "Sem 2", "Sem 3", "Sem 4", "Sem 5", "Sem 6"),
    val selectedYear: String = "2023-24",
    val years: List<String> = listOf("2021-22", "2022-23", "2023-24", "2024-25"),
    
    val overallPassRate: String = "94%",
    val growthRate: String = "+2.4%",
    val vsPreviousPeriod: String = "Last Semester",
    
    val gradeDistribution: List<GradeCount> = listOf(
        GradeCount("A+", 12),
        GradeCount("A", 24),
        GradeCount("B", 32),
        GradeCount("C", 18),
        GradeCount("D", 6),
        GradeCount("E", 4)
    ),
    
    val topStudents: List<StudentResult> = listOf(
        StudentResult("Felix Graham", "44201", 98.5, 1),
        StudentResult("Sarah Jenkins", "44218", 96.2, 2),
        StudentResult("Michael Thorne", "44302", 95.8, 3),
        StudentResult("Alice Wong", "44315", 94.2, 4),
        StudentResult("Bob Miller", "44320", 93.5, 5)
    ),
    
    val atRiskStudents: List<AtRiskStudent> = listOf(
        AtRiskStudent("John Doe", "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png"),
        AtRiskStudent("Jane Smith", "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png"),
        AtRiskStudent("Kevin Lee", "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png"),
        AtRiskStudent("Mark Brown", "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png"),
        AtRiskStudent("Lucy Chen", "https://www.clipartmax.com/png/middle/144-1442578_flat-person-icon-d.png")
    ),
    val atRiskCount: Int = 5
)

data class GradeCount(
    val grade: String,
    val count: Int
)

data class StudentResult(
    val name: String,
    val id: String,
    val percentage: Double,
    val rank: Int
)

data class AtRiskStudent(
    val name: String,
    val imageUrl: String? = null
)
