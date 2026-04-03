package com.piyushprajpti.feature.events.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.events.dashboard.components.CalendarWidget
import com.piyushprajpti.feature.events.dashboard.components.MajorEventCard
import com.piyushprajpti.feature.events.dashboard.components.UpcomingEventCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EventDashboardScreen(
    onBackClick: () -> Unit,
    onEventClick: () -> Unit,
    viewModel: EventDashboardViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Events & Calendar"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Calendar Section
            item {
                CalendarWidget(
                    month = state.currentMonth,
                    days = state.calendarDays,
                    onDateSelected = { viewModel.onEvent(EventDashboardEvent.OnDateSelected(it)) },
                    onPreviousMonth = { viewModel.onEvent(EventDashboardEvent.OnPreviousMonth) },
                    onNextMonth = { viewModel.onEvent(EventDashboardEvent.OnNextMonth) }
                )
            }

            // Major Event Section
            state.majorEvent?.let { majorEvent ->
                item {
                    MajorEventCard(
                        event = majorEvent,
                        onClick = onEventClick
                    )
                }
            }

            // Filters Section
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.filters) { filter ->
                        val isSelected = state.selectedFilter == filter.name
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    if (isSelected) CustomTheme.colors.primary.copy(alpha = 0.1f)
                                    else CustomTheme.colors.secondaryBackground
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.outline,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .clickable { viewModel.onEvent(EventDashboardEvent.OnFilterSelected(filter.name)) }
                                .padding(horizontal = 15.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (filter.name != "All") {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(filter.color(CustomTheme.colors))
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Text(
                                text = filter.name,
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.primaryText
                            )
                        }
                    }
                }
            }

            // Upcoming Events Section
            item {
                Text(
                    text = "Upcoming Events",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
            }

            items(state.upcomingEvents) { event ->
                UpcomingEventCard(
                    event = event,
                    onClick = onEventClick
                )
            }
        }
    }
}
