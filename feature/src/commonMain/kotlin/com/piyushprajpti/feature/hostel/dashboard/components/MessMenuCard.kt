package com.piyushprajpti.feature.hostel.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.hostel.dashboard.models.MessMenu

@Composable
fun MessMenuCard(
    menus: List<MessMenu>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 15.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Restaurant,
                contentDescription = null,
                tint = CustomTheme.colors.secondaryText,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "TODAY'S MESS MENU",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.secondaryText
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(CustomTheme.colors.secondaryBackground)
        ) {
            menus.forEachIndexed { index, menu ->
                MessItem(menu = menu)
                if (index < menus.size - 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(CustomTheme.colors.outline.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}

@Composable
private fun MessItem(menu: MessMenu) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.width(80.dp)) {
            Text(
                text = menu.mealType,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary
            )
            Text(
                text = menu.time,
                fontSize = 10.sp,
                color = CustomTheme.colors.tertiaryText
            )
        }
        
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(1.dp)
                .background(CustomTheme.colors.outline.copy(alpha = 0.5f))
        )
        
        Spacer(modifier = Modifier.width(15.dp))
        
        Text(
            text = menu.items,
            fontSize = 14.sp,
            color = CustomTheme.colors.primaryText,
            lineHeight = 20.sp
        )
    }
}
