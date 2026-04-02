package com.piyushprajpti.feature.assignment.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun VisibilityToggleItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) CustomTheme.colors.primary else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.secondaryText
    )
}