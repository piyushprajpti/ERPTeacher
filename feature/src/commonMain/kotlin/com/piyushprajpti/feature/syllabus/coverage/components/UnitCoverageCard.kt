package com.piyushprajpti.feature.syllabus.coverage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.syllabus.coverage.UnitCoverage
import com.piyushprajpti.feature.syllabus.coverage.UnitStatus

@Composable
fun UnitCoverageCard(
    unit: UnitCoverage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val statusColor = when (unit.status) {
        UnitStatus.COMPLETED -> CustomTheme.colors.success
        UnitStatus.IN_PROGRESS -> CustomTheme.colors.orange
        UnitStatus.LAGGING -> CustomTheme.colors.error
    }

    val icon = when (unit.status) {
        UnitStatus.COMPLETED -> Icons.Default.Check
        UnitStatus.IN_PROGRESS -> Icons.Default.MoreHoriz
        UnitStatus.LAGGING -> Icons.Default.PriorityHigh
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, statusColor.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(statusColor.copy(alpha = 0.1f))
                    .border(1.dp, statusColor.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = statusColor,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Unit ${unit.unitNumber}: ${unit.title}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    Text(
                        text = unit.status.name.replace("_", " "),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = statusColor
                    )
                }
                
                // Added subject and course name as requested
                Text(
                    text = "${unit.subjectName} • ${unit.courseName}",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }
        }

        if (unit.status == UnitStatus.IN_PROGRESS) {
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { unit.topicsCovered.toFloat() / unit.totalTopics },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = statusColor,
                trackColor = CustomTheme.colors.outline,
                strokeCap = StrokeCap.Round
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${unit.topicsCovered} / ${unit.totalTopics} topics covered" + 
                       (unit.dueDateText?.let { " • $it" } ?: ""),
                fontSize = 12.sp,
                color = CustomTheme.colors.tertiaryText
            )
        }
    }
}
