package com.piyushprajpti.feature.assignment.submission.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Grading
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.PrimaryActionButton

@Composable
fun AssignmentHeader(
    subject: String,
    unit: String,
    title: String,
    dueDate: String,
    onGradeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CustomTheme.colors.secondaryBackground,
                        CustomTheme.colors.secondaryBackground.copy(alpha = 0.8f)
                    )
                )
            )
            .padding(20.dp)
    ) {
        Text(
            text = "$subject • $unit",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText,
            lineHeight = 28.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                tint = CustomTheme.colors.secondaryText,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Due: $dueDate",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = CustomTheme.colors.secondaryText
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryActionButton(
                text = "Grade All",
                onClick = onGradeAllClick,
                modifier = Modifier.weight(1f),
                height = 48.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Grading,
                        contentDescription = null,
                        tint = CustomTheme.colors.white,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Grade All",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CustomTheme.colors.white
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CustomTheme.colors.outline.copy(alpha = 0.3f))
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = CustomTheme.colors.primaryText,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
