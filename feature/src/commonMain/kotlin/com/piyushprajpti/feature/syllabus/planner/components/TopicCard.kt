package com.piyushprajpti.feature.syllabus.planner.components

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
import com.piyushprajpti.components.CustomTextField
import com.piyushprajpti.feature.syllabus.planner.TopicInfo
import com.piyushprajpti.feature.syllabus.planner.TopicStatus

@Composable
fun TopicCard(
    topic: TopicInfo,
    onStatusChange: (TopicStatus) -> Unit,
    onNotesChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isCompleted = topic.status == TopicStatus.COVERED_EARLIER || topic.status == TopicStatus.COVERED_TODAY
    val statusColor = if (isCompleted) CustomTheme.colors.success else CustomTheme.colors.orange
    val icon = if (isCompleted) Icons.Default.Check else Icons.Default.MoreHoriz

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, statusColor.copy(alpha = 0.5f), RoundedCornerShape(25.dp))
            .padding(15.dp)
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
                        text = topic.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    if (topic.tag != null) {
                        Text(
                            text = topic.tag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.success,
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .background(CustomTheme.colors.success.copy(alpha = 0.1f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(
                    text = topic.subtitle,
                    fontSize = 13.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }
        }

        if (topic.quote != null) {
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .background(CustomTheme.colors.primaryBackground.copy(alpha = 0.3f))
                    .padding(15.dp)
            ) {
                Text(
                    text = "\"${topic.quote}\"",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.primaryText
                )
            }
        }

        if (topic.status == TopicStatus.COVERED_TODAY || topic.status == TopicStatus.PENDING) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "UPDATE STATUS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.secondaryText
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatusButton(
                    text = "Covered today",
                    isSelected = topic.status == TopicStatus.COVERED_TODAY,
                    onClick = { onStatusChange(TopicStatus.COVERED_TODAY) },
                    modifier = Modifier.weight(1f)
                )
                StatusButton(
                    text = "Covered earlier",
                    isSelected = topic.status == TopicStatus.COVERED_EARLIER,
                    onClick = { onStatusChange(TopicStatus.COVERED_EARLIER) },
                    modifier = Modifier.weight(1f)
                )
                StatusButton(
                    text = "Pending",
                    isSelected = topic.status == TopicStatus.PENDING,
                    onClick = { onStatusChange(TopicStatus.PENDING) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Teacher Notes",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.secondaryText
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                value = topic.notes,
                onValueChange = onNotesChange,
                placeholder = "Add teaching notes for this topic...",
                singleLine = false,
                textFieldModifier = Modifier.height(80.dp)
            )
        }
    }
}

@Composable
fun StatusButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(if (isSelected) CustomTheme.colors.primary.copy(alpha = 0.1f) else CustomTheme.colors.primaryBackground.copy(alpha = 0.3f))
            .border(1.dp, if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.outline, RoundedCornerShape(15.dp))
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) CustomTheme.colors.primaryText else CustomTheme.colors.secondaryText
        )
    }
}
