package com.piyushprajpti.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.outlined.LocationOn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.home.root.TeacherScheduleItem

@Composable
fun ScheduleItemCard(
    item: TeacherScheduleItem,
    onMarkAttendanceClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(0.5.dp, CustomTheme.colors.outline, RoundedCornerShape(24.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.subject,
                    color = CustomTheme.colors.primaryText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = CustomTheme.colors.secondaryText,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = item.room,
                        color = CustomTheme.colors.secondaryText,
                        fontSize = 14.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(CustomTheme.colors.primary.copy(alpha = 0.1f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = item.time,
                    color = CustomTheme.colors.primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(contentAlignment = Alignment.CenterStart) {
                    item.studentAvatars.take(3).forEachIndexed { index, url ->
                        AsyncImage(
                            model = url,
                            contentDescription = null,
                            modifier = Modifier
                                .offset(x = (index * 16).dp)
                                .size(32.dp)
                                .clip(CircleShape)
                                .border(2.dp, CustomTheme.colors.secondaryBackground, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    if (item.totalStudents > 0) {
                        Box(
                            modifier = Modifier
                                .offset(x = (minOf(item.studentAvatars.size, 3) * 16).dp)
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF2E3A59))
                                .border(2.dp, CustomTheme.colors.secondaryBackground, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+${item.totalStudents - minOf(item.studentAvatars.size, 3)}",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Button(
                onClick = onMarkAttendanceClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (item.isAttendanceMarked) CustomTheme.colors.primaryBackground else CustomTheme.colors.primary,
                    contentColor = if (item.isAttendanceMarked) CustomTheme.colors.secondaryText else CustomTheme.colors.white
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.height(36.dp),
                enabled = !item.isAttendanceMarked
            ) {
                Text(
                    text = if (item.isAttendanceMarked) "Attendance Taken" else "Take Attendance",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
