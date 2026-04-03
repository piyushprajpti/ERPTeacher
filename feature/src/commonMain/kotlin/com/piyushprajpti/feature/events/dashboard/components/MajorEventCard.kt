package com.piyushprajpti.feature.events.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.events.dashboard.MajorEvent

@Composable
fun MajorEventCard(
    event: MajorEvent,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4A344E),
                        Color(0xFF2D1F2E)
                    )
                )
            )
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "NEXT MAJOR EVENT",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.white
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = CustomTheme.colors.white.copy(alpha = 0.7f),
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${event.daysAway} days away • ${event.dateRange}",
                    fontSize = 14.sp,
                    color = CustomTheme.colors.white.copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomTheme.colors.primary,
                    contentColor = CustomTheme.colors.white
                ),
                shape = RoundedCornerShape(15.dp),
                contentPadding = PaddingValues(horizontal = 25.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "View Details",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        // Abstract decoration (simplified)
        Icon(
            imageVector = Icons.Default.Info, // Placeholder for the SVG in image
            contentDescription = null,
            tint = CustomTheme.colors.white.copy(alpha = 0.1f),
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 20.dp, y = 20.dp)
        )
    }
}
