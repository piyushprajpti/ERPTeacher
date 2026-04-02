package com.piyushprajpti.feature.attendance.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.HourglassEmpty
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
import com.piyushprajpti.feature.attendance.dashboard.ClassItem
import com.piyushprajpti.feature.attendance.dashboard.ClassStatus

@Composable
fun ClassItemCard(
    classItem: ClassItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .clickable { onClick() }
    ) {
        if (classItem.status == ClassStatus.ONGOING) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(60.dp) // Approximate height for the indicator
                    .align(Alignment.CenterStart)
                    .background(CustomTheme.colors.primary)
            )
        }

        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CustomTheme.colors.primaryBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (classItem.status) {
                        ClassStatus.COMPLETED -> Icons.Default.CheckCircle
                        ClassStatus.ONGOING -> Icons.Default.HourglassEmpty
                        ClassStatus.UPCOMING -> Icons.Default.History
                    },
                    contentDescription = null,
                    tint = when (classItem.status) {
                        ClassStatus.COMPLETED -> CustomTheme.colors.success
                        ClassStatus.ONGOING -> CustomTheme.colors.primary
                        ClassStatus.UPCOMING -> CustomTheme.colors.secondaryText
                    },
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = classItem.subject,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${classItem.startTime} - ${classItem.endTime} • ${classItem.room}",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }

            Text(
                text = classItem.status.name,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = when (classItem.status) {
                    ClassStatus.COMPLETED -> CustomTheme.colors.success
                    ClassStatus.ONGOING -> CustomTheme.colors.primary
                    ClassStatus.UPCOMING -> CustomTheme.colors.secondaryText
                }
            )
        }
    }
}
