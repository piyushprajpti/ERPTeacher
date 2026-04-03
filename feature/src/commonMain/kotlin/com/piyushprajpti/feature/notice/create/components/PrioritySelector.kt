package com.piyushprajpti.feature.notice.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun PrioritySelector(
    selectedPriority: String,
    onPrioritySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val priorities = listOf("Normal", "Urgent")

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Priority",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = CustomTheme.colors.secondaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .background(CustomTheme.colors.secondaryBackground)
                .padding(4.dp)
        ) {
            priorities.forEach { priority ->
                val isSelected = selectedPriority == priority
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) CustomTheme.colors.outline else CustomTheme.colors.secondaryBackground)
                        .clickable { onPrioritySelected(priority) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = priority,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) CustomTheme.colors.primaryText else CustomTheme.colors.secondaryText
                    )
                }
            }
        }
    }
}