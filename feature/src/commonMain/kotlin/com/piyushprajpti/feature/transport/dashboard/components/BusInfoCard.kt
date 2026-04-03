package com.piyushprajpti.feature.transport.dashboard.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.DirectionsBus
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

@Composable
fun BusInfoCard(
    busName: String,
    routeNumber: String,
    busNumber: String,
    pickupPoint: String,
    scheduledTime: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CustomTheme.colors.primary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary,
                    modifier = Modifier.size(35.dp)
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "MY BUS",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary
                )
                Text(
                    text = busName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Text(
                    text = routeNumber,
                    fontSize = 14.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(CustomTheme.colors.primary.copy(alpha = 0.1f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "ACTIVE",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Pickup Point",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.tertiaryText
                )
                Text(
                    text = pickupPoint,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.primaryText
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Scheduled Time",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.tertiaryText
                )
                Text(
                    text = scheduledTime,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.primaryText
                )
            }
        }
    }
}
