package com.piyushprajpti.feature.student_leave.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.student_leave.dashboard.components.LeaveRequestCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StudentLeaveDashboardScreen(
    onBackClick: () -> Unit,
    viewModel: StudentLeaveDashboardViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Student Leave Requests"
        )

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)

        // Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LeaveStatus.entries.forEach { status ->
                val isSelected = state.selectedTab == status
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { viewModel.onEvent(StudentLeaveDashboardEvent.OnTabSelected(status)) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = status.name.lowercase().replaceFirstChar { it.uppercase() },
                            fontSize = 16.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryText
                        )
                        if (status == LeaveStatus.PENDING && state.pendingCount > 0) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(CircleShape)
                                    .background(CustomTheme.colors.primary.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = state.pendingCount.toString(),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CustomTheme.colors.primary
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(2.dp)
                            .background(if (isSelected) CustomTheme.colors.primary else Color.Transparent)
                    )
                }
            }
        }

        // Leave Requests List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.leaves, key = { it.id }) { leave ->
                LeaveRequestCard(
                    leave = leave,
                    onApprove = { viewModel.onEvent(StudentLeaveDashboardEvent.OnApproveClick(leave.id)) },
                    onReject = { viewModel.onEvent(StudentLeaveDashboardEvent.OnRejectClick(leave.id)) }
                )
            }

            if (state.leaves.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(0.7f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No ${state.selectedTab.name.lowercase()} leave requests found.",
                            color = CustomTheme.colors.secondaryText,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}