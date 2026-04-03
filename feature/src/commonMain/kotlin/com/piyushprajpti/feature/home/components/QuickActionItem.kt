package com.piyushprajpti.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.home.root.QuickAction

@Composable
fun QuickActionsGrid(
    actions: List<QuickAction>,
    onClick: (QuickAction) -> Unit
) {
    val chunks = actions.chunked(4)
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        chunks.forEach { rowActions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowActions.forEach { action ->
                    QuickActionItem(action, onClick = { onClick(action) })
                }
                if (rowActions.size < 4) {
                    repeat(4 - rowActions.size) {
                        Spacer(modifier = Modifier.width(70.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun QuickActionItem(
    action: QuickAction,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.width(70.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(CustomTheme.colors.secondaryBackground)
                .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(16.dp))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = action.icon,
                contentDescription = action.title,
                tint = CustomTheme.colors.primaryText,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = action.title,
            color = CustomTheme.colors.secondaryText,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}
