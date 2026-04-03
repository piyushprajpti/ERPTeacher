package com.piyushprajpti.feature.hostel.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.piyushprajpti.feature.hostel.dashboard.models.WardenDetails

@Composable
fun WardenContactCard(
    warden: WardenDetails,
    onCallClick: (String) -> Unit,
    onMessageClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = warden.imageUrl,
            contentDescription = warden.name,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.width(15.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "HOSTEL WARDEN",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary
            )
            Text(
                text = warden.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = warden.phoneNumber,
                fontSize = 12.sp,
                color = CustomTheme.colors.secondaryText
            )
        }
        
        Row {
            IconButton(
                onClick = { onCallClick(warden.phoneNumber) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CustomTheme.colors.primary.copy(alpha = 0.8f))
            ) {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = "Call",
                    tint = CustomTheme.colors.white,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(10.dp))
            
            IconButton(
                onClick = { onMessageClick(warden.phoneNumber) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CustomTheme.colors.outline.copy(alpha = 0.5f))
            ) {
                Icon(
                    imageVector = Icons.Outlined.Message,
                    contentDescription = "Message",
                    tint = CustomTheme.colors.primaryText,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
