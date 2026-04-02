package com.piyushprajpti.feature.assignment.create.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
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
fun SectionHeader(
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CustomTheme.colors.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title.uppercase(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.secondaryText,
            letterSpacing = 1.sp
        )
    }
}