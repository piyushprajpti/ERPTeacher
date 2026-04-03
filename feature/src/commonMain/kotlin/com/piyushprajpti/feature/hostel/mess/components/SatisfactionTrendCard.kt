package com.piyushprajpti.feature.hostel.mess.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.piyushprajpti.feature.hostel.mess.models.SatisfactionData

@Composable
fun SatisfactionTrendCard(
    data: List<SatisfactionData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(25.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Weekly Satisfaction Trend",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = "Last 7 Days",
                fontSize = 12.sp,
                color = CustomTheme.colors.tertiaryText
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            data.forEach { trend ->
                val barHeight = (trend.rating / 5f) * 120 // Scale to 120dp max
                val isToday = trend.day == "F" // Assuming F is today as per image highlight

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    if (isToday) {
                        Text(
                            text = trend.rating.toString(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.primary,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(30.dp)
                            .height(barHeight.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(
                                if (isToday) CustomTheme.colors.primary 
                                else if (trend.rating < 2) Color(0xFF37474F) 
                                else CustomTheme.colors.primary.copy(alpha = 0.2f)
                            )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = trend.day,
                        fontSize = 10.sp,
                        color = if (isToday) CustomTheme.colors.primary else CustomTheme.colors.tertiaryText
                    )
                }
            }
        }
    }
}
