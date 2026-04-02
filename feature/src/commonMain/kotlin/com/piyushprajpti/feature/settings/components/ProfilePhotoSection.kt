package com.piyushprajpti.feature.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun ProfilePhotoSection(
    image: String?,
    name: String,
    enrollmentNo: String,
    department: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, CustomTheme.colors.primary.copy(0.5f), CircleShape)
                .background(CustomTheme.colors.secondaryBackground),
            contentAlignment = Alignment.Center
        ) {
            if (image == null) {
                Text(
                    text = name.take(1),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CustomTheme.colors.primary
                )
            } else {
                AsyncImage(
                    model = image,
                    contentDescription = "profile photo",
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Enrollment No: $enrollmentNo",
            fontSize = 14.sp,
            color = CustomTheme.colors.secondaryText
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(CustomTheme.colors.primary.copy(alpha = 0.1f))
                .border(1.dp, CustomTheme.colors.primary.copy(0.9f), RoundedCornerShape(50.dp))
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Text(
                text = department,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary.copy(0.9f)
            )
        }
    }
}