package com.piyushprajpti.feature.assignment.submission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AssignmentSubmissionViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(AssignmentSubmissionState())
    val state = _state.onStart {
        loadDummyData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AssignmentSubmissionState()
    )

    fun onEvent(event: AssignmentSubmissionEvent) {
        when (event) {
            is AssignmentSubmissionEvent.OnFilterChange -> {
                _state.update { 
                    it.copy(
                        selectedFilter = event.filter,
                        filteredSubmissions = filterSubmissions(it.submissions, event.filter)
                    )
                }
            }
            AssignmentSubmissionEvent.OnGradeAllClick -> {
                messageHelper.showMessage("Grade All clicked")
            }
            is AssignmentSubmissionEvent.OnSubmissionClick -> {
                messageHelper.showMessage("Clicked on ${event.submissionId}")
            }
        }
    }

    private fun loadDummyData() {
        val dummySubmissions = listOf(
            Submission(
                id = "1",
                studentName = "Jane Doe",
                rollNo = "2024-001",
                submittedAt = "10:45 AM",
                hasAttachment = true,
                attachmentType = AttachmentType.Document
            ),
            Submission(
                id = "2",
                studentName = "Ben Smith",
                rollNo = "2024-012",
                submittedAt = "02:15 PM",
                score = 85,
                isLate = true,
                hasAttachment = true,
                attachmentType = AttachmentType.Image
            ),
            Submission(
                id = "3",
                studentName = "Alice Liu",
                rollNo = "2024-045",
                submittedAt = null,
                score = null
            ),
            Submission(
                id = "4",
                studentName = "Michael Chen",
                rollNo = "2024-008",
                submittedAt = "09:12 AM",
                hasAttachment = true,
                attachmentType = AttachmentType.Image
            ),
            Submission(
                id = "5",
                studentName = "Sarah Connor",
                rollNo = "2024-022",
                submittedAt = "11:30 AM",
                score = 92,
                hasAttachment = true
            ),
            Submission(
                id = "6",
                studentName = "David Miller",
                rollNo = "2024-015",
                submittedAt = "04:20 PM",
                isLate = true,
                hasAttachment = true
            ),
            Submission(
                id = "7",
                studentName = "Emily Blunt",
                rollNo = "2024-033",
                submittedAt = null
            ),
            Submission(
                id = "8",
                studentName = "Chris Evans",
                rollNo = "2024-005",
                submittedAt = "08:00 AM",
                score = 78,
                hasAttachment = true
            ),
            Submission(
                id = "9",
                studentName = "Robert Downey",
                rollNo = "2024-009",
                submittedAt = "07:30 AM",
                score = 95,
                hasAttachment = true
            ),
            Submission(
                id = "10",
                studentName = "Scarlett Johansson",
                rollNo = "2024-010",
                submittedAt = "06:15 PM",
                isLate = true,
                hasAttachment = true,
                attachmentType = AttachmentType.Image
            )
        )

        _state.update {
            it.copy(
                subject = "PHYSICS",
                unit = "UNIT 3",
                title = "Quantum Mechanics & Wave Theory",
                dueDate = "25 Oct, 11:59 PM",
                totalStudents = 45,
                submittedCount = 32,
                pendingCount = 13,
                lateCount = 4,
                submissions = dummySubmissions,
                filteredSubmissions = dummySubmissions
            )
        }
    }

    private fun filterSubmissions(submissions: List<Submission>, filter: SubmissionFilter): List<Submission> {
        return when (filter) {
            SubmissionFilter.All -> submissions
            SubmissionFilter.Submitted -> submissions.filter { it.submittedAt != null }
            SubmissionFilter.Graded -> submissions.filter { it.score != null }
            SubmissionFilter.Late -> submissions.filter { it.isLate }
        }
    }
}
