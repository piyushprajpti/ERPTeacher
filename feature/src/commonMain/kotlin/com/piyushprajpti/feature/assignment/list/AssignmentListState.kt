package com.piyushprajpti.feature.assignment.list

data class AssignmentListState(
    val isLoading: Boolean = false,
    val assignments: List<Assignment> = emptyList()
)

data class Assignment(
    val id: String,
    val subject: String,
    val unit: String,
    val title: String,
    val course: String,
    val className: String,
    val dueDate: String,
    val dueTime: String
)
