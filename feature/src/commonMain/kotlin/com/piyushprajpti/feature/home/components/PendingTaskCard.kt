package com.piyushprajpti.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
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
import com.piyushprajpti.feature.home.root.PendingTask
import com.piyushprajpti.feature.home.root.PendingTaskType

@Composable
fun PendingTaskCard(
    task: PendingTask,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = when (task.taskType) {
        PendingTaskType.UNGRADED_ASSIGNMENTS -> Icons.Outlined.Assignment
        PendingTaskType.LEAVE_APPLICATIONS -> Icons.Outlined.AssignmentTurnedIn
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(0.5.dp, CustomTheme.colors.outline, RoundedCornerShape(50.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CustomTheme.colors.primary,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = "${task.count} ${task.title}",
            color = CustomTheme.colors.primaryText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
