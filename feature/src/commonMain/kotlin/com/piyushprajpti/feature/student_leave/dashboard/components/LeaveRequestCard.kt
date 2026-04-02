package com.piyushprajpti.feature.student_leave.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.student_leave.dashboard.LeaveRequest
import com.piyushprajpti.feature.student_leave.dashboard.LeaveStatus

@Composable
fun LeaveRequestCard(
    leave: LeaveRequest,
    onApprove: () -> Unit,
    onReject: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(20.dp))
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = leave.studentImage,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1.0f)) {
                Text(
                    text = leave.studentName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Text(
                    text = "ROLL: ${leave.rollNo} • ${leave.semester}",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }

            if (leave.isNew && leave.status == LeaveStatus.PENDING) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(CustomTheme.colors.primary.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "NEW",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = CustomTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "${leave.leaveType} | ${leave.startDate} - ${leave.endDate} (${leave.duration})",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = CustomTheme.colors.primary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "\"${leave.reason}\"",
            fontSize = 14.sp,
            color = CustomTheme.colors.secondaryText,
            lineHeight = 20.sp
        )

        leave.attachment?.let { attachment ->
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(CustomTheme.colors.primaryBackground)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(CustomTheme.colors.primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        tint = CustomTheme.colors.primary
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = attachment.fileName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CustomTheme.colors.primaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${attachment.fileSize} • ${attachment.fileType}",
                        fontSize = 11.sp,
                        color = CustomTheme.colors.secondaryText
                    )
                }

                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "View",
                    tint = CustomTheme.colors.secondaryText,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        if (leave.status == LeaveStatus.PENDING) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = onReject,
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = CustomTheme.colors.primary
                    ),
                    border = BorderStroke(1.dp, CustomTheme.colors.primary)
                ) {
                    Text("Reject", fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.width(15.dp))

                Button(
                    onClick = onApprove,
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomTheme.colors.success,
                        contentColor = CustomTheme.colors.white
                    )
                ) {
                    Text("Approve", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}