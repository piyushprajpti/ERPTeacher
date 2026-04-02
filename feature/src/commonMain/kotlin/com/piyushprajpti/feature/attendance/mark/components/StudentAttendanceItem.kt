package com.piyushprajpti.feature.attendance.mark.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.attendance.mark.AttendanceStatus
import com.piyushprajpti.feature.attendance.mark.StudentAttendance

@Composable
fun StudentAttendanceItem(
    student: StudentAttendance,
    onStatusChange: (AttendanceStatus) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (student.avatarUrl != null) {
            AsyncImage(
                model = student.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(CustomTheme.colors.secondaryBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = student.name.split(" ").mapNotNull { it.firstOrNull() }.joinToString("").take(2).uppercase(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
            }
        }

        Spacer(modifier = Modifier.width(15.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = student.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = student.rollNo,
                fontSize = 12.sp,
                color = CustomTheme.colors.secondaryText
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusButton(
                text = "P",
                isSelected = student.status == AttendanceStatus.PRESENT,
                activeColor = CustomTheme.colors.success,
                onClick = { onStatusChange(AttendanceStatus.PRESENT) }
            )
            StatusButton(
                text = "L",
                isSelected = student.status == AttendanceStatus.LATE,
                activeColor = CustomTheme.colors.warning,
                onClick = { onStatusChange(AttendanceStatus.LATE) }
            )
            StatusButton(
                text = "A",
                isSelected = student.status == AttendanceStatus.ABSENT,
                activeColor = CustomTheme.colors.error,
                onClick = { onStatusChange(AttendanceStatus.ABSENT) }
            )
        }
    }
}

@Composable
private fun StatusButton(
    text: String,
    isSelected: Boolean,
    activeColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(if (isSelected) activeColor else Color.Transparent)
            .border(
                width = 1.dp,
                color = if (isSelected) activeColor else CustomTheme.colors.outline,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.secondaryText
        )
    }
}
