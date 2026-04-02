package com.piyushprajpti.feature.result

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.result.components.AtRiskCard
import com.piyushprajpti.feature.result.components.FilterChip
import com.piyushprajpti.feature.result.components.GradeDistributionCard
import com.piyushprajpti.feature.result.components.PassRateCard
import com.piyushprajpti.feature.result.components.TopStudentCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ResultScreen(
    onBackClick: () -> Unit,
    viewModel: ResultViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
    ) {
        TopBar(onBackClick = onBackClick, title = "Class Analysis")

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FilterChip(
                        text = state.selectedSubject,
                        options = state.subjects,
                        onOptionSelected = { viewModel.onEvent(ResultEvent.OnSubjectSelected(it)) },
                        modifier = Modifier.weight(1.3f)
                    )
                    FilterChip(
                        text = state.selectedSemester,
                        options = state.semesters,
                        onOptionSelected = { viewModel.onEvent(ResultEvent.OnSemesterSelected(it)) },
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        text = state.selectedYear,
                        options = state.years,
                        onOptionSelected = { viewModel.onEvent(ResultEvent.OnYearSelected(it)) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                PassRateCard(
                    passRate = state.overallPassRate,
                    growth = state.growthRate,
                    vsPrevious = state.vsPreviousPeriod
                )
            }

            item {
                GradeDistributionCard(state.gradeDistribution)
            }

            item {
                SectionHeader(title = "Top 3 Students", actionText = "View All", onActionClick = {})
            }

            items(state.topStudents.take(3)) { student ->
                TopStudentCard(student)
            }

            item {
                AtRiskCard(
                    count = state.atRiskCount,
                    students = state.atRiskStudents,
                    onAlertClick = { viewModel.onEvent(ResultEvent.OnSendInterventionAlert) }
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    ActionButton(
                        text = "Share Report",
                        icon = Icons.Default.Share,
                        containerColor = CustomTheme.colors.secondaryBackground,
                        contentColor = CustomTheme.colors.primaryText,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onEvent(ResultEvent.OnShareReport) }
                    )
                    ActionButton(
                        text = "Export Result",
                        icon = Icons.Default.FileDownload,
                        containerColor = CustomTheme.colors.primary,
                        contentColor = CustomTheme.colors.white,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onEvent(ResultEvent.OnExportResult) }
                    )
                }
            }
            
            item {
                Spacer(modifier = Modifier.navigationBarsPadding().height(1.dp))
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    actionText: String,
    onActionClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )
        Text(
            text = actionText,
            fontSize = 14.sp,
            color = CustomTheme.colors.primary,
            modifier = Modifier.clickable { onActionClick() }
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(50.dp)
            .background(containerColor, RoundedCornerShape(25.dp))
            .clickable { onClick() }
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = contentColor
        )
    }
}
