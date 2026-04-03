package com.piyushprajpti.feature.syllabus.coverage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAddCheck
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material3.Icon
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
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.syllabus.coverage.components.ProgressCard
import com.piyushprajpti.feature.syllabus.coverage.components.TimelineChart
import com.piyushprajpti.feature.syllabus.coverage.components.UnitCoverageCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SyllabusCoverageScreen(
    onBackClick: () -> Unit,
    onPlannerClick: () -> Unit,
    viewModel: SyllabusCoverageViewModel = koinViewModel()
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
            title = "Syllabus Coverage",
            modifier = Modifier.padding(horizontal = 15.dp)
        )

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ProgressCard(
                progress = state.totalProgress,
                totalUnits = state.totalUnits,
                pendingUnits = state.pendingUnits,
                delayedUnits = state.delayedUnits
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "UNIT BREAKDOWN",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.secondaryText
                )
                Text(
                    text = "Batch Mark",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CustomTheme.colors.primary,
                    modifier = Modifier.clickable { viewModel.onEvent(SyllabusCoverageEvent.OnBatchMarkTopics) }
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                state.units.forEach { unit ->
                    UnitCoverageCard(
                        unit = unit,
                        onClick = onPlannerClick
                    )
                }
            }

            // Timeline Variance Chart
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp))
                    .background(CustomTheme.colors.secondaryBackground)
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Timeline Variance",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Indicator(color = CustomTheme.colors.secondaryText, label = "Planned")
                        Spacer(modifier = Modifier.width(10.dp))
                        Indicator(color = CustomTheme.colors.primary, label = "Actual")
                    }
                }
                
                Spacer(modifier = Modifier.height(30.dp))
                
                TimelineChart(
                    data = state.timelineData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                
                Spacer(modifier = Modifier.height(10.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    state.timelineData.forEach { point ->
                        Text(
                            text = point.label,
                            fontSize = 10.sp,
                            color = CustomTheme.colors.tertiaryText
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PrimaryActionButton(
                text = "Batch Mark Topics",
                onClick = { viewModel.onEvent(SyllabusCoverageEvent.OnBatchMarkTopics) },
                height = 50.dp
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.PlaylistAddCheck,
                        contentDescription = null,
                        tint = CustomTheme.colors.white,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Batch Mark Topics",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.white
                    )
                }
            }

            PrimaryActionButton(
                text = "Export Coverage Report",
                onClick = { viewModel.onEvent(SyllabusCoverageEvent.OnExportReport) },
                containerColor = Color.Transparent,
                contentColor = CustomTheme.colors.primaryText,
                height = 50.dp
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.FileDownload,
                        contentDescription = null,
                        tint = CustomTheme.colors.primaryText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Export Coverage Report",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                }
            }
        }
    }
}

@Composable
private fun Indicator(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, fontSize = 12.sp, color = CustomTheme.colors.secondaryText)
    }
}
