package com.piyushprajpti.feature.result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.result.StudentResult

@Composable
fun TopStudentCard(
    student: StudentResult,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.secondaryBackground, RoundedCornerShape(20.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .background(CustomTheme.colors.primary.copy(alpha = 0.2f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = student.name.take(1),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = student.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = "ID: ${student.id}",
                fontSize = 12.sp,
                color = CustomTheme.colors.secondaryText
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${student.percentage}%",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary
            )
            Text(
                text = "Rank #${student.rank}",
                fontSize = 10.sp,
                color = CustomTheme.colors.success
            )
        }
    }
}
