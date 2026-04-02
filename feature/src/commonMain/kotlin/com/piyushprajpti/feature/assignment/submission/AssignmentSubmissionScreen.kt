package com.piyushprajpti.feature.assignment.submission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.assignment.submission.components.AssignmentHeader
import com.piyushprajpti.feature.assignment.submission.components.FilterChips
import com.piyushprajpti.feature.assignment.submission.components.StatCards
import com.piyushprajpti.feature.assignment.submission.components.SubmissionItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AssignmentSubmissionScreen(
    onBackClick: () -> Unit,
    viewModel: AssignmentSubmissionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Submissions"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                AssignmentHeader(
                    subject = state.subject,
                    unit = state.unit,
                    title = state.title,
                    dueDate = state.dueDate,
                    onGradeAllClick = { viewModel.onEvent(AssignmentSubmissionEvent.OnGradeAllClick) }
                )
            }

            item {
                StatCards(
                    total = state.totalStudents,
                    submitted = state.submittedCount,
                    pending = state.pendingCount,
                    late = state.lateCount
                )
            }

            item {
                FilterChips(
                    selectedFilter = state.selectedFilter,
                    onFilterChange = { viewModel.onEvent(AssignmentSubmissionEvent.OnFilterChange(it)) }
                )
            }

            items(state.filteredSubmissions, key = { it.id }) { submission ->
                SubmissionItem(
                    submission = submission,
                    onClick = { viewModel.onEvent(AssignmentSubmissionEvent.OnSubmissionClick(submission.id)) }
                )
            }

            // Add some bottom spacing to ensure content isn't cramped
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
