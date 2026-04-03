package com.piyushprajpti.feature.hostel.gate_pass.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.piyushprajpti.feature.hostel.gate_pass.models.GatePass

@Composable
fun ActivePassCard(
    pass: GatePass,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = "ACTIVE PASS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primary,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .background(CustomTheme.colors.secondaryBackground)
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                StatusTag(
                    status = pass.statusText,
                    color = CustomTheme.colors.success
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF1E3932)) // Dark greenish background as in image
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = pass.qrCodeUrl,
                    contentDescription = "QR Code",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = pass.type,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Text(
                text = pass.validity ?: "",
                fontSize = 14.sp,
                color = CustomTheme.colors.secondaryText
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TimeColumn(label = "Out Time", time = pass.outTime)
                TimeColumn(label = "In Time", time = pass.inTime, alignEnd = true)
            }
        }
    }
}

@Composable
private fun StatusTag(status: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = status,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Composable
private fun TimeColumn(label: String, time: String, alignEnd: Boolean = false) {
    Column(horizontalAlignment = if (alignEnd) Alignment.End else Alignment.Start) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = CustomTheme.colors.tertiaryText
        )
        Text(
            text = time,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )
    }
}
