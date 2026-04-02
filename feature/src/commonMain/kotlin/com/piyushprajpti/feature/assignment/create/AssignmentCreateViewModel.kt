package com.piyushprajpti.feature.assignment.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AssignmentCreateViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(AssignmentCreateState())
    val state = _state.onStart {
        _state.update {
            it.copy(
                allowedSubmissions = listOf(SubmissionType.PDF),
                referenceMaterials = listOf("BST_Resources.pdf", "Complexity_Guide.docx")
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AssignmentCreateState()
    )

    fun onEvent(event: AssignmentCreateEvent) {
        when (event) {
            is AssignmentCreateEvent.OnTitleChange -> {
                _state.update { it.copy(title = event.title) }
            }

            is AssignmentCreateEvent.OnDescriptionChange -> {
                _state.update { it.copy(description = event.description) }
            }

            is AssignmentCreateEvent.OnDueDateChange -> {
                _state.update { it.copy(dueDate = event.date) }
            }

            is AssignmentCreateEvent.OnDueTimeChange -> {
                _state.update { it.copy(dueTime = event.time) }
            }

            is AssignmentCreateEvent.OnMaxMarksChange -> {
                _state.update { it.copy(maxMarks = event.marks) }
            }

            is AssignmentCreateEvent.OnSubmissionTypeToggle -> {
                _state.update {
                    val current = it.allowedSubmissions.toMutableList()
                    if (current.contains(event.type)) {
                        current.remove(event.type)
                    } else {
                        current.add(event.type)
                    }
                    it.copy(allowedSubmissions = current)
                }
            }

            is AssignmentCreateEvent.OnPublishVisibilityChange -> {
                _state.update { it.copy(publishVisibility = event.visibility) }
            }

            AssignmentCreateEvent.OnAddReferenceMaterial -> {
                // Logic to add reference material
            }

            AssignmentCreateEvent.OnCreateAssignment -> {
                createAssignment()
            }
        }
    }

    private fun createAssignment() {
        // Logic to create assignment
        messageHelper.showMessage("Assignment Created Successfully")
    }
}