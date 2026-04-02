package com.piyushprajpti.feature.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

@Composable
fun ScrollableDate(
    selectedDate: LocalDate,
    startDate: LocalDate,
    endDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val dates = remember(startDate, endDate) {
        val list = mutableListOf<LocalDate>()
        var current = startDate
        while (current <= endDate) {
            list.add(current)
            current = current.plus(DatePeriod(days = 1))
        }
        list
    }

    val initialIndex = remember { dates.indexOf(selectedDate).coerceAtLeast(0) }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)
    val density = LocalDensity.current
    var isInitialScroll by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val screenWidthPx = constraints.maxWidth
            val selectedItemWidthPx = with(density) { 70.dp.toPx() }

            LaunchedEffect(selectedDate) {
                val index = dates.indexOf(selectedDate)
                if (index != -1) {
                    val scrollOffset = (screenWidthPx / 2 - selectedItemWidthPx / 2).toInt()
                    if (isInitialScroll) {
                        listState.scrollToItem(index, -scrollOffset)
                        isInitialScroll = false
                    } else {
                        listState.animateScrollToItem(index, -scrollOffset)
                    }
                }
            }

            LazyRow(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                contentPadding = PaddingValues(horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(dates) { date ->
                    val isSelected = date == selectedDate
                    DateItem(
                        date = date,
                        isSelected = isSelected,
                        onClick = { onDateSelected(date) }
                    )
                }
            }
        }
    }
}


@Composable
fun DateItem(
    date: LocalDate,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val dayName = date.dayOfWeek.name.take(3)
    val monthName = date.month.name.take(3)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryBackground)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = monthName,
            color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.secondaryText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = date.day.toString(),
            color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.secondaryText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = dayName,
            color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.secondaryText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}