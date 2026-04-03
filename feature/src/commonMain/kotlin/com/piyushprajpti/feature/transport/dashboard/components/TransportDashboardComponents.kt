package com.piyushprajpti.feature.transport.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.transport.dashboard.TimelineItem

@Composable
fun MapPlaceholder(
    currentLocation: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
    ) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1524661135-423995f22d0b?auto=format&fit=crop&q=80&w=1000",
            contentDescription = "Map",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(45.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.primary)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.DirectionsBus,
                contentDescription = null,
                tint = Color.White
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CustomTheme.colors.black.copy(alpha = 0.6f))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Column {
                Text(
                    text = "Current Location",
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = currentLocation,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun EtaCard(eta: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, CustomTheme.colors.primary.copy(alpha = 0.3f), RoundedCornerShape(15.dp))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "ETA to My Stop",
                fontSize = 14.sp,
                color = CustomTheme.colors.secondaryText
            )
            Text(
                text = eta,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = CustomTheme.colors.primary
            )
        }
        Icon(
            imageVector = Icons.Default.HourglassBottom,
            contentDescription = null,
            tint = CustomTheme.colors.primary,
            modifier = Modifier.size(35.dp)
        )
    }
}

@Composable
fun RouteTimeline(timeline: List<TimelineItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Route Timeline",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        timeline.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(40.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(
                                if (item.isCurrent) CustomTheme.colors.primary 
                                else if (item.isPast) CustomTheme.colors.blue.copy(alpha = 0.5f)
                                else CustomTheme.colors.tertiaryText.copy(alpha = 0.3f)
                            )
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (item.isCurrent) {
                            Box(modifier = Modifier.fillMaxSize().clip(CircleShape).background(Color.White))
                        }
                    }
                    
                    if (index < timeline.size - 1) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .weight(1f)
                                .background(CustomTheme.colors.outline)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 30.dp)
                ) {
                    Text(
                        text = item.title,
                        fontSize = 15.sp,
                        fontWeight = if (item.isPickupPoint) FontWeight.Bold else FontWeight.Medium,
                        color = if (item.isPickupPoint) CustomTheme.colors.primary else CustomTheme.colors.primaryText
                    )
                    if (item.isPickupPoint) {
                        Text(
                            text = "Your Pickup Point",
                            fontSize = 11.sp,
                            color = CustomTheme.colors.primary.copy(alpha = 0.7f)
                        )
                    }
                }

                Text(
                    text = item.time,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (item.isCurrent) CustomTheme.colors.primary else CustomTheme.colors.secondaryText
                )
            }
        }
    }
}

@Composable
fun FeeSupportSection(
    feeStatus: String,
    validUntil: String,
    onSupportClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(15.dp))
                .background(CustomTheme.colors.secondaryBackground)
                .padding(15.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = CustomTheme.colors.success,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "FEE STATUS",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.tertiaryText
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feeStatus,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = "Valid $validUntil",
                fontSize = 11.sp,
                color = CustomTheme.colors.secondaryText
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(15.dp))
                .background(CustomTheme.colors.secondaryBackground)
                .clickable { onSupportClick() }
                .padding(15.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.SupportAgent,
                    contentDescription = null,
                    tint = CustomTheme.colors.orange,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "SUPPORT",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.tertiaryText
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Transport Office",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
fun DriverCard(
    name: String,
    image: String,
    onCallClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Driver",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Bus Driver",
                fontSize = 12.sp,
                color = CustomTheme.colors.secondaryText
            )
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
        }

        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.primary)
                .clickable { onCallClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
