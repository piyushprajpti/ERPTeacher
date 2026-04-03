package com.piyushprajpti.feature.hostel.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.hostel.dashboard.models.StayDetails

@Composable
fun StayDetailsCard(
    stayDetails: StayDetails,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .border(1.dp, CustomTheme.colors.primary.copy(alpha = 0.5f), RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground.copy(alpha = 0.5f), RoundedCornerShape(25.dp))
            .padding(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(CustomTheme.colors.primary.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "ACTIVE STAY",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary
                    )
                }
                Text(
                    text = stayDetails.sharingType,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Room ${stayDetails.roomNumber}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stayDetails.blockName,
                    fontSize = 14.sp,
                    color = CustomTheme.colors.secondaryText
                )
                Text(
                    text = " | ",
                    fontSize = 14.sp,
                    color = CustomTheme.colors.tertiaryText
                )
                Text(
                    text = stayDetails.floor,
                    fontSize = 14.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "ROOMMATES",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.tertiaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        stayDetails.roommates.forEachIndexed { index, roommate ->
                            AsyncImage(
                                model = roommate.imageUrl,
                                contentDescription = roommate.name,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, CustomTheme.colors.secondaryBackground, CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            if (index < stayDetails.roommates.size - 1) {
                                Spacer(modifier = Modifier.width((-8).dp))
                            }
                        }
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "FEE STATUS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.tertiaryText
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = CustomTheme.colors.success,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stayDetails.feeStatus,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = CustomTheme.colors.success
                        )
                    }
                    stayDetails.nextPaymentDate?.let {
                        Text(
                            text = "Next: $it",
                            fontSize = 10.sp,
                            color = CustomTheme.colors.secondaryText
                        )
                    }
                }
            }
        }
    }
}
