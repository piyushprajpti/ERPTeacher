package com.piyushprajpti.feature.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.LocalBottomBarPadding
import com.piyushprajpti.components.ScrollableDate
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val topBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomBarPadding = LocalBottomBarPadding.current

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .padding(top = topBarPadding)
    ) {
        ScrollableDate(
            selectedDate = state.selectedDate,
            startDate = state.startDate,
            endDate = state.endDate,
            onDateSelected = { viewModel.onEvent(ScheduleEvent.SelectDate(it)) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 24.dp,
                bottom = bottomBarPadding + 16.dp
            )
        ) {
            items(state.scheduleItems) { item ->
                ScheduleItemRow(item = item)
            }
        }
    }
}


@Composable
fun ScheduleItemRow(item: ScheduleItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TimelineIndicator(status = item.status)

        ScheduleCard(
            item = item,
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
fun TimelineIndicator(status: ScheduleStatus) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(32.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(
                    when (status) {
                        ScheduleStatus.COMPLETED -> CustomTheme.colors.secondaryBackground
                        ScheduleStatus.ONGOING -> CustomTheme.colors.primary.copy(alpha = 0.2f)
                        ScheduleStatus.UPCOMING -> CustomTheme.colors.secondaryBackground
                    }
                )
                .border(
                    width = 2.dp,
                    color = when (status) {
                        ScheduleStatus.COMPLETED -> CustomTheme.colors.secondaryText
                        ScheduleStatus.ONGOING -> CustomTheme.colors.primary
                        ScheduleStatus.UPCOMING -> CustomTheme.colors.secondaryText.copy(alpha = 0.3f)
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            when (status) {
                ScheduleStatus.COMPLETED -> Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = CustomTheme.colors.secondaryText,
                    modifier = Modifier.size(16.dp)
                )

                ScheduleStatus.ONGOING -> Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(CustomTheme.colors.primary)
                )

                ScheduleStatus.UPCOMING -> {}
            }
        }
        Box(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
                .background(CustomTheme.colors.outline.copy(alpha = 0.5f))
        )
    }
}

@Composable
fun ScheduleCard(
    item: ScheduleItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${item.startTime} - ${item.endTime}",
                color = CustomTheme.colors.secondaryText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            StatusBadge(status = item.status, isCancelled = item.isCancelled)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = item.subject,
            color = if (item.isCancelled) CustomTheme.colors.secondaryText else CustomTheme.colors.primaryText,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            textDecoration = if (item.isCancelled) TextDecoration.LineThrough else null
        )

        if (item.isFreePeriod) {
            Text(
                text = "No scheduled classes",
                color = CustomTheme.colors.secondaryText,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        } else if (item.isCancelled) {
            Text(
                text = "Class has been cancelled",
                color = CustomTheme.colors.error,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            if (item.status == ScheduleStatus.ONGOING) {
                OngoingSection(item)
            } else {
                UpcomingOrCompletedSection(item)
            }
        }
    }
}

@Composable
fun StatusBadge(status: ScheduleStatus, isCancelled: Boolean) {
    val text = if (isCancelled) "CANCELLED" else status.name
    val color = when {
        isCancelled -> CustomTheme.colors.error
        status == ScheduleStatus.COMPLETED -> CustomTheme.colors.secondaryText
        status == ScheduleStatus.ONGOING -> CustomTheme.colors.success
        else -> CustomTheme.colors.tertiaryText
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color.copy(alpha = 0.1f))
            .border(1.dp, color.copy(alpha = 0.2f), RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun OngoingSection(item: ScheduleItem) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CustomTheme.colors.primaryBackground.copy(alpha = 0.5f))
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.AccountBalance,
                            contentDescription = null,
                            tint = CustomTheme.colors.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "LOCATION",
                                color = CustomTheme.colors.tertiaryText,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = item.location,
                                color = CustomTheme.colors.primaryText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "CLASS/SECTION",
                        color = CustomTheme.colors.tertiaryText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.classSection,
                        color = CustomTheme.colors.primaryText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        item.progress?.let {
            LinearProgressIndicator(
                progress = { it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = CustomTheme.colors.primary,
                trackColor = CustomTheme.colors.outline,
                strokeCap = StrokeCap.Round
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.timeRemaining ?: "",
                    color = CustomTheme.colors.secondaryText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${(it * 100).toInt()}% ELAPSED",
                    color = CustomTheme.colors.secondaryText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun UpcomingOrCompletedSection(item: ScheduleItem) {
    Row(
        modifier = Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Groups,
                contentDescription = null,
                tint = CustomTheme.colors.secondaryText,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.classSection,
                color = CustomTheme.colors.secondaryText,
                fontSize = 13.sp
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = CustomTheme.colors.secondaryText,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.location,
                color = CustomTheme.colors.secondaryText,
                fontSize = 13.sp
            )
        }
    }
}
