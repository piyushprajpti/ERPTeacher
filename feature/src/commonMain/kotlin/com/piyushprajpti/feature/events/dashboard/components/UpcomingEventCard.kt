package com.piyushprajpti.feature.events.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.events.dashboard.UpcomingEvent

@Composable
fun UpcomingEventCard(
    event: UpcomingEvent,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .clickable { onClick() }
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Date Box
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(CustomTheme.colors.outline),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = event.month,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = event.categoryColor(CustomTheme.colors)
                )
                Text(
                    text = event.date,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = CustomTheme.colors.primaryText
                )
            }
        }

        Spacer(modifier = Modifier.width(15.dp))

        // Event Details
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = event.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${event.time} • ${event.location}",
                fontSize = 13.sp,
                color = CustomTheme.colors.secondaryText
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = event.category,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = event.categoryColor(CustomTheme.colors)
            )
        }

        // Action Icon
        Icon(
            imageVector = Icons.Outlined.AddBox,
            contentDescription = "Add to Calendar",
            tint = CustomTheme.colors.tertiaryText,
            modifier = Modifier.size(24.dp)
        )
    }
}
