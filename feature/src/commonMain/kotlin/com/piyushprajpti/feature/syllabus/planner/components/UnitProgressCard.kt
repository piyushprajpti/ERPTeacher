package com.piyushprajpti.feature.syllabus.planner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import com.piyushprajpti.feature.syllabus.planner.UnitInfo

@Composable
fun UnitProgressCard(
    unit: UnitInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(80.dp)
        ) {
            CircularProgressIndicator(
                progress = { 1f },
                modifier = Modifier.size(80.dp),
                color = CustomTheme.colors.outline,
                strokeWidth = 8.dp,
                strokeCap = StrokeCap.Round
            )
            CircularProgressIndicator(
                progress = { unit.progress },
                modifier = Modifier.size(80.dp),
                color = CustomTheme.colors.primary,
                strokeWidth = 8.dp,
                strokeCap = StrokeCap.Round
            )
            Text(
                text = "${(unit.progress * 100).toInt()}%",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "UNIT ${unit.number}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(CustomTheme.colors.primary.copy(alpha = 0.1f))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = null,
                    tint = CustomTheme.colors.success,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = unit.status,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CustomTheme.colors.success
                )
            }

            Text(
                text = unit.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )

            Text(
                text = "Target completion: ${unit.targetCompletion}",
                fontSize = 12.sp,
                color = CustomTheme.colors.secondaryText
            )
        }
    }
}
