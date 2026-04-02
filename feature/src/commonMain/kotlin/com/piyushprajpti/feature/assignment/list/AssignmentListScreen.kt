package com.piyushprajpti.feature.assignment.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.assignment.list.components.AssignmentCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AssignmentListScreen(
    onBackClick: () -> Unit,
    onSubmissionClick: () -> Unit,
    onCreateClick: () -> Unit,
    viewModel: AssignmentListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(
                title = "Assignment",
                onBackClick = onBackClick
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 15.dp,
                    end = 15.dp,
                    top = 10.dp,
                    bottom = 80.dp // Extra padding for FAB and system bars
                ),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(state.assignments, key = { it.id }) { assignment ->
                    AssignmentCard(
                        assignment = assignment,
                        onSubmissionsClick = onSubmissionClick,
                        onShareClick = {
                            viewModel.onEvent(AssignmentListEvent.OnShareAssignment(assignment.id))
                        }
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = onCreateClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .navigationBarsPadding(),
            containerColor = CustomTheme.colors.primary,
            contentColor = CustomTheme.colors.white,
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Assignment"
            )
        }
    }
}
