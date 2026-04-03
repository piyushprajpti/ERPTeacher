package com.piyushprajpti.feature.hostel.mess

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.hostel.mess.components.RateMealCard
import com.piyushprajpti.feature.hostel.mess.components.SatisfactionTrendCard
import com.piyushprajpti.feature.hostel.mess.components.SkipMealsCard
import com.piyushprajpti.feature.hostel.mess.components.UpcomingSpecialCard
import com.piyushprajpti.feature.hostel.mess.components.WeeklyScheduleCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HostelMessScreen(
    onBackClick: () -> Unit,
    viewModel: HostelMessViewModel = koinViewModel()
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
            title = "Mess Menu"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            state.upcomingSpecial?.let {
                item {
                    UpcomingSpecialCard(
                        specialEvent = it,
                        onViewMenuClick = { viewModel.onEvent(HostelMessEvent.OnViewSpecialMenuClick) }
                    )
                }
            }

            item {
                WeeklyScheduleCard(
                    schedule = state.weeklySchedule,
                    selectedDayIndex = state.selectedDayIndex,
                    onDaySelected = { viewModel.onEvent(HostelMessEvent.OnDaySelected(it)) }
                )
            }

            item {
                RateMealCard(
                    todayEmojiRating = state.todayEmojiRating,
                    onEmojiSelected = { viewModel.onEvent(HostelMessEvent.OnEmojiRateMeal(it)) },
                    todayRating = state.todayRating,
                    onRatingSelected = { viewModel.onEvent(HostelMessEvent.OnRateMeal(it)) }
                )
            }

            item {
                SatisfactionTrendCard(data = state.satisfactionTrend)
            }

            item {
                SkipMealsCard(
                    isEnabled = state.isSkipMealsEnabled,
                    onToggle = { viewModel.onEvent(HostelMessEvent.OnSkipMealsToggle(it)) }
                )
            }
        }
    }
}
