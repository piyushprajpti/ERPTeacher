package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.transport.tracking.TransportTrackingState

@Composable
fun DriverInfoCard(
    state: TransportTrackingState,
    onToggleAlert: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(CustomTheme.colors.secondaryBackground.copy(alpha = 0.9f))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Driver Info Row
        DriverInfoRow(
            driverName = state.driverName,
            driverRating = state.driverRating,
            driverImage = state.driverImage
        )

        // Bus Info Row
        BusInfoRow(
            busNumber = state.busNumber,
            currentSpeed = state.currentSpeed
        )

        // Alert Control Row
        AlertControlRow(
            isAlertEnabled = state.isAlertEnabled,
            onToggleAlert = onToggleAlert
        )
    }
}

@Composable
private fun DriverInfoRow(
    driverName: String,
    driverRating: String,
    driverImage: String
) {
    val uriHandler = LocalUriHandler.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = driverImage,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = driverName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = "DRIVER • $driverRating ⭐",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = CustomTheme.colors.primary
            )
        }

        IconButton(
            onClick = {
                uriHandler.openUri("tel:+918569214300")
            },
            modifier = Modifier
                .clip(CircleShape)
                .background(CustomTheme.colors.primary.copy(alpha = 0.1f))
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call Driver",
                tint = CustomTheme.colors.primary
            )
        }
    }
}

@Composable
private fun BusInfoRow(
    busNumber: String,
    currentSpeed: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        InfoBox(
            label = "BUS NUMBER",
            value = busNumber,
            modifier = Modifier.weight(1f)
        )
        InfoBox(
            label = "CURRENT SPEED",
            value = currentSpeed,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AlertControlRow(
    isAlertEnabled: Boolean,
    onToggleAlert: (Boolean) -> Unit
) {
    // Memoize the callback to prevent creation on each recomposition
    val handleToggle = remember(onToggleAlert) { { checked: Boolean -> onToggleAlert(checked) } }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.NotificationsActive,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Alert me when 5 mins away",
                fontSize = 15.sp,
                color = CustomTheme.colors.primaryText
            )
        }

        Switch(
            checked = isAlertEnabled,
            onCheckedChange = handleToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = CustomTheme.colors.white,
                checkedTrackColor = CustomTheme.colors.primary,
                uncheckedThumbColor = CustomTheme.colors.secondaryText,
                uncheckedTrackColor = CustomTheme.colors.outline
            )
        )
    }
}

@Composable
private fun InfoBox(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.primaryBackground.copy(alpha = 0.5f))
            .padding(15.dp)
    ) {
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.secondaryText
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = CustomTheme.colors.primaryText
        )
    }
}
