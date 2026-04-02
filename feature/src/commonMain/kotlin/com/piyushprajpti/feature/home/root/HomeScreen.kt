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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.LocalBottomBarPadding
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.home.components.AttendanceAlertCard
import com.piyushprajpti.feature.home.components.HomeHeader
import com.piyushprajpti.feature.home.components.NextClassCard
import com.piyushprajpti.feature.home.components.QuickActionsGrid
import com.piyushprajpti.feature.home.components.ScheduleItemCard
import com.piyushprajpti.feature.home.components.SectionTitle
import com.piyushprajpti.feature.home.components.StatCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNotificationClick: () -> Unit,
    onQuickActionClick: (QuickAction) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val topBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomBarPadding = LocalBottomBarPadding.current

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground),
        contentPadding = PaddingValues(
            top = topBarPadding + 16.dp,
            bottom = bottomBarPadding + 16.dp,
            start = 16.dp,
            end = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header
        item {
            HomeHeader(
                state = state,
                onNotificationClick = onNotificationClick
            )
        }

        // Attendance Alert
        state.attendanceAlert?.let { alert ->
            item {
                AttendanceAlertCard(alert)
            }
        }

        // Up Next
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SectionTitle("UP NEXT")
                state.nextClass?.let { nextClass ->
                    NextClassCard(nextClass)
                }
            }
        }

        // Stats Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    icon = Icons.Outlined.Timeline,
                    value = "${state.stats.attendancePercentage}%",
                    label = "ATTENDANCE",
                    color = Color(0xFFE16349),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    icon = Icons.Outlined.Payments,
                    value = "₹${state.stats.feesDue}",
                    label = "FEES DUE",
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    icon = Icons.Outlined.Book,
                    value = "${state.stats.booksCount}",
                    label = "BOOKS",
                    color = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Quick Actions
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SectionTitle("QUICK ACTIONS")
                QuickActionsGrid(
                    actions = QuickAction.entries,
                    onClick = onQuickActionClick
                )
            }
        }

        // Today's Schedule
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SectionTitle("TODAY'S SCHEDULE")
                state.todaySchedule.forEach { item ->
                    ScheduleItemCard(item)
                }
            }
        }
    }
}
