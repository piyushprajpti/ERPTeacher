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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.result.AtRiskStudent

@Composable
fun AtRiskCard(
    count: Int,
    students: List<AtRiskStudent>,
    onAlertClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.secondaryBackground, RoundedCornerShape(20.dp))
            .border(1.dp, CustomTheme.colors.error.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
            .padding(15.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = CustomTheme.colors.error,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "AT-RISK STUDENTS ($count)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.error,
                letterSpacing = 1.sp
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Row {
            students.take(3).forEachIndexed { index, student ->
                AsyncImage(
                    model = student.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(2.dp, CustomTheme.colors.secondaryBackground, CircleShape),
                    contentScale = ContentScale.Crop
                )
                if (index < students.take(3).size - 1) {
                    Spacer(modifier = Modifier.width((-10).dp))
                }
            }
            if (students.size > 3) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(CustomTheme.colors.primary.copy(alpha = 0.2f), CircleShape)
                        .border(2.dp, CustomTheme.colors.secondaryBackground, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+${students.size - 3}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(
            text = "These students have scored below 40% in the last two internal assessments.",
            fontSize = 12.sp,
            color = CustomTheme.colors.secondaryText
        )
        
        Spacer(modifier = Modifier.height(15.dp))
        
        Button(
            onClick = onAlertClick,
            modifier = Modifier.fillMaxWidth().height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomTheme.colors.primary.copy(alpha = 0.1f),
                contentColor = CustomTheme.colors.primary
            ),
            shape = RoundedCornerShape(25.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, CustomTheme.colors.primary.copy(alpha = 0.3f))
        ) {
            Text(text = "Send Intervention Alert", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
