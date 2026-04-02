package com.piyushprajpti.feature.home.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.LocalBottomBarPadding
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.home.components.AttendanceAlertCard
import com.piyushprajpti.feature.home.components.ClassesSummaryCard
import com.piyushprajpti.feature.home.components.HomeHeader
import com.piyushprajpti.feature.home.components.NextClassCard
import com.piyushprajpti.feature.home.components.PendingTaskCard
import com.piyushprajpti.feature.home.components.QuickActionsGrid
import com.piyushprajpti.feature.home.components.ScheduleItemCard
import com.piyushprajpti.feature.home.components.SectionTitle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNotificationClick: () -> Unit,
    onQuickActionClick: (QuickAction) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomBarPadding = LocalBottomBarPadding.current

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground),
        contentPadding = PaddingValues(
            top = 5.dp + statusBarPadding,
            bottom = bottomBarPadding,
            start = 15.dp,
            end = 15.dp
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Header
        item {
            HomeHeader(
                state = state,
                onNotificationClick = {
//                    viewModel.onEvent(HomeEvent.OnNotificationClick)
                    onNotificationClick()
                }
            )
        }

        // Attendance Alert
        state.attendanceAlert?.let { alert ->
            item {
                AttendanceAlertCard(alert = alert)
            }
        }

        // Next Class
        state.nextClass?.let { nextClass ->
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    SectionTitle("NEXT CLASS")
                    NextClassCard(nextClass = nextClass)
                }
            }
        }

        // Pending Tasks
        if (state.pendingTasks.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    SectionTitle("PENDING TASKS")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        state.pendingTasks.forEach { task ->
                            PendingTaskCard(
                                task = task,
                                onClick = { /* Navigate to task details */ },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        // Today's Schedule
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SectionTitle(
                    title = "Today's Schedule",
                    onViewAllClick = { /* Navigate to full schedule */ }
                )
                state.todaySchedule.forEach { scheduleItem ->
                    ScheduleItemCard(
                        item = scheduleItem,
                        onMarkAttendanceClick = { /* Handle mark attendance */ }
                    )
                }
            }
        }

        // Quick Actions
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SectionTitle("Quick Actions")
                QuickActionsGrid(
                    actions = QuickAction.entries,
                    onClick = { action ->
                        viewModel.onEvent(HomeEvent.OnQuickActionClick(action))
                        onQuickActionClick(action)
                    }
                )
            }
        }

        // Classes Summary
        item {
            ClassesSummaryCard(summary = state.classesSummary)
        }
    }
}
