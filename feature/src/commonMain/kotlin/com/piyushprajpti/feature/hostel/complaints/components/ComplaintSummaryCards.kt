package com.piyushprajpti.feature.hostel.complaints.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun ComplaintSummaryCards(
    activeCount: Int,
    resolvedCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        SummaryCard(
            modifier = Modifier.weight(1f),
            label = "ACTIVE",
            count = activeCount.toString().padStart(2, '0'),
            countColor = CustomTheme.colors.primary
        )
        SummaryCard(
            modifier = Modifier.weight(1f),
            label = "RESOLVED",
            count = resolvedCount.toString().padStart(2, '0'),
            countColor = CustomTheme.colors.success
        )
    }
}

@Composable
private fun SummaryCard(
    modifier: Modifier = Modifier,
    label: String,
    count: String,
    countColor: Color
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp)
    ) {
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.tertiaryText
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = count,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = countColor
        )
    }
}
