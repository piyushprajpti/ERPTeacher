package com.piyushprajpti.feature.documents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.documents.list.StudentInfo

@Composable
fun StudentIdCard(
    info: StudentInfo,
    onShowScannerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        CustomTheme.colors.secondaryBackground,
                        CustomTheme.colors.secondaryBackground.copy(alpha = 0.5f)
                    )
                )
            )
            .border(1.dp, CustomTheme.colors.outline.copy(alpha = 0.2f), RoundedCornerShape(25.dp))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "ACADEMIC PORTAL",
                        color = CustomTheme.colors.primary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "AMS UNIVERSITY",
                        color = CustomTheme.colors.primaryText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary.copy(alpha = 0.6f),
                    modifier = Modifier.size(35.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AsyncImage(
                    model = info.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(CustomTheme.colors.outline),
                    contentScale = ContentScale.Crop
                )

                Column {
                    Text(
                        text = info.name,
                        color = CustomTheme.colors.primaryText,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Enrollment: ${info.enrollment}",
                        color = CustomTheme.colors.secondaryText,
                        fontSize = 13.sp
                    )
                    Text(
                        text = "${info.course} | ${info.year}",
                        color = CustomTheme.colors.tertiaryText,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 15.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(CustomTheme.colors.outline.copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(CustomTheme.colors.success)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = info.status,
                        color = CustomTheme.colors.secondaryText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, CustomTheme.colors.primary.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .clickable(onClick = onShowScannerClick)
                        .padding(horizontal = 15.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = null,
                        tint = CustomTheme.colors.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = "SHOW TO SCANNER",
                        color = CustomTheme.colors.primary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun Modifier.height(height: Dp): Modifier = this.size(width = 0.dp, height = height).fillMaxWidth()
