package com.piyushprajpti.feature.assignment.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.assignment.list.Assignment

@Composable
fun AssignmentCard(
    assignment: Assignment,
    onSubmissionsClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${assignment.subject} • ${assignment.unit}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary
            )
            
            Text(
                text = "${assignment.course} | ${assignment.className}",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = CustomTheme.colors.tertiaryText
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = assignment.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                tint = CustomTheme.colors.secondaryText,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Due: ${assignment.dueDate}, ${assignment.dueTime}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = CustomTheme.colors.secondaryText
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onSubmissionsClick,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomTheme.colors.primary,
                    contentColor = CustomTheme.colors.white
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "View Submissions",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            IconButton(
                onClick = onShareClick,
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CustomTheme.colors.outline.copy(alpha = 0.1f)),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = CustomTheme.colors.primaryText
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}
