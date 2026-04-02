package com.piyushprajpti.feature.my_leave.balance.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
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
import com.piyushprajpti.feature.my_leave.balance.UpcomingLeave

@Composable
fun UpcomingLeaveCard(
    leave: UpcomingLeave,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(240.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(leave.color.copy(alpha = 0.2f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = leave.type,
                        fontSize = 12.sp,
                        color = leave.color,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = CustomTheme.colors.tertiaryText,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = leave.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${leave.startDate} - ${leave.endDate}",
                fontSize = 14.sp,
                color = CustomTheme.colors.secondaryText
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = leave.status,
                fontSize = 12.sp,
                color = CustomTheme.colors.tertiaryText
            )
        }
    }
}
