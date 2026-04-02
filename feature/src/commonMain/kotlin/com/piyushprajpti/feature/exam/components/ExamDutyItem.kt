package com.piyushprajpti.feature.exam.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.exam.ExamDuty
import com.piyushprajpti.feature.exam.ExamStatus

@Composable
fun ExamDutyItem(duty: ExamDuty) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExamTimelineIndicator(status = duty.status)

        ExamDutyCard(
            duty = duty,
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
fun ExamTimelineIndicator(status: ExamStatus) {
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
                        ExamStatus.COMPLETED -> CustomTheme.colors.secondaryBackground
                        ExamStatus.ONGOING -> CustomTheme.colors.primary.copy(alpha = 0.2f)
                        ExamStatus.UPCOMING -> CustomTheme.colors.secondaryBackground
                    }
                )
                .border(
                    width = 2.dp,
                    color = when (status) {
                        ExamStatus.COMPLETED -> CustomTheme.colors.secondaryText
                        ExamStatus.ONGOING -> CustomTheme.colors.primary
                        ExamStatus.UPCOMING -> CustomTheme.colors.secondaryText.copy(alpha = 0.3f)
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            when (status) {
                ExamStatus.COMPLETED -> Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = CustomTheme.colors.secondaryText,
                    modifier = Modifier.size(16.dp)
                )

                ExamStatus.ONGOING -> Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(CustomTheme.colors.primary)
                )

                ExamStatus.UPCOMING -> {}
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
fun ExamDutyCard(
    duty: ExamDuty,
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
                text = "${duty.startTime} - ${duty.endTime}",
                color = CustomTheme.colors.secondaryText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            ExamStatusBadge(status = duty.status)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = duty.subject,
            color = CustomTheme.colors.primaryText,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = duty.examName,
            color = CustomTheme.colors.secondaryText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CustomTheme.colors.primaryBackground.copy(alpha = 0.5f))
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Outlined.AccountBalance,
                        contentDescription = null,
                        tint = CustomTheme.colors.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "ROOM / BLOCK",
                            color = CustomTheme.colors.tertiaryText,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${duty.roomNumber} (${duty.block})",
                            color = CustomTheme.colors.primaryText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "STUDENTS",
                        color = CustomTheme.colors.tertiaryText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = duty.studentCount.toString(),
                        color = CustomTheme.colors.primaryText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun ExamStatusBadge(status: ExamStatus) {
    val color = when (status) {
        ExamStatus.COMPLETED -> CustomTheme.colors.secondaryText
        ExamStatus.ONGOING -> CustomTheme.colors.success
        ExamStatus.UPCOMING -> CustomTheme.colors.tertiaryText
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color.copy(alpha = 0.1f))
            .border(1.dp, color.copy(alpha = 0.2f), RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = status.name,
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
