package com.piyushprajpti.feature.hostel.mess.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Celebration
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.hostel.mess.models.SpecialEvent

@Composable
fun UpcomingSpecialCard(
    specialEvent: SpecialEvent,
    onViewMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CustomTheme.colors.primary,
                        CustomTheme.colors.primary.copy(alpha = 0.7f)
                    )
                )
            )
            .padding(25.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "UPCOMING SPECIALS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.white.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = specialEvent.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.white
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.Celebration,
                    contentDescription = null,
                    tint = CustomTheme.colors.white.copy(alpha = 0.5f),
                    modifier = Modifier.size(40.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "${specialEvent.date} • ${specialEvent.description}",
                fontSize = 14.sp,
                color = CustomTheme.colors.white.copy(alpha = 0.9f)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Button(
                onClick = onViewMenuClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomTheme.colors.white.copy(alpha = 0.2f),
                    contentColor = CustomTheme.colors.white
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "View Special Menu",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
