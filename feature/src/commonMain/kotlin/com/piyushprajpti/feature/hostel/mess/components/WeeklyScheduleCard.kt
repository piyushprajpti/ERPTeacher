package com.piyushprajpti.feature.hostel.mess.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.hostel.mess.models.DaySchedule

@Composable
fun WeeklyScheduleCard(
    schedule: List<DaySchedule>,
    selectedDayIndex: Int,
    onDaySelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Weekly Schedule",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(CustomTheme.colors.primary.copy(alpha = 0.1f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Week 42",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            itemsIndexed(schedule) { index, daySchedule ->
                DayColumn(
                    daySchedule = daySchedule,
                    isSelected = selectedDayIndex == index,
                    onClick = { onDaySelected(index) }
                )
            }
        }
    }
}

@Composable
private fun DayColumn(
    daySchedule: DaySchedule,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = daySchedule.day,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.tertiaryText,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        daySchedule.meals.forEach { meal ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = if (isSelected) CustomTheme.colors.primary else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(CustomTheme.colors.secondaryBackground)
                    .padding(15.dp)
            ) {
                Column {
                    Text(
                        text = meal.type,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = meal.menu,
                        fontSize = 12.sp,
                        color = CustomTheme.colors.primaryText,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}
