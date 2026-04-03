package com.piyushprajpti.feature.hostel.complaints.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.hostel.complaints.models.Complaint
import com.piyushprajpti.feature.hostel.complaints.models.ComplaintStatus

@Composable
fun RecentActivityCard(
    complaints: List<Complaint>,
    onRate: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = "RECENT ACTIVITY",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.tertiaryText,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        complaints.forEach { complaint ->
            ActivityItem(
                complaint = complaint,
                onRate = onRate
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
private fun ActivityItem(
    complaint: Complaint,
    onRate: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(CustomTheme.colors.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Build,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = complaint.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Text(
                    text = "${complaint.category} • ${complaint.date}",
                    fontSize = 14.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }

            StatusTag(
                status = complaint.statusText,
                color = if (complaint.status == ComplaintStatus.RESOLVED) CustomTheme.colors.success else CustomTheme.colors.primary
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (complaint.status == ComplaintStatus.RESOLVED) {
            ResolvedSection(
                rating = complaint.rating ?: 0,
                onRate = { onRate(complaint.id, it) }
            )
        } else {
            TimelineSection(progress = complaint.progress)
        }
    }
}

@Composable
private fun StatusTag(status: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = status,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Composable
private fun TimelineSection(progress: Float) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(CustomTheme.colors.outline.copy(alpha = 0.5f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(2.dp)
                    .background(CustomTheme.colors.primary)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TimelineDot(active = true)
                TimelineDot(active = progress >= 0.5f)
                TimelineDot(active = progress >= 1f)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TimelineText("Submitted", active = true)
            TimelineText("Assigned", active = progress >= 0.5f)
            TimelineText("Resolved", active = progress >= 1f)
        }
    }
}

@Composable
private fun TimelineDot(active: Boolean) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(if (active) CustomTheme.colors.primary else CustomTheme.colors.outline)
            .offset(y = (-4).dp)
    )
}

@Composable
private fun TimelineText(text: String, active: Boolean) {
    Text(
        text = text,
        fontSize = 10.sp,
        fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
        color = if (active) CustomTheme.colors.primaryText else CustomTheme.colors.tertiaryText
    )
}

@Composable
private fun ResolvedSection(rating: Int, onRate: (Int) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(CustomTheme.colors.outline.copy(alpha = 0.5f))
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Rate the service:",
            fontSize = 12.sp,
            color = CustomTheme.colors.secondaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            repeat(5) { index ->
                val isSelected = index < rating
                Icon(
                    imageVector = if (isSelected) Icons.Default.Star else Icons.Outlined.StarBorder,
                    contentDescription = null,
                    tint = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.tertiaryText,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onRate(index + 1) }
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}
