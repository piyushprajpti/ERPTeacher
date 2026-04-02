package com.piyushprajpti.feature.attendance.mark

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.attendance.mark.components.AttendanceSummaryCard
import com.piyushprajpti.feature.attendance.mark.components.StudentAttendanceItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AttendanceMarkScreen(
    onBackClick: () -> Unit,
    viewModel: AttendanceMarkViewModel = koinViewModel()
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
            title = "Mark Attendance"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(15.dp)
        ) {
            item {
                Text(
                    text = "Attendance Overview",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Text(
                    text = state.sessionActiveTime,
                    fontSize = 14.sp,
                    color = CustomTheme.colors.secondaryText
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AttendanceSummaryCard(
                        label = "PRESENT",
                        count = state.presentCount,
                        color = CustomTheme.colors.success,
                        modifier = Modifier.weight(1f)
                    )
                    AttendanceSummaryCard(
                        label = "LATE",
                        count = state.lateCount,
                        color = CustomTheme.colors.warning,
                        modifier = Modifier.weight(1f)
                    )
                    AttendanceSummaryCard(
                        label = "ABSENT",
                        count = state.absentCount,
                        color = CustomTheme.colors.error,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "STUDENT ROSTER • ${state.totalStudents} TOTAL",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.tertiaryText,
                        letterSpacing = 1.sp
                    )
                    Row(
                        modifier = Modifier.clickable { viewModel.onEvent(AttendanceMarkEvent.OnFilterClick) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null,
                            tint = CustomTheme.colors.secondaryText,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Filter",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = CustomTheme.colors.secondaryText
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            items(state.students) { student ->
                StudentAttendanceItem(
                    student = student,
                    onStatusChange = { status ->
                        viewModel.onEvent(AttendanceMarkEvent.OnStatusChange(student.id, status))
                    }
                )
                HorizontalDivider(
                    color = CustomTheme.colors.outline,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PrimaryActionButton(
                text = "Submit Attendance",
                onClick = { viewModel.onEvent(AttendanceMarkEvent.OnSubmitClick) },
                containerColor = CustomTheme.colors.primary,
                contentColor = CustomTheme.colors.white,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Submit Attendance",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.white
                    )
                }
            )

            PrimaryActionButton(
                text = "Save Draft",
                onClick = { viewModel.onEvent(AttendanceMarkEvent.OnSaveDraftClick) },
                containerColor = CustomTheme.colors.secondaryBackground,
                contentColor = CustomTheme.colors.primaryText,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Save,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = CustomTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Save Draft",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                }
            )
        }
    }
}
