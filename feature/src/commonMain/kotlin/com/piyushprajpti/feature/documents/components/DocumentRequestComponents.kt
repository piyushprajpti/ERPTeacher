package com.piyushprajpti.feature.documents.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.documents.request.RecentRequest
import com.piyushprajpti.feature.documents.request.RequestStatus
import com.piyushprajpti.feature.documents.request.Urgency

@Composable
fun InfoCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, CustomTheme.colors.primary.copy(0.2f), RoundedCornerShape(15.dp))
            .padding(15.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = CustomTheme.colors.primary,
            modifier = Modifier.size(24.dp)
        )
        Column {
            Text(
                text = "Expected Delivery",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = "Your documents will be processed within 3-5 working days.",
                fontSize = 13.sp,
                color = CustomTheme.colors.secondaryText
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = CustomTheme.colors.primary
    )
}

@Composable
fun Counter(
    value: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButtonContainer(
            icon = Icons.Default.Remove,
            onClick = onDecrement,
            enabled = value > 1
        )
        Text(
            text = value.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )
        IconButtonContainer(
            icon = Icons.Default.Add,
            onClick = onIncrement,
            containerColor = CustomTheme.colors.primary
        )
    }
}

@Composable
fun IconButtonContainer(
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true,
    containerColor: Color = CustomTheme.colors.outline
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .background(if (enabled) containerColor else containerColor.copy(0.5f))
            .clickable(enabled = enabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CustomTheme.colors.white,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun UrgencySelector(
    selectedUrgency: Urgency,
    onUrgencyChange: (Urgency) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .clickable {
                onUrgencyChange(if (selectedUrgency == Urgency.STANDARD) Urgency.URGENT else Urgency.STANDARD)
            }
            .padding(horizontal = 15.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(
                imageVector = Icons.Default.FlashOn,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = if (selectedUrgency == Urgency.STANDARD) "Standard" else "Urgent",
                fontSize = 14.sp,
                color = CustomTheme.colors.primaryText
            )
        }
    }
}

@Composable
fun DeliveryMethodCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.outline
    val backgroundColor = if (isSelected) CustomTheme.colors.primary.copy(0.05f) else CustomTheme.colors.secondaryBackground

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(15.dp))
            .clickable { onClick() }
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CustomTheme.colors.primaryText,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = CustomTheme.colors.primaryText
        )
    }
}

@Composable
fun UploadPlaceholder(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(
                border = BorderStroke(1.dp, CustomTheme.colors.primary.copy(0.3f)),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(CustomTheme.colors.primary.copy(0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FileUpload,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = "Tap to upload files",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = "PDF, JPG (Max 5MB)",
                fontSize = 11.sp,
                color = CustomTheme.colors.secondaryText
            )
        }
    }
}

@Composable
fun RecentRequestItem(
    request: RecentRequest,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .clickable { onClick() }
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(CustomTheme.colors.primary.copy(0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = when(request.status) {
                    RequestStatus.DELIVERED -> Icons.Default.AssignmentTurnedIn
                    else -> Icons.Default.Description
                },
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(22.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = request.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = "Requested: ${request.date}",
                fontSize = 12.sp,
                color = CustomTheme.colors.secondaryText
            )
        }

        StatusBadge(status = request.status)
    }
}

@Composable
fun StatusBadge(status: RequestStatus) {
    val color = when (status) {
        RequestStatus.PROCESSING -> CustomTheme.colors.warning
        RequestStatus.DELIVERED -> CustomTheme.colors.success
        RequestStatus.REJECTED -> CustomTheme.colors.error
    }
    
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(0.1f))
            .border(1.dp, color.copy(0.3f), RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = status.name,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}
