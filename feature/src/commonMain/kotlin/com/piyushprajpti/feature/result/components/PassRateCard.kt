package com.piyushprajpti.feature.result.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun PassRateCard(
    passRate: String,
    growth: String,
    vsPrevious: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CustomTheme.colors.primary.copy(alpha = 0.15f),
                        CustomTheme.colors.primary.copy(alpha = 0.05f)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .border(1.dp, CustomTheme.colors.primary.copy(alpha = 0.2f), RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "OVERALL PASS RATE",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = CustomTheme.colors.secondaryText,
                letterSpacing = 1.sp
            )
            Text(
                text = passRate,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary
            )
            Row(
                modifier = Modifier
                    .background(CustomTheme.colors.success.copy(alpha = 0.1f), RoundedCornerShape(15.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                    contentDescription = null,
                    tint = CustomTheme.colors.success,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$growth vs $vsPrevious",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.success
                )
            }
        }
    }
}
