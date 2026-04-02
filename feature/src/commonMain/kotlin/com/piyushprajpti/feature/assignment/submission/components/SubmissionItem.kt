package com.piyushprajpti.feature.assignment.submission.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.filled.Image
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
import com.piyushprajpti.feature.assignment.submission.AttachmentType
import com.piyushprajpti.feature.assignment.submission.Submission

@Composable
fun SubmissionItem(
    submission: Submission,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, CustomTheme.colors.outline.copy(alpha = 0.5f), RoundedCornerShape(25.dp))
            .clickable { onClick() }
            .padding(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar with initials
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(CustomTheme.colors.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                val initials = submission.studentName.split(" ")
                    .mapNotNull { it.firstOrNull() }
                    .joinToString("")
                    .take(2)
                
                Text(
                    text = initials,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = submission.studentName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Text(
                    text = "Roll No: ${submission.rollNo}${if (submission.submittedAt != null) " • ${submission.submittedAt}" else " • Not Submitted"}",
                    fontSize = 13.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }

            if (submission.hasAttachment) {
                Icon(
                    imageVector = if (submission.attachmentType == AttachmentType.Document) 
                        Icons.AutoMirrored.Filled.InsertDriveFile else Icons.Default.Image,
                    contentDescription = null,
                    tint = CustomTheme.colors.secondaryText,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .border(1.dp, CustomTheme.colors.outline, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = submission.score?.toString() ?: "--",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (submission.score != null) CustomTheme.colors.primaryText else CustomTheme.colors.secondaryText
                    )
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = "/${submission.totalScore}",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }
        }

        if (submission.isLate) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 5.dp, y = (-10).dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(CustomTheme.colors.error.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "LATE",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.error
                )
            }
        }
    }
}
