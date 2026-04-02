package com.piyushprajpti.feature.my_leave.balance.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.my_leave.balance.LeaveBalance

@Composable
fun LeaveBalanceCard(
    balance: LeaveBalance,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp)
    ) {
        Column {
            Text(
                text = balance.type,
                fontSize = 14.sp,
                color = CustomTheme.colors.secondaryText,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = balance.consumed.toString(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = balance.color
                )
                Text(
                    text = " / ${balance.total}",
                    fontSize = 18.sp,
                    color = CustomTheme.colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { if (balance.total > 0) balance.consumed.toFloat() / balance.total else 0f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(10.dp)),
                color = balance.color,
                trackColor = balance.color.copy(alpha = 0.1f),
                strokeCap = StrokeCap.Round
            )
        }
    }
}
