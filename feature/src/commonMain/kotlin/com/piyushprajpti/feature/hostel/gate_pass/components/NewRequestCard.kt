package com.piyushprajpti.feature.hostel.gate_pass.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun NewRequestCard(
    requestType: Int,
    onRequestTypeChange: (Int) -> Unit,
    reason: String,
    onReasonChange: (String) -> Unit,
    departureTime: String,
    onDepartureClick: () -> Unit,
    returnTime: String,
    onReturnClick: () -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp)
    ) {
        // Toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CustomTheme.colors.primaryBackground)
                .padding(4.dp)
        ) {
            val types = listOf("Gate Pass", "Guest Pass")
            types.forEachIndexed { index, title ->
                val isSelected = requestType == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) CustomTheme.colors.primary else Color.Transparent)
                        .clickable { onRequestTypeChange(index) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.secondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Reason for Leave",
            fontSize = 12.sp,
            color = CustomTheme.colors.secondaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = reason,
            onValueChange = onReasonChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(CustomTheme.colors.primaryBackground),
            placeholder = {
                Text(
                    "e.g. Hospital visit, Weekend home",
                    fontSize = 14.sp,
                    color = CustomTheme.colors.tertiaryText
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = CustomTheme.colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = CustomTheme.colors.primaryText,
                unfocusedTextColor = CustomTheme.colors.primaryText
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.History,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TimeField(
                label = "Departure",
                time = departureTime,
                onClick = onDepartureClick,
                modifier = Modifier.weight(1f)
            )
            TimeField(
                label = "Expected Return",
                time = returnTime,
                onClick = onReturnClick,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = onSubmitClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = CustomTheme.colors.primary),
            shape = RoundedCornerShape(15.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = "Submit Request",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.white
            )
        }
    }
}

@Composable
private fun TimeField(
    label: String,
    time: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = CustomTheme.colors.secondaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CustomTheme.colors.primaryBackground)
                .clickable { onClick() }
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = time,
                fontSize = 14.sp,
                color = CustomTheme.colors.primaryText
            )
        }
    }
}
