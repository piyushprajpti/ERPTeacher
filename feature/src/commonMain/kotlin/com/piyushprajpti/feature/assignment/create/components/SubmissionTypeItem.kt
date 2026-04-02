package com.piyushprajpti.feature.assignment.create.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun SubmissionTypeItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        color = if (isSelected) CustomTheme.colors.primary.copy(alpha = 0.1f) else CustomTheme.colors.secondaryBackground,
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.outline
        )
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryText,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryText,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}